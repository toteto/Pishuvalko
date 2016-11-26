package com.jane.antonio.pishuvalko.utils;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.NonNull;

import com.jane.antonio.pishuvalko.models.Line;
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
  public static List<Path> scaleWritableCharacterStepPaths(@NonNull WritableCharacter character,
    @NonNull Matrix scaleMatrix) {
    List<Path> scaledPaths = new LinkedList<>();
    for (Step step : character.getSteps()) {
      Path scaledPath = step.getDrawablePath();
      scaledPath.transform(scaleMatrix);
      scaledPaths.add(scaledPath);
    }
    return scaledPaths;
  }

  public static double calculateDistance(@NonNull PointF first, @NonNull PointF second) {
    return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
  }
}
