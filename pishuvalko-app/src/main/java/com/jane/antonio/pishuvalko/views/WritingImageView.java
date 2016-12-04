package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

/** ImageView with writable surface. */
public class WritingImageView extends ImageView {
  private final Path drawPath;
  private final Paint drawPaint;

  public WritingImageView(Context context) {
    this(context, null);
  }

  public WritingImageView(Context context, AttributeSet attrs) {
    super(context, attrs);
    drawPath = new Path();
    drawPaint = new Paint();

    drawPaint.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
    drawPaint.setStyle(Paint.Style.STROKE);
    drawPaint.setStrokeWidth(context.getResources().getInteger(R.integer.draw_stroke_width));
    drawPaint.setAntiAlias(true);
    drawPaint.setStrokeJoin(Paint.Join.ROUND);
    drawPaint.setStrokeCap(Paint.Cap.ROUND);
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

  public void setWritableCharacter(WritableCharacter character) {

  }
}
