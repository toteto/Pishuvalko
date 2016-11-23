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
    Arc s2s2 = new Arc(0f, 0f, 1f, 1f, 270, 180);
    Line s2s3 = new Line(new PointF(0.5f, 1f), new PointF(0f, 1f));
    steps.add(new Step(s2s1, s2s2, s2s3));
    return new WritableCharacter("P", steps, 2, 2);
  }

  public static WritableCharacter getF() {
    List<Step> steps = new ArrayList<>();
    steps.add(new Step(new Line(new PointF(2f, 0f), new PointF(2f, 4f))));

    Line s2s1 = new Line(new PointF(2f, 1f), new PointF(1f, 1f));
    Arc s2s2 = new Arc(0f, 1f, 2f, 3f, 270, -180);
    Line s2s3 = new Line(new PointF(1f, 3f), new PointF(2f, 3f));
    steps.add(new Step(s2s1, s2s2, s2s3));

    Line s3s1 = new Line(new PointF(2f, 1f), new PointF(3f, 1f));
    Arc s3s2 = new Arc(2f, 1f, 4f, 3f, 270, 180);
    Line s3s3 = new Line(new PointF(3f, 3f), new PointF(2f, 3f));
    steps.add(new Step(s3s1, s3s2, s3s3));

    return new WritableCharacter("P", steps, 4f, 4f);
  }
}
