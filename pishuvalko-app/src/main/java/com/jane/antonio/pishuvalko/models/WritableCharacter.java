package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;

import java.util.List;

/**
 * Model that describes the character that will be written by the user.
 */
public class WritableCharacter {
  private final String name;
  private final List<Path> segments;

  public WritableCharacter(String name, List<Path> segments) {
    this.name = name;
    this.segments = segments;
  }

  public String getName() {
    return name;
  }

  public List<Path> getSegments() {
    return segments;
  }
}
