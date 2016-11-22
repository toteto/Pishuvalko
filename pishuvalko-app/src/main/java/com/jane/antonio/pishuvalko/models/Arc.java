package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * A {@link Segment} that represents a curved line - arc.
 */
public class Arc extends Segment {
  private static final long serialVersionUID = 5399925238008475610L;
  private final PointF secondPoint;
  private final ARC_DIRECTION direction;
  private final float swipeDegrees;


  public enum ARC_DIRECTION {
    CLOCKWISE,
    COUNTER_CLOCKWISE
  }

  public Arc(PointF firstPoint, PointF secondPoint, ARC_DIRECTION direction, float swipeDegrees) {
    super(firstPoint);
    this.secondPoint = secondPoint;
    this.direction = direction;
    this.swipeDegrees = swipeDegrees;
  }

  @Override
  protected Path makeDrawablePath() {
    return null;
  }

  public PointF getSecondPoint() {
    return secondPoint;
  }

  public ARC_DIRECTION getDirection() {
    return direction;
  }

  public float getSwipeDegrees() {
    return swipeDegrees;
  }

  /*public void Arca(float x1, float y1, float x2, float y2, Side direction) {
    float centerX = x1 + ((x2 - x1) / 2);
    float centerY = y1 + ((y2 - y1) / 2);

    double xLen = (x2 - x1);
    double yLen = (y2 - y1);
    float radius = (float) (Math.sqrt(xLen * xLen + yLen * yLen) / 2);

    RectF oval = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);

    double radStartAngle = 0;
    if (direction == Side.LEFT) {
      radStartAngle = Math.atan2(y1 - centerY, x1 - centerX);
    } else if (direction == Side.RIGHT) {
      radStartAngle = Math.atan2(y2 - centerY, x2 - centerX);
    }
    fromAngle = (float) Math.toDegrees(radStartAngle);
    angleSweep = 180;

    getPath().addArc(oval, fromAngle, angleSweep);
  }*/
}
