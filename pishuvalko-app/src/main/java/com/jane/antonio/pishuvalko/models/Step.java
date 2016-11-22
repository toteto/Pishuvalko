package com.jane.antonio.pishuvalko.models;

import android.graphics.Path;

import java.util.Arrays;
import java.util.List;

public class Step {
  private final List<Segment> segments;

  public Step(Segment... segments) {
    this.segments = Arrays.asList(segments);
  }

  public List<Segment> getSegments() {
    return segments;
  }

  public Path getDrawablePath() {
    Path path = new Path();
    for (Segment segment : segments) {
      path.addPath(segment.getDrawablePath(true));
    }
    return path;
  }
}
