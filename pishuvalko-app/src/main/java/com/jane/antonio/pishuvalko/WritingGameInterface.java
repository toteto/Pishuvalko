package com.jane.antonio.pishuvalko;

/**
 * Interface that provides the required method for the writing game controller.
 */
public interface WritingGameInterface {
  /**
   * Enabled/Disables the control for erasing the board.
   */
  void setEraseEnabled(boolean toEnable);

  /**
   * Close the game activity.
   */
  void exit();

  void showClapping(int duration);
}
