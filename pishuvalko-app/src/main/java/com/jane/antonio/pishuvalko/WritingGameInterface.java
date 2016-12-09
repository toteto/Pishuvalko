package com.jane.antonio.pishuvalko;

/** Interface that provides the required method for the writing game controller. */
public interface WritingGameInterface {
  /** Enabled/Disables the control for going to the next level. */
  void setNextEnabled(boolean toEnable);

  /** Enabled/Disables the control for going to the previous level. */
  void setPreviousEnabled(boolean toEnable);
}
