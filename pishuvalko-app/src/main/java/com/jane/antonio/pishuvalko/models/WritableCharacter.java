package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;

import java.util.List;

/**
 * Model that describes the character that will be written by the user.
 */
public class WritableCharacter {
  private final String name;
  private final List<Path> segments;
  private final float height;
  private final float width;

  public WritableCharacter(String name, List<Path> segments, float height, float width) {
    this.name = name;
    this.segments = segments;
    this.height = height;
    this.width = width;
  }

  public String getName() {
    return name;
  }

  public List<Path> getSegments() {
    return segments;
  }

  public float getHeight() {
    return height;
  }

  public float getWidth() {
    return width;
  }
}
