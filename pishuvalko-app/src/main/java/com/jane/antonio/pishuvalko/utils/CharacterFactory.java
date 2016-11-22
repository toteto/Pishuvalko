package com.jane.antonio.pishuvalko.utils;

import android.graphics.PointF;

import com.jane.antonio.pishuvalko.models.Arc;
import com.jane.antonio.pishuvalko.models.Line;
import com.jane.antonio.pishuvalko.models.Step;
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
    List<Step> steps = new ArrayList<>();
    steps.add(new Step(new Line(new PointF(0f, 2f), new PointF(1f, 0f))));
    steps.add(new Step(new Line(new PointF(1f, 0f), new PointF(2f, 2f))));
    steps.add(new Step(new Line(new PointF(0.5f, 1f), new PointF(1.5f, 1f))));
    return new WritableCharacter("A", steps, 2, 2);
  }

  public static WritableCharacter getP() {
    List<Step> steps = new ArrayList<>();
    steps.add(new Step(new Line(new PointF(0f, 2f), new PointF(0f, 0f))));

    Line s2s1 = new Line(new PointF(0f, 0f), new PointF(0.5f, 0f));
    Arc s2s2 = new Arc(new PointF(0.5f, 0f), new PointF(0.5f, 1f), Arc.ARC_DIRECTION.CLOCKWISE, 180);
    Line s2s3 = new Line(new PointF(0.5f, 1f), new PointF(0f, 1f));
    steps.add(new Step(s2s1, s2s2, s2s3));
    return new WritableCharacter("P", steps, 2, 2);
  }
}
