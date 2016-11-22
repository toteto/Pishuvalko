package com.jane.antonio.pishuvalko.models;


import android.graphics.Path;
import android.graphics.PointF;

/**
 * A {@link Segment} implementation that represents a straight line.
 */
public class Line extends Segment {
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
}
