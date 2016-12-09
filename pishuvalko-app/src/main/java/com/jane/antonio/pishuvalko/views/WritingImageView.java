package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

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

  /**
   * Display the provided character on the view.
   *
   * @param currentCharacter the character to get the drawables from
   * @param showShape if true, shape layer will be shown
   * @param showSteps if true, steps layer will be shown
   */
  public void showCharacter(WritableCharacter currentCharacter, boolean showShape, boolean showSteps) {
    this.characterShape = currentCharacter.getOutlineDrawable(getContext());
    this.characterSteps = currentCharacter.getStepsDrawable(getContext());

    List<Drawable> layers = new LinkedList<>();
    if (showShape && characterShape != null) {
      layers.add(characterShape);
    }
    if (showSteps && characterSteps != null) {
      layers.add(characterSteps);
    }
    LayerDrawable layerDrawable = new LayerDrawable(layers.toArray(new Drawable[layers.size()]));
    setImageDrawable(layerDrawable);
  }

  /** Erases the writing made by the user. */
  public void eraseWriting() {
    drawPath.reset();
    invalidate();
  }

  /** Retrieve the image that the user has drawn on the screen. */
  public Bitmap retrieveDrawnImage() {
    final Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.RGB_565);
    final Canvas canvas = new Canvas(bitmap);
    canvas.drawPath(drawPath, drawPaint);

    return Bitmap.createBitmap(bitmap);
  }
}
