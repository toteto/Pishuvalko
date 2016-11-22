package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.jane.antonio.pishuvalko.controllers.OnWritingChangeListener;
import com.jane.antonio.pishuvalko.models.Progress;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.utils.PishuvalkoUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * Custom view that will be used by the user to write the {@link WritableCharacter} on.
 */
public class WritingView extends View {
  private static final String LOG_TAG = WritingView.class.getSimpleName();
  private WritableCharacter currentCharacter;
  private Progress currentProgress;
  private OnWritingChangeListener onWritingChangeListener;

  private final Paint characterPaint;
  private final Paint writingPaint;

  private final Matrix scaleMatrix;
  private final List<Path> drawingPaths;

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

    scaleMatrix = new Matrix();
    drawingPaths = new LinkedList<>();
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        Log.d(LOG_TAG, "ACTION_DOWN: " + event.getX() + ":" + event.getY());
        invalidate();
        return true;
      case MotionEvent.ACTION_MOVE:
        invalidate();
        return true;
      case MotionEvent.ACTION_UP:
        invalidate();
        return true;
    }
    if (onWritingChangeListener != null) {
      onWritingChangeListener.onWritingChange(currentCharacter, /*tmp*/ new Path());
    }
    return false;
  }


  @Override
  protected void onDraw(Canvas canvas) {
    for (Path path : drawingPaths) {
      canvas.drawPath(path, characterPaint);
    }
    super.onDraw(canvas);
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    float maxScaleByWidth = w / currentCharacter.getWidth();
    float maxScaleByHeight = h / currentCharacter.getHeight();

    float scale = Math.min(maxScaleByHeight, maxScaleByWidth);
    scaleMatrix.setScale(scale, scale);
    drawingPaths.clear();
    drawingPaths.addAll(PishuvalkoUtils.scaleWritableCharacterStepPaths(currentCharacter, scaleMatrix));
  }

  public void setCurrentCharacter(WritableCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
    drawingPaths.clear();
    drawingPaths.addAll(PishuvalkoUtils.scaleWritableCharacterStepPaths(currentCharacter, scaleMatrix));
    currentProgress = null;
    invalidate();
  }

  public void setCurrentProgress(Progress currentProgress) {
    this.currentProgress = currentProgress;
    invalidate();
  }

  public void setOnWritingChangeListener(OnWritingChangeListener onWritingChangeListener) {
    this.onWritingChangeListener = onWritingChangeListener;
  }

}
