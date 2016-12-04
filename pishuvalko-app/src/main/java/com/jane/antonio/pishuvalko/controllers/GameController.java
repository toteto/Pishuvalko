package com.jane.antonio.pishuvalko.controllers;

import android.support.annotation.Nullable;

import com.jane.antonio.pishuvalko.WritingGameInterface;
import com.jane.antonio.pishuvalko.models.WritableCapitalLetter;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

/** Controller/presenter for the state of the game and responsible for handing the user inputs. */
public class GameController {
  private final WritingGameInterface gameInterface;

  /**
   * Constructor.
   *
   * @param gameInterface the UI where the user is interacting with the game.
   */
  public GameController(WritingGameInterface gameInterface) {
    this.gameInterface = gameInterface;
  }


  /**
   * Should be called when the activity is ready and visible.
   *
   * @param writableCharacter test parameter for now
   */
  public void onStart(@Nullable final WritableCharacter writableCharacter) {
    if (writableCharacter == null) {
      gameInterface.showCharacter(new WritableCapitalLetter("A", "A.png"));
    } else {
      gameInterface.showCharacter(writableCharacter);
    }
  }

  /** Should be called when the views from the activity have been detached or the activity has been stopped. */
  public void onStop() {

  }

  /** Called from the user when he clicks next. Should display next level if there is available one. */
  public void onNextClicked() {

  }

  /** called from the user when he click previous. Should display the previous level. */
  public void onPreviusClicked() {

  }


  /** Called when the user click the close button. */
  public void onCloseClicked() {

  }
}
