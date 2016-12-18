package com.jane.antonio.pishuvalko.models;

import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/** Interface needed for working with solutions. */
public interface ISolutionStorage {
  /**
   * Saves the solution mapped to the provided character.
   *
   * @return the success of the saving process
   */
  boolean saveSolution(@NonNull Bitmap solution, @NonNull WritableCharacter character,
    @WritableCharacter.GuidesType int currentGuideType);

  /** Check if there is a solution for the provided character. */
  boolean solutionExists(@NonNull WritableCharacter character, @WritableCharacter.GuidesType int guideType);

  /**
   * Tries to read solution for the provided character.
   *
   * @return the solution, null if no solution is found
   */
  @Nullable
  Bitmap readSolution(@NonNull WritableCharacter character, @WritableCharacter.GuidesType int guideType);

  /**
   * Tries to remove previously saved solution.
   *
   * @return true if it has been successfully removed or it hasnt been found. False otherwise.
   */
  boolean removeSolution(@NonNull WritableCharacter character, @WritableCharacter.GuidesType int guideType);
}
