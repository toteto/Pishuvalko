package com.jane.antonio.pishuvalko.models;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ListIterator;

public class GameState {
  @Retention(RetentionPolicy.SOURCE)

  @IntDef({CT_FAILED, CT_SEGMENT_COMPLETED, CT_STEP_COMPLETED, CT_CHARACTER_COMPLETED})
  public @interface CompleteType {  }

  public static final int CT_FAILED = -1;
  public static final int CT_SEGMENT_COMPLETED = 0;
  public static final int CT_STEP_COMPLETED = 1;
  public static final int CT_CHARACTER_COMPLETED = 2;

  private final WritableCharacter currentCharacter;
  private Step currentStep;
  private Segment currentsSegment;

  private final ListIterator<Step> stepsIterator;
  private ListIterator<Segment> segmentsIterator;

  public GameState(WritableCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;

    stepsIterator = this.currentCharacter.getSteps().listIterator();
    currentStep = stepsIterator.next();

    segmentsIterator = this.currentStep.getSegments().listIterator();
    currentsSegment = segmentsIterator.next();
  }

  public WritableCharacter getCurrentCharacter() {
    return currentCharacter;
  }

  public Step getCurrentStep() {
    return currentStep;
  }

  public Segment getCurrentsSegment() {
    return currentsSegment;
  }

  @CompleteType
  public int completeSegment(Segment segment) {
    if (currentsSegment.approximatelyEquals(segment)) {
      if (segmentsIterator.hasNext()) {
        currentsSegment = segmentsIterator.next();
        return CT_SEGMENT_COMPLETED;
      } else {
        return completeStep();
      }
    } else {
      return CT_FAILED;
    }
  }

  @CompleteType
  private int completeStep() {
    if (stepsIterator.hasNext()) {
      currentStep = stepsIterator.next();
      return CT_STEP_COMPLETED;
    } else {
      return CT_CHARACTER_COMPLETED;
    }
  }
}
