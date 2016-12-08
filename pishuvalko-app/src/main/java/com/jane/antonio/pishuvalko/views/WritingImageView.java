package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.jane.antonio.pishuvalko.R;

import java.util.LinkedList;
import java.util.List;

/** ImageView with writable surface. */
public class WritingImageView extends ImageView {
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
    canvas.drawPath(drawPath, drawPaint);
  }

  /** Sets a writable character that the user will be drawing on. */
  public void setShapeAndStepsLayers(@NonNull Drawable shape, @NonNull Drawable steps) {
    drawPath.reset();
    this.characterShape = shape;
    this.characterSteps = steps;
    setShapeAndStepsVisibility(false, false);
  }

  /** Sets the visibility of the shape and steps for the current character. */
  public void setShapeAndStepsVisibility(boolean shapeVisible, boolean stepsVisible) {
    List<Drawable> layers = new LinkedList<>();
    if (shapeVisible && characterShape != null) {
      layers.add(characterShape);
    }
    if (stepsVisible && characterSteps != null) {
      layers.add(characterSteps);
    }
    LayerDrawable layerDrawable = new LayerDrawable(layers.toArray(new Drawable[layers.size()]));
    setImageDrawable(layerDrawable);
  }

}
