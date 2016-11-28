package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jane.antonio.pishuvalko.controllers.WritingViewListener;
import com.jane.antonio.pishuvalko.models.Line;
import com.jane.antonio.pishuvalko.models.Segment;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.utils.PishuvalkoUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * Custom view that will be used by the user to write the {@link WritableCharacter} on.
 */
public class WritingView extends View {
  private static final String LOG_TAG = WritingView.class.getSimpleName();
  private WritableCharacter currentCharacter;
  private WritingViewListener writingViewListener;

  private final Paint characterPaint;
  private final Paint writingPaint;

  private final Matrix scaleMatrix;
  private final List<Path> drawingSteps;

  private final Path handDrawnPath;

  private PointF prevPoint;
  private final List<Segment> handDrawnSegments;

  public WritingView(Context context) {
    this(context, null);
  }

  public WritingView(Context context, AttributeSet attrs) {
    super(context, attrs);

    characterPaint = new Paint();
    characterPaint.setColor(ContextCompat.getColor(context, android.R.color.holo_red_dark));
    characterPaint.setStrokeWidth(50);
    characterPaint.setStyle(Paint.Style.STROKE);

    writingPaint = new Paint();
    writingPaint.setColor(ContextCompat.getColor(context, android.R.color.holo_orange_dark));
    writingPaint.setStrokeWidth(20);
    writingPaint.setStyle(Paint.Style.STROKE);

    scaleMatrix = new Matrix();
    drawingSteps = new LinkedList<>();

    handDrawnPath = new Path();

    handDrawnSegments = new LinkedList<>();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        startedWriting(event.getX(), event.getY());
        invalidate();
        return true;
      case MotionEvent.ACTION_MOVE:
        writeSegment(event.getX(), event.getY());
        invalidate();
        return true;
      case MotionEvent.ACTION_UP:
        endWriting();
        invalidate();
        return true;
    }
    return false;
  }

  /** Method responsible for handing when the user starts writing on the view. */
  private void startedWriting(float x, float y) {
    prevPoint = new PointF(x, y);
    handDrawnPath.moveTo(x, y);
    handDrawnSegments.clear();
    if (writingViewListener != null) {
      writingViewListener.onStartedWriting();
    }
  }

  /** Method responsible for handing the writing writing movement on the view. */
  private void writeSegment(float nextX, float nextY) {
    PointF currPoint = new PointF(nextX, nextY);
    double distance = PishuvalkoUtils.calculateDistance(prevPoint, currPoint);
    // avoiding jitter by defining minimal size of one segment
    if (distance < 20) {
      return;
    }
    Segment drawnSegment = new Line(prevPoint, currPoint);
    handDrawnSegments.add(drawnSegment);

    prevPoint = currPoint;
    handDrawnPath.lineTo(nextX, nextY);
    if (writingViewListener != null) {
      writingViewListener.onWroteSegment(drawnSegment);
    }
  }

  /** Method responsible for handing when the user stops writing (ex. lifts up the finger). */
  private void endWriting() {
    mergeHandDrawnSegments();
    if (writingViewListener != null) {
      writingViewListener.onEndedWriting();
    }
  }


  /**
   * Merges the hand drawn segments stored in this.handDrawnSegments.
   *
   * @return true  if it is successfully merged.
   */
  private boolean mergeHandDrawnSegments() {
    ListIterator<Segment> iterator = handDrawnSegments.listIterator();
    Segment prevSegment = iterator.next();
    while (iterator.hasNext()) {
      Segment currSegment = iterator.next();
      prevSegment = prevSegment.mergeSegments(currSegment, 1f);
      if (prevSegment == null) {
        break;
      }
    }
    if (prevSegment != null) {
      handDrawnPath.reset();
      handDrawnPath.addPath(prevSegment.getDrawablePath(false));
      return true;
    }
    return false;
  }


  @Override
  protected void onDraw(Canvas canvas) {
    for (Path path : drawingSteps) {
      canvas.drawPath(path, characterPaint);
    }
    canvas.drawPath(handDrawnPath, writingPaint);
    super.onDraw(canvas);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    if (currentCharacter != null) {
      float maxScaleByWidth = w / currentCharacter.getWidth();
      float maxScaleByHeight = h / currentCharacter.getHeight();

      float scale = Math.min(maxScaleByHeight, maxScaleByWidth);
      scaleMatrix.setScale(scale, scale);
      drawingSteps.clear();
      drawingSteps.addAll(PishuvalkoUtils.scaleWritableCharacterStepPaths(currentCharacter, scaleMatrix));
    }
  }

  public void setCurrentCharacter(WritableCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
    drawingSteps.clear();
    drawingSteps.addAll(PishuvalkoUtils.scaleWritableCharacterStepPaths(currentCharacter, scaleMatrix));
    invalidate();
  }

  public void setWritingViewListener(WritingViewListener writingViewListener) {
    this.writingViewListener = writingViewListener;
  }

}
