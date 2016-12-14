package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** ImageView with writable surface. */
public class WritingImageView extends ImageView {
  private final List<Path> drawingPaths;
  private final List<Paint> drawingPaints;
  private final Paint basePaint;

  private Path currPath;
  private Paint currPaint;

  @Nullable
  private Drawable characterShape;
  @Nullable
  private Drawable characterSteps;

  /** . */
  public WritingImageView(Context context) {
    this(context, null);
  }

  /** . */
  public WritingImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    drawingPaths = new ArrayList<>();
    drawingPaints = new ArrayList<>();

    basePaint = new Paint();
    basePaint.setColor(ContextCompat.getColor(getContext(), android.R.color.white));
    basePaint.setStyle(Paint.Style.STROKE);
    basePaint.setStrokeWidth(context.getResources().getInteger(R.integer.draw_stroke_width));
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        if (currPath == null) {
          initPathAndPaint();
        }
        currPath.moveTo(event.getX(), event.getY());
        invalidate();
        return true;
      case MotionEvent.ACTION_MOVE:
        if (currPath == null) {
          initPathAndPaint();
        }
        currPath.lineTo(event.getX(), event.getY());
        invalidate();
        return true;
      case MotionEvent.ACTION_UP:
    }
    return super.onTouchEvent(event);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    drawPaths(canvas);
  }

  private void drawPaths(@NonNull Canvas canvas) {
    for (int i = 0; i < drawingPaths.size(); i++) {
      canvas.drawPath(drawingPaths.get(i), drawingPaints.get(i));
    }
  }

  /**
   * Display the provided character on the view.
   *
   * @param currentCharacter the character to get the drawables from
   */
  public void showCharacter(WritableCharacter currentCharacter, @WritableCharacter.GuidesType int guideType) {
    this.characterShape = currentCharacter.getOutlineDrawable(getContext());
    this.characterSteps = currentCharacter.getStepsDrawable(getContext());

    List<Drawable> layers = new LinkedList<>();
    if (characterShape != null && guideType != WritableCharacter.NOR_SHAPE_NOR_STEPS) {
      layers.add(characterShape);
    }
    if (characterSteps != null && guideType == WritableCharacter.SHAPE_AND_STEPS) {
      layers.add(characterSteps);
    }
    LayerDrawable layerDrawable = new LayerDrawable(layers.toArray(new Drawable[layers.size()]));
    setImageDrawable(layerDrawable);
  }

  /** Erases the writing made by the user. */
  public void eraseWriting() {
    drawingPaths.clear();
    drawingPaints.clear();
    currPath = new Path();
    drawingPaths.add(currPath);
    drawingPaints.add(currPaint);
    invalidate();
  }

  /** Retrieve the image that the user has drawn on the screen. */
  @NonNull
  public Bitmap getSolution() {
    final Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
    final Canvas canvas = new Canvas(bitmap);
    drawPaths(canvas);

    return Bitmap.createBitmap(bitmap);
  }

  /** Sets the drawing color of the {@link WritingImageView#currPaint}. */
  public void setDrawingColor(@ColorInt int color) {
    initPathAndPaint();
    currPaint.setColor(color);
  }

  private void initPathAndPaint() {
    currPath = new Path();
    currPaint = new Paint(basePaint);
    drawingPaths.add(currPath);
    drawingPaints.add(currPaint);
  }
}
