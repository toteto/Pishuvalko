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
  boolean saveSolution(@NonNull WritableCharacter character, @NonNull Bitmap solution);

  /** Check if there is a solution for the provided character. */
  boolean solutionExists(@NonNull WritableCharacter character);

  /**
   * Tries to read solution for the provided character.
   *
   * @return the solution, null if no solution is found
   */
  @Nullable
  Bitmap readSolution(@NonNull WritableCharacter character);
}
