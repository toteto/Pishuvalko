package com.jane.antonio.pishuvalko.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.jane.antonio.pishuvalko.models.WritableCharacter;

/** Custom view that will be used by the user to write the {@link WritableCharacter} on. */
public class WritingView extends View {
  private WritableCharacter currentCharacter;

  public WritingView(Context context) {
    super(context);
  }

  public WritingView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public WritableCharacter getCurrentCharacter() {
    return currentCharacter;
  }

  public void setCurrentCharacter(WritableCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;
  }
}
