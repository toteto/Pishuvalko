package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;

/** Segment that should represent one part of the {@link WritableCharacter}. */
public abstract class Segment {
  private final Path path;

  public Segment(Path path) {
    this.path = path;
  }

  public Path getPath() {
    return path;
  }
}
