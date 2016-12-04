package com.jane.antonio.pishuvalko.utils;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.support.annotation.NonNull;

public class PishuvalkoUtils {
  private PishuvalkoUtils() {
    // invisible
  }

  public static double calculateDistance(@NonNull PointF first, @NonNull PointF second) {
    return Math.sqrt(Math.pow(first.x - second.x, 2) + Math.pow(first.y - second.y, 2));
  }

  public static PointF calculateHorizontalAndVerticalDistance(@NonNull PointF first, @NonNull PointF second) {
    return new PointF(Math.abs(first.x - second.x), Math.abs(first.y - second.y));
  }

  public static PointF scalePoint(@NonNull PointF point, @NonNull Matrix scaleMatrix, boolean counter) {
    float[] scaleValues = new float[9];
    scaleMatrix.getValues(scaleValues);
    PointF scaledPoint = new PointF();
    if (counter) {
      scaledPoint.set(point.x / scaleValues[0], point.y / scaleValues[4]);
    } else {
      scaledPoint.set(point.x * scaleValues[0], point.y * scaleValues[4]);
    }
    return scaledPoint;
  }
}
