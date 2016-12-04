package com.jane.antonio.pishuvalko.models;


import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jane.antonio.pishuvalko.utils.PishuvalkoUtils;

/**
 * A {@link Segment} implementation that represents a straight line.
 */
public class Line extends Segment<Line> {
  private static final String LOG_TAG = Line.class.getSimpleName();
  private static final long serialVersionUID = 858575805197496892L;

  private final PointF secondPoint;

  public Line(PointF firstPoint, PointF secondPoint) {
    super(firstPoint);
    this.secondPoint = secondPoint;
  }

  public PointF getSecondPoint() {
    return secondPoint;
  }

  @Override
  public Path makeDrawablePath() {
    Path line = new Path();
    line.moveTo(getFirstPoint().x, getFirstPoint().y);
    line.lineTo(secondPoint.x, secondPoint.y);
    return line;
  }

  @Override
  public Line mergeSegments(@NonNull Line other, float tolerance) {
    if (secondPoint.equals(other.getFirstPoint())) {
      if (getFirstPoint().equals(getSecondPoint())) {
        // this-> is not Line, it is point
        return other;
      }
      if (other.getFirstPoint().equals(other.getSecondPoint())) {
        // other is not Line, it is a point
        return this;
      }

      // calculate the angle of this-> line
      float deltaX = getSecondPoint().x - getFirstPoint().x;
      float deltaY = getFirstPoint().y - getSecondPoint().y;
      double thisAngle = Math.toDegrees(Math.atan2(deltaY, deltaX));

      // calculate the angle of otherLine
      deltaX = other.getSecondPoint().x - other.getFirstPoint().x;
      deltaY = other.getFirstPoint().y - other.getSecondPoint().y;
      double otherAngle = Math.toDegrees(Math.atan2(deltaY, deltaX));

      double diff = Math.abs(thisAngle - otherAngle) / 360;
      if (diff < tolerance) {
        return new Line(getFirstPoint(), other.getSecondPoint());
      } else {
        Log.e(LOG_TAG,
          "mergeSegments: Lines are not within the tolerance to be merged. Allowed: " + tolerance + " Calculated: "
            + diff);
      }
    } else {
      Log.e(LOG_TAG, "mergeSegments: Lines are not connected.");
    }
  return null;
  }

  @Override
  public boolean approximatelyEquals(Line other) {
    return false;
  }

  @Override
  public Line scaleSegment(@NonNull Matrix scaleMatrix, boolean counter) {
    PointF scaledFirst = PishuvalkoUtils.scalePoint(getFirstPoint(), scaleMatrix, counter);
    PointF scaledSecond = PishuvalkoUtils.scalePoint(getSecondPoint(), scaleMatrix, counter);
    return new Line(scaledFirst, scaledSecond);
  }
}
