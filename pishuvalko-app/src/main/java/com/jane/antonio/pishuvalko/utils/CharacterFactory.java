package com.jane.antonio.pishuvalko.utils;

import android.graphics.Path;

import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.ArrayList;
import java.util.List;

/**
 * Temporary class used for testing purpuses. It should generate
 * {@link com.jane.antonio.pishuvalko.models.WritableCharacter}.
 */
public class CharacterFactory {

  private CharacterFactory() {
    // factory
  }

  public static WritableCharacter getA() {
    List<Path> segments = new ArrayList<>();
    int scale = 1;
    Path segment = new Path();
    segment.moveTo(0, 0);
    segment.lineTo(scale * 1, scale * 2);
    segments.add(segment);

    segment = new Path();
    segment.moveTo(scale * 1, scale * 2);
    segment.lineTo(scale * 2, 0);
    segments.add(segment);

    segment = new Path();
    segment.moveTo((float) (scale * 0.5), scale * 1);
    segment.lineTo((float) (scale * 1.5), scale * 1);
    segments.add(segment);

    return new WritableCharacter("A", segments, 2, 2);
  }
}
