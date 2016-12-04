package com.jane.antonio.pishuvalko.models;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.LinkedList;
import java.util.List;
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

  private List<Segment> succesfulyDrawnSegments;
  private Segment currentDrawnSegment;

  public GameState(WritableCharacter currentCharacter) {
    this.currentCharacter = currentCharacter;

    stepsIterator = this.currentCharacter.getSteps().listIterator();
    currentStep = stepsIterator.next();

    segmentsIterator = this.currentStep.getSegments().listIterator();
    currentsSegment = segmentsIterator.next();

    succesfulyDrawnSegments = new LinkedList<>();
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
   * Method that handles new point entries.
   *
   * @param line the new line entry made by the user.
   * @return the segment that has been formed by this and past entries
   */
  @CompleteType
  public int computeDrawnLine(@NonNull Line line) {
    if (currentDrawnSegment == null) {
      // this is the first line that the user is drawing
      if (currentsSegment instanceof Line) {
        currentDrawnSegment = line;
      } else if (currentsSegment instanceof Arc) {
        // // TODO: 03.12.2016 define arc
      }
    } else {
      // there has been a previously drawn segment
      if (currentsSegment instanceof Line) {
        Line newSegment = ((Line) currentDrawnSegment).mergeSegments(line, 0.1f);
        if (newSegment != null) {
          currentDrawnSegment = newSegment;
          return completeSegment(newSegment);
        }
      }
    }
    return CT_FAILED;
  }

  /**
   * Try to complete the current segment.
   *
   * @param segment the segment that has been drawn. This will be compared with the current segment in the state.
   * @return the validity and completion level of the drawn segment.
   */
  @CompleteType
  public int completeSegment(@NonNull Segment segment) {
    if (currentsSegment.approximatelyEquals(segment)) {
      if (segmentsIterator.hasNext()) {
        currentsSegment = segmentsIterator.next();
        succesfulyDrawnSegments.add(currentsSegment);
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

  public List<Segment> getSuccessfullyDrawnSegments() {
    return succesfulyDrawnSegments;
  }
}
