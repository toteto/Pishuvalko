package com.jane.antonio.pishuvalko.controllers;

import com.jane.antonio.pishuvalko.WritingGameInterface;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/** Controller/presenter for the state of the game and responsible for handing the user inputs. */
public class GameController {
  private final WritingGameInterface gameInterface;
  private final List<WritableCharacter> characterList;
  private ListIterator<WritableCharacter> characterIterator;
  private WritableCharacter currentCharacter;

  /**
   * Constructor.
   *
   * @param gameInterface the UI where the user is interacting with the game.
   */
  public GameController(WritingGameInterface gameInterface) {
    this.gameInterface = gameInterface;
    characterList = new ArrayList<>();
  }


  /**
   * Should be called when the activity is ready and visible.
   */
  public void onStart(List<WritableCharacter> writableCharacters, int index) {
    if (writableCharacters.size() > index) {
      characterList.clear();
      characterList.addAll(writableCharacters);
      characterIterator = characterList.listIterator(index);
      currentCharacter = characterIterator.next();
      gameInterface.showCharacter(currentCharacter);
    } else {
      throw new IndexOutOfBoundsException("The provided list contains less items than the provided index");
    }
  }

  /** Should be called when the views from the activity have been detached or the activity has been stopped. */
  public void onStop() {

  }

  /** Called from the user when he clicks next. Should display next level if there is available one. */
  public void onNextClicked() {

  }

  /** called from the user when he click previous. Should display the previous level. */
  public void onPreviousClicked() {

  }


  /** Called when the user click the close button. */
  public void onCloseClicked() {

  }
}
