package com.jane.antonio.pishuvalko.models;

import android.graphics.Bitmap;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** Interface needed for working with solutions. */
public interface ISolutionStorage {
  @Retention(RetentionPolicy.SOURCE)
  @IntDef({SOLUTION_APPROVED, SOLUTION_DECLINED, SOLUTION_NOT_RATED})
  public @interface SolutionState {
  }

  /**
   * State meaning the solution has been approved by the parent.
   */
  public static final int SOLUTION_APPROVED = 1;
  /**
   * State meaning the solution has been declined by the parent.
   */
  public static final int SOLUTION_DECLINED = 2;
  /**
   * State meaning the solution has not yet been rated by the parent.
   */
  public static final int SOLUTION_NOT_RATED = 3;
  /**
   * Saves the solution mapped to the provided character.
   *
   * @return the success of the saving process
   */
  boolean saveSolution(@NonNull Bitmap solution, @NonNull WritableCharacter character);

  /** Check if there is a solution for the provided character. */
  boolean solutionExists(@NonNull WritableCharacter character);

  /**
   * Tries to read solution for the provided character.
   *
   * @return the solution, null if no solution is found
   */
  @Nullable
  Bitmap readSolution(@NonNull WritableCharacter character);

  /**
   * Tries to remove previously saved solution.
   *
   * @return true if it has been successfully removed or it hasnt been found. False otherwise.
   */
  boolean removeSolution(@NonNull WritableCharacter character);

  /** Reset that approved state of the provided character. */
  void resetApprovalStatus(@NonNull WritableCharacter character);

  /** Write to storage approve status for the provided character. */
  void approveSolution(@NonNull WritableCharacter character);

  /** Write to storage decline status for the provided character. */
  void declineSolution(@NonNull WritableCharacter character);

  /**
   * Get the approval status of the solution.
   *
   * @return true if the solution has been approved. False if the solution has been declined by the parent. Null if the
   * provided character hasn't been nor approved nor declined.
   */
  @SolutionStorage.SolutionState
  int isSolutionApproved(@NonNull WritableCharacter character);
}
