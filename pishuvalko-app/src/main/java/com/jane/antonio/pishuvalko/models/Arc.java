package com.jane.antonio.pishuvalko.models;

import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.NonNull;

/**
 * A {@link Segment} that represents a curved line - arc.
 */
public class Arc extends Segment<Arc> {
  private static final long serialVersionUID = 5399925238008475610L;
  private final PointF secondPoint;
  private final float swipeDegrees;
  private float startAngle;

  public Arc(float left, float top, float right, float bottom, float startAngle, float swipeDegrees) {
    super(new PointF(left, top));
    secondPoint = new PointF(right, bottom);
    this.startAngle = startAngle;
    this.swipeDegrees = swipeDegrees;
  }

  @Override
  protected Path makeDrawablePath() {
    Path path = new Path();
    path.addArc(new RectF(getFirstPoint().x, getFirstPoint().y, getSecondPoint().x, getSecondPoint().y), startAngle,
      swipeDegrees);
    return path;
  }

  @Override
  public Arc mergeSegments(@NonNull Line other, float tolerance) {
    return null;
  }

  @Override
  public boolean approximatelyEquals(Arc other) {
    return false;
  }

  @Override
  public Arc scaleSegment(@NonNull Matrix scaleMatrix, boolean counter) {
    // TODO: 01.12.2016
    return null;
  }

  /** The points is storing the right and bottom bounds. */
  public PointF getSecondPoint() {
    return secondPoint;
  }

  public float getSwipeDegrees() {
    return swipeDegrees;
  }
}
