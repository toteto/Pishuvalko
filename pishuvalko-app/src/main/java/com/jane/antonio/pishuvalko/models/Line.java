package com.jane.antonio.pishuvalko.models;


import android.graphics.Path;

/** A {@link Segment} implementation that represents a straight line. */
public class Line extends Segment {

  private final float x1;
  private final float y1;
  private final float x2;
  private final float y2;

  public Line(float x1, float y1, float x2, float y2) {
    super(new Path());
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;
    getPath().moveTo(x1, y1);
    getPath().lineTo(x2, y2);
  }


  public float getX1() {
    return x1;
  }

  public float getY1() {
    return y1;
  }

  public float getX2() {
    return x2;
  }

  public float getY2() {
    return y2;
  }
}
