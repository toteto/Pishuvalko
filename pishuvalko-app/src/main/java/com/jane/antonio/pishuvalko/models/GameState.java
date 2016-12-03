package com.jane.antonio.pishuvalko.models;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ListIterator;

public class GameState {
  @Retention(RetentionPolicy.SOURCE)


  /**
   * Values that will bbe used as feedback for the completion of the current state.
   * */
  @IntDef({CT_FAILED, CT_SEGMENT_COMPLETED, CT_STEP_COMPLETED, CT_CHARACTER_COMPLETED})
  public @interface CompleteType {
  }

  /** Value representing that the writing of the character has failed, or it is invalid. */
  public static final int CT_FAILED = -1;
  /** Value representing that the current segment has been successfully completed. */
  public static final int CT_SEGMENT_COMPLETED = 0;
  /** Value representing that the current step has been successfully completed. */
  public static final int CT_STEP_COMPLETED = 1;
  /** Value representing that the current character has been successfully completed. */
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

  /**
   * Try to complete the current segment.
   *
   * @param segment the segment that has been drawn. This will be compared with the current segment in the state.
   * @return the validity and completion level of the drawn segment.
   */
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
