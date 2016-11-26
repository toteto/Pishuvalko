package com.jane.antonio.pishuvalko.models;

import java.util.ListIterator;

public class GameState {
  enum COMPLETION_RETURN_TYPE {
    FAILED,
    SEGMENT_COMPLETED,
    STEP_COMPLETED,
    CHARACTER_COMPLETED
  }

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

  public COMPLETION_RETURN_TYPE completeSegment(Segment segment) {
    if (currentsSegment.approximatelyEquals(segment)) {
      if (segmentsIterator.hasNext()) {
        currentsSegment = segmentsIterator.next();
        return COMPLETION_RETURN_TYPE.SEGMENT_COMPLETED;
      } else {
        return completeStep();
      }
    } else {
      return COMPLETION_RETURN_TYPE.FAILED;
    }
  }

  private COMPLETION_RETURN_TYPE completeStep() {
    if (stepsIterator.hasNext()) {
      currentStep = stepsIterator.next();
      return COMPLETION_RETURN_TYPE.STEP_COMPLETED;
    } else {
      return COMPLETION_RETURN_TYPE.CHARACTER_COMPLETED;
    }
  }
}
