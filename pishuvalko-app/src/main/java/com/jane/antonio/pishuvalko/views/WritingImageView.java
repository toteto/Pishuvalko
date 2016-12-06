package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jane.antonio.pishuvalko.R;

/** ImageView with writable surface. */
public class WritingImageView extends View {
  private final Path drawPath;
  private final Paint drawPaint;

  private Drawable characterShape;
  private Drawable characterSteps;

  /** . */
  public WritingImageView(Context context) {
    this(context, null);
  }

  /** . */
  public WritingImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    drawPath = new Path();
    drawPaint = new Paint();

    drawPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    drawPaint.setStyle(Paint.Style.STROKE);
    drawPaint.setStrokeWidth(context.getResources().getInteger(R.integer.draw_stroke_width));
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        drawPath.moveTo(event.getX(), event.getY());
        invalidate();
        return true;
      case MotionEvent.ACTION_MOVE:
        drawPath.lineTo(event.getX(), event.getY());
        invalidate();
        return true;
      case MotionEvent.ACTION_UP:
    }
    return super.onTouchEvent(event);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (characterShape != null) {
      characterShape.setBounds(getLeft(), getTop(), getRight(), getBottom());
      characterShape.draw(canvas);
    }

    if (characterSteps != null) {
      characterSteps.setBounds(getLeft(), getTop(), getRight(), getBottom());
      characterSteps.draw(canvas);
    }

    canvas.drawPath(drawPath, drawPaint);
  }

  /** Sets a writable character that the user will be drawing on. */
  public void setShapeAndStepsLayers(@NonNull Drawable shape, @NonNull Drawable steps) {
    drawPath.reset();
    this.characterShape = shape;
    this.characterSteps = steps;
    invalidate();
  }

  /**
   * Sets the visibility of the shape for the current {@link com.jane.antonio.pishuvalko.models.WritableCharacter}. If
   * there is no shape drawable set previously, this method will be ignored.
   */
  @NonNull
  public WritingImageView withShape(boolean with) {
    if (characterShape != null) {
      characterShape.setVisible(with, false);
      invalidate();
    }
    return this;
  }

  /**
   * Sets the visibility of the steps for the current {@link com.jane.antonio.pishuvalko.models.WritableCharacter}. If
   * there is no shape drawable set previously, this method will be ignored.
   */
  @NonNull
  public WritingImageView withSteps(boolean with) {
    if (characterSteps != null) {
      characterSteps.setVisible(with, false);
      invalidate();
    }
    return this;
  }

}
