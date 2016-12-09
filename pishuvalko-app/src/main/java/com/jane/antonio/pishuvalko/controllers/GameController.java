package com.jane.antonio.pishuvalko.controllers;

import android.graphics.Bitmap;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.jane.antonio.pishuvalko.WritingGameInterface;
import com.jane.antonio.pishuvalko.models.ISolutionStorage;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.views.WritingImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

/** Controller/presenter for the state of the game and responsible for handing the user inputs. */
public class GameController {
  @NonNull
  private final WritingGameInterface gameInterface;
  @NonNull
  private final ISolutionStorage solutionStorage;
  private WritingImageView writingImageView;
  private final List<WritableCharacter> characterList;
  private ListIterator<WritableCharacter> characterIterator;
  private WritableCharacter currentCharacter;

  /**
   * Constructor.
   *
   * @param gameInterface the UI where the user is interacting with the game.
   * @param solutionStorage the storage that will keep the solutions
   */
  public GameController(@NonNull WritingGameInterface gameInterface, @NonNull ISolutionStorage solutionStorage) {
    this.gameInterface = gameInterface;
    this.solutionStorage = solutionStorage;
    characterList = new ArrayList<>();
  }

  /**
   * Should be called when the activity is ready and visible.
   */
  public void onStart(@NonNull WritingImageView writingImageView, @NonNull List<WritableCharacter> writableCharacters,
    @IntRange(from = 0) int index) {
    this.writingImageView = writingImageView;
    if (writableCharacters.size() > index) {
      characterList.clear();
      characterList.addAll(writableCharacters);
      characterIterator = characterList.listIterator(index);

      currentCharacter = characterIterator.next();
      writingImageView.showCharacter(currentCharacter, true, true);
      updateNavigation();
    } else {
      throw new IndexOutOfBoundsException("The provided list contains less items than the provided index");
    }
  }

  /** Should be called when the views from the activity have been detached or the activity has been stopped. */
  public void onStop() {

  }

  /** Called from the user when he clicks next. Should display next level if there is available one. */
  public void onNext() {
    if (characterIterator.hasNext()) {
      saveCurrentSolution();
      currentCharacter = characterIterator.next();
      writingImageView.showCharacter(currentCharacter, true, true);
    }
    updateNavigation();
  }

  /** called from the user when he click previous. Should display the previous level. */
  public void onPrevious() {
    if (characterIterator.hasPrevious()) {
      saveCurrentSolution();
      currentCharacter = characterIterator.previous();
      writingImageView.showCharacter(currentCharacter, true, true);
    }
    updateNavigation();
  }

  private void updateNavigation() {
    gameInterface.setPreviousEnabled(characterIterator != null && characterIterator.hasPrevious());
    gameInterface.setNextEnabled(characterIterator != null && characterIterator.hasNext());
  }


  /** Called when the user click the close button. */
  public void onClose() {

  }

  /** Saves the {@link GameController#currentCharacter} solution to  {@link GameController#solutionStorage} */
  private void saveCurrentSolution() {
    final Bitmap solution = writingImageView.getSolution();
    if (!solutionStorage.saveSolution(currentCharacter, solution)) {
      throw new RuntimeException("Unable to save current solution.");
    }
  }
}
