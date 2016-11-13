package com.jane.antonio.pishuvalko.utils;

import com.jane.antonio.pishuvalko.models.Line;
import com.jane.antonio.pishuvalko.models.Segment;
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
    List<Segment> segments = new ArrayList<>();
    segments.add(new Line(0f, 2f, 1f, 0f));
    segments.add(new Line(1f, 0f, 2f, 2f));
    segments.add(new Line(0.5f, 1f, 1.5f, 1f));
    return new WritableCharacter("A", segments, 2, 2);
  }
}
