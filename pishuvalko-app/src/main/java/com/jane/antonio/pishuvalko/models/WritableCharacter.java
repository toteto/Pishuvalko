package com.jane.antonio.pishuvalko.models;

import java.util.List;

/**
 * Model that describes the character that will be written by the user.
 */
public class WritableCharacter {
  private final String name;
  private final List<Segment> segments;
  private final float height;
  private final float width;

  public WritableCharacter(String name, List<Segment> segments, float height, float width) {
    this.name = name;
    this.segments = segments;
    this.height = height;
    this.width = width;
  }

  public String getName() {
    return name;
  }

  public List<Segment> getSegments() {
    return segments;
  }

  public float getHeight() {
    return height;
  }

  public float getWidth() {
    return width;
  }
}
