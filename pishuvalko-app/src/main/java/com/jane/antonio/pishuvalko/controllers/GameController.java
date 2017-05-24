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
  private int currentGuideTypeIndex = 0;
  /** Game types that are available for this game. Now are made static, should be converted to dynamic types later. */
  @WritableCharacter.GuidesType
  public static final int[] GAME_GUIDE_TYPES = {SHAPE_AND_STEPS, ONLY_SHAPE};

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
    gameInterface.showClapping(1);
    final Bitmap solution = writingImageView.getSolution();
    if (!solutionStorage.saveSolution(solution, currentCharacter)) {
      throw new RuntimeException("Unable to save current solution.");
    }
  }

  /**
   * When there is a confirmation of the current showing game. Shows the next guide type or the next character based on
   * the current guide type.
   */
  public void onConfirm() {
    if (writingImageView.isAnytingDrawn()) {
      if (currentGuideTypeIndex + 1 < GAME_GUIDE_TYPES.length) {
        // there is more guide types for the current character
        onErase();
        showNextGuideType();
      } else if (characterIterator.hasNext()) {
        // no more guide types for the current character
        saveCurrentSolution(); // save the solution
        onErase();
        showNextCharacter(); // go to the next character
      } else {
        saveCurrentSolution(); // save the solution
        onClose(); // no more characters, close the writing game
      }
    }
  }

  /**
   * Shows the next guide type.
   *
   * @throws IndexOutOfBoundsException if there is no more guide types available.
   */
  private void showNextGuideType() {
    currentGuideTypeIndex += 1;
    writingImageView.showCharacter(currentCharacter, getCurrentGuideType());
  }

  /**
   * Request next character.
   *
   * @throws java.util.NoSuchElementException when no next character is available.
   */
  public void showNextCharacter() {
    currentCharacter = characterIterator.next();
    currentGuideTypeIndex = 0;
    writingImageView.showCharacter(currentCharacter, getCurrentGuideType());
  }

  /** On change of the default drawing color. */
  public void onColorSelected(@ColorInt int color) {
    writingImageView.setDrawingColor(color);
  }

  /** On erase action. */
  public void onErase() {
    writingImageView.eraseWriting();
  }

  public int[] getGameGuideTypes() {
    return GAME_GUIDE_TYPES;
  }

  @WritableCharacter.GuidesType
  public int getCurrentGuideType() {
    return GAME_GUIDE_TYPES[currentGuideTypeIndex];
  }
}
