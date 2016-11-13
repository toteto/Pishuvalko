package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;
import android.graphics.RectF;

/** A {@link Segment} that represents a curved line - arc. */
public class Arc extends Segment {
  private final float x1;
  private final float y1;
  private final float x2;
  private final float y2;
  private final float fromAngle;
  private final float angleSweep;

  public Arc(float x1, float y1, float x2, float y2, Side direction) {
    super(new Path());
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
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
  }

  public enum Side {
    LEFT,
    RIGHT
  }
}
