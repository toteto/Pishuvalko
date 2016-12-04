package com.jane.antonio.pishuvalko;

import com.jane.antonio.pishuvalko.models.WritableCharacter;

/** Interface that provides the required method for the writing game controller. */
public interface WritingGameInterface {
  /** Should show the porivded character on the {@link com.jane.antonio.pishuvalko.views.WritingImageView}. */
  void showCharacter(WritableCharacter character);

  /** Enabled/Disables the control for going to the next level. */
  void setNextEnabled(boolean toEnable);

  /** Enabled/Disables the control for going to the previous level. */
  void setPreviousEnabled(boolean toEnable);
}
