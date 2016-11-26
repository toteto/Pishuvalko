package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;
import android.graphics.PointF;

import java.io.Serializable;

/**
 * Segment that should represent one part of the {@link WritableCharacter}. It has option to attach other segments to it
 * in order to make more complex segments.
 */
public abstract class Segment implements Serializable {
  private static final long serialVersionUID = 2702346901257766473L;
  private final PointF firstPoint;
  private Path drawablePath;

  /**
   * Constructor with the first point of the segment.
   */
  public Segment(PointF firstPoint) {
    this.firstPoint = firstPoint;
  }

  public final PointF getFirstPoint() {
    return firstPoint;
  }

  /**
   * Gets the {@link Path} for the segment.
   *
   * @param lazy true if to use lazy loading. Lazy == drawing only the first time it is called.
   */
  public final Path getDrawablePath(boolean lazy) {
    if (drawablePath == null || lazy) {
      drawablePath = makeDrawablePath();
    }
    return drawablePath;
  }

  /**
   * Constructs the {@link Path} for the segment.
   */
  protected abstract Path makeDrawablePath();

  /**
   * Merges this segment with the one provided.
   *
   * @param tolerance the tolerance allowed for merging of segments
   * @param other the other segment that will merge with this->
   * @return the merged segment, or null if it is not possible to be merged.
   */
  public abstract Segment mergeSegments(Segment other, float tolerance);
}
