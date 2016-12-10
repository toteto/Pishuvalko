package com.jane.antonio.pishuvalko.controllers;

import android.graphics.Bitmap;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

import com.jane.antonio.pishuvalko.WritingGameInterface;
import com.jane.antonio.pishuvalko.models.ISolutionStorage;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.views.WritingImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import static com.jane.antonio.pishuvalko.models.WritableCharacter.NOR_SHAPE_NOR_STEPS;
import static com.jane.antonio.pishuvalko.models.WritableCharacter.ONLY_SHAPE;
import static com.jane.antonio.pishuvalko.models.WritableCharacter.SHAPE_AND_STEPS;

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
  @WritableCharacter.GuidesType
  private int currentGuideType = SHAPE_AND_STEPS;

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
      writingImageView.showCharacter(currentCharacter, SHAPE_AND_STEPS);
    } else {
      throw new IndexOutOfBoundsException("The provided list contains less items than the provided index");
    }
  }

  /** Should be called when the views from the activity have been detached or the activity has been stopped. */
  public void onStop() {

  }

  /** Called when the user click the close button. */
  public void onClose() {
    gameInterface.exit();
  }

  /** Saves the {@link GameController#currentCharacter} solution to  {@link GameController#solutionStorage} */
  private void saveCurrentSolution() {
    final Bitmap solution = writingImageView.getSolution();
    if (!solutionStorage.saveSolution(solution, currentCharacter, currentGuideType)) {
      throw new RuntimeException("Unable to save current solution.");
    }
  }

  /**
   * When there is a confirmation of the current showing game. Shows the next guide type or the next character based on
   * the current guide type.
   */
  public void onConfirm() {
    saveCurrentSolution();
    switch (currentGuideType) {
      case SHAPE_AND_STEPS:
        currentGuideType = ONLY_SHAPE;
        break;
      case WritableCharacter.ONLY_SHAPE:
        currentGuideType = NOR_SHAPE_NOR_STEPS;
        break;
      case WritableCharacter.NOR_SHAPE_NOR_STEPS:
        if (characterIterator.hasNext()) {
          onNext();
        } else {
          onClose();
        }
        return;
    }
    writingImageView.showCharacter(currentCharacter, currentGuideType);
  }

  /** Request next character. */
  public void onNext() {
    if (characterIterator.hasNext()) {
      currentCharacter = characterIterator.next();
      currentGuideType = SHAPE_AND_STEPS;
      writingImageView.showCharacter(currentCharacter, currentGuideType);
    }
  }

  /** On change of the default drawing color. */
  public void onColorSelected(@ColorInt int color) {
    writingImageView.setDrawingColor(color);
  }

  /** On erase action. */
  public void onErase() {
    writingImageView.eraseWriting();
  }
}
