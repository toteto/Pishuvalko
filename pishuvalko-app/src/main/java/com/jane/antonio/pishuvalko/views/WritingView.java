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
  private float shiftX = 0;
  private float shiftY = 0;
  private final List<Segment> handDrawnSegments;

  /** @param context the context. */
  public WritingView(Context context) {
    this(context, null);
  }

  /**
   * @param context the context
   * @param attrs the view params
   */
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
    prevPoint = new PointF(x, y); // initial point
    handDrawnSegments.clear(); // clearing the previously drawn segments
    if (writingViewListener != null) {
      // scale the point down to the scale how it is defined in the WritableCharacter
      PointF newPrevPoint = PishuvalkoUtils.scalePoint(prevPoint, scaleMatrix, true);
      // send the scaled point to the listener
      newPrevPoint = writingViewListener.onStartedWriting(newPrevPoint);
      if (newPrevPoint != null) {
        // the listener confirmed that the point is valid
        // scale the point back to scale of the drawing view
        newPrevPoint = PishuvalkoUtils.scalePoint(newPrevPoint, scaleMatrix, false);
        handDrawnPath.moveTo(newPrevPoint.x, newPrevPoint.y);

        // shift values used later for coordinates shift based on the feedback from the listener
        shiftX = prevPoint.x - newPrevPoint.x;
        shiftY = prevPoint.y - newPrevPoint.y;
      }
      // updating the point that will be used for later. It can be null, meaning it is invalid prevPoint.
      prevPoint = newPrevPoint;
    }
  }

  /** Method responsible for handing the writing writing movement on the view. */
  private void writeSegment(float nextX, float nextY) {
    if (prevPoint == null) {
      // invalid prev point, we don't write anything
      return;
    }

    // defining current point with the shift values
    PointF currPoint = new PointF(nextX - shiftX, nextY - shiftY);
    double distance = PishuvalkoUtils.calculateDistance(prevPoint, currPoint);
    // avoiding jitter by defining minimal size of one segment
    if (distance < 20) {
      return;
    }
    Segment drawnSegment = new Line(prevPoint, currPoint);
    handDrawnSegments.add(drawnSegment);
    // drawing just a line path
    handDrawnPath.lineTo(currPoint.x, currPoint.y);

    prevPoint = currPoint;
    if (writingViewListener != null) {
      // sending the listener the drawn segment, scaled down to the scale of the Writable character
      writingViewListener.onWroteSegment(drawnSegment.scaleSegment(scaleMatrix, true));
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
    if (handDrawnSegments.size() > 0) {
      ListIterator<Segment> iterator = handDrawnSegments.listIterator();
      Segment prevSegment = iterator.next();
      while (iterator.hasNext()) {
        Segment currSegment = iterator.next();
        prevSegment = prevSegment.mergeSegments(currSegment, 0.1f);
        if (prevSegment == null) {
          break;
        }
      }
      if (prevSegment != null) {
        handDrawnPath.reset();
        handDrawnPath.addPath(prevSegment.getDrawablePath(false));
        return true;
      }
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

  /**
   * Sets the current character that the user will be writing. Also scales the steps defined in the character and sets
   * the new scale matrix.
   */
  public void setCurrentCharacter(WritableCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
    drawingSteps.clear();
    // initialize the new scale matrix
    onSizeChanged(getWidth(), getHeight(), getWidth(), getHeight());
    drawingSteps.addAll(PishuvalkoUtils.scaleWritableCharacterStepPaths(currentCharacter, scaleMatrix));
    invalidate();
  }

  public void setWritingViewListener(WritingViewListener writingViewListener) {
    this.writingViewListener = writingViewListener;
  }

}
