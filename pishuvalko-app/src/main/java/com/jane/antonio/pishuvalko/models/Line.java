package com.jane.antonio.pishuvalko.models;


import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;

import com.jane.antonio.pishuvalko.utils.PishuvalkoUtils;

/**
 * A {@link Segment} implementation that represents a straight line.
 */
public class Line extends Segment {
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
  public Segment mergeSegments(Segment other, float tolerance) {
    if (other instanceof Line) {
      Line otherLine = ((Line) other);
      if (secondPoint.equals(otherLine.getFirstPoint())) {
        if (getFirstPoint().equals(getSecondPoint())) {
          // this-> is not Line, it is point
          return other;
        }
        if (other.getFirstPoint().equals(((Line) other).getSecondPoint())) {
          // other is not Line, it is a point
          return this;
        }

        // calculate the angle of this-> line
        float delta_x = getSecondPoint().x - getFirstPoint().x;
        float delta_y = getFirstPoint().y - getSecondPoint().y;
        double thisAngle = Math.toDegrees(Math.atan2(delta_y, delta_x));

        // calculate the angle of otherLine
        delta_x = otherLine.getSecondPoint().x - otherLine.getFirstPoint().x;
        delta_y = otherLine.getFirstPoint().y - otherLine.getSecondPoint().y;
        double otherAngle = Math.toDegrees(Math.atan2(delta_y, delta_x));

        double diff = Math.abs(thisAngle - otherAngle) / 360;
        if (diff < tolerance) {
          return new Line(getFirstPoint(), otherLine.getSecondPoint());
        } else {
          Log.e(LOG_TAG,
            "mergeSegments: Lines are not within the tolerance to be merged. Allowed: " + tolerance + " Calculated: "
              + diff);
        }
      } else {
        Log.e(LOG_TAG, "mergeSegments: Lines are not connected.");
      }
    } else {
      Log.e(LOG_TAG, "mergeSegments: Other is not instance of Line");
    }
    return null;
  }

  @Override
  public boolean approximatelyEquals(Segment other) {
    return false;
  }

  @Override
  public Segment scaleSegment(Matrix scaleMatrix, boolean counter) {
    PointF scaledFirst = PishuvalkoUtils.scalePoint(getFirstPoint(), scaleMatrix, counter);
    PointF scaledSecond = PishuvalkoUtils.scalePoint(getSecondPoint(), scaleMatrix, counter);
    return new Line(scaledFirst, scaledSecond);
  }
}
