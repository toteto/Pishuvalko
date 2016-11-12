package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.jane.antonio.pishuvalko.controllers.OnWritingChangeListener;
import com.jane.antonio.pishuvalko.models.Progress;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

/** Custom view that will be used by the user to write the {@link WritableCharacter} on. */
public class WritingView extends View {
  private WritableCharacter currentCharacter;
  private Progress currentProgress;
  private OnWritingChangeListener onWritingChangeListener;

  public WritingView(Context context) {
    this(context, null);
  }

  public WritingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    if (onWritingChangeListener != null){
      onWritingChangeListener.onWritingChange(currentCharacter, /*tmp*/ new Path());
    }
    return super.onTouchEvent(event);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
  }

  public WritableCharacter getCurrentCharacter() {
    return currentCharacter;
  }

  public void setCurrentCharacter(WritableCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
    currentProgress = null;
    invalidate();
  }

  public void setCurrentProgress(Progress currentProgress) {
    this.currentProgress = currentProgress;
    invalidate();
  }

  public void setOnWritingChangeListener(OnWritingChangeListener onWritingChangeListener) {
    this.onWritingChangeListener =onWritingChangeListener ;
  }
}
