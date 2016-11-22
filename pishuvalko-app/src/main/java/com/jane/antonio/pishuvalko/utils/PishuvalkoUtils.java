package com.jane.antonio.pishuvalko.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.support.annotation.NonNull;

import com.jane.antonio.pishuvalko.models.Step;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.LinkedList;
import java.util.List;

public class PishuvalkoUtils {
  private PishuvalkoUtils() {
    // invisible
  }

  /**
   * Scales to drawable paths of the {@link WritableCharacter} as separate paths corresponding the the {@link Step}s.
   */
  public static List<Path> scaleWritableCharacterStepPaths(@NonNull WritableCharacter character, @NonNull Matrix scaleMatrix) {
    List<Path> scaledPaths = new LinkedList<>();
    for (Step step : character.getSteps()) {
      Path scaledPath = step.getDrawablePath();
      scaledPath.transform(scaleMatrix);
    }
    return scaledPaths;
  }
}
