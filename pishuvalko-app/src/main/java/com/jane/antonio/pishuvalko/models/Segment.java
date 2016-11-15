package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;

import java.io.Serializable;

/**
 * Segment that should represent one part of the {@link WritableCharacter}. It has option to attach other segments to it
 * in order to make more complex segments.
 */
public abstract class Segment implements Serializable{
  private static final long serialVersionUID = 2702346906257766473L;

  private final Path path;
  private Segment connectedSegment;

  public Segment(Path path) {
    this.path = path;
  }

  public Path getPath() {
    return path;
  }

  public Segment getConnectedSegment() {
    return connectedSegment;
  }

  public void setConnectedSegment(Segment connectedSegment) {
    this.connectedSegment = connectedSegment;
  }
}
