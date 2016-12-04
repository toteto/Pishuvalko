package com.jane.antonio.pishuvalko.controllers;

import android.graphics.PointF;
import android.support.annotation.NonNull;

import com.jane.antonio.pishuvalko.models.GameState;
import com.jane.antonio.pishuvalko.models.Line;
import com.jane.antonio.pishuvalko.models.Segment;
import com.jane.antonio.pishuvalko.utils.CharacterFactory;
import com.jane.antonio.pishuvalko.utils.PishuvalkoUtils;
import com.jane.antonio.pishuvalko.views.WritingView;

import java.util.ArrayList;
import java.util.List;

/** A game holder class that is responsible for storing and managing the current game state. */
public class GameHolder implements WritingViewListener {
  private GameState currentGameState;
  private final WritingView writingView;

  public GameHolder(@NonNull WritingView writingView) {
    currentGameState = new GameState(CharacterFactory.getA());
    this.writingView = writingView;
    this.writingView.setWritingViewListener(this);
  }

  /** Signals the game holder that the views ({@link WritingView}) have been attached to an activity. */
  public void onViewsAttached() {
    writingView.setCurrentCharacter(currentGameState.getCurrentCharacter());
  }

  /** Signals the game holder that the views ({@link WritingView}) have been detached from an activity. */
  public void onViewsDetached() {

  }

  @Override
  public PointF onStartedWriting(PointF point) {
    // distance from the first point of the currently-drawing-segment to the point where the user started drawing
    PointF distance = PishuvalkoUtils.calculateHorizontalAndVerticalDistance(
      currentGameState.getCurrentsSegment().getFirstPoint(), point);
    if (currentGameState.getCurrentCharacter().getWidth() * .20 > distance.x
      && currentGameState.getCurrentCharacter().getHeight() * .20 > distance.y) {
      return currentGameState.getCurrentsSegment().getFirstPoint();
    }
    return null;
  }

  @Override
  public List<Segment> onWroteSegment(Line line) {
    int completeType = currentGameState.computeDrawnLine(line);
    switch (completeType) {
      case GameState.CT_FAILED:
        return new ArrayList<>();
      case GameState.CT_SEGMENT_COMPLETED:
        return currentGameState.getSuccessfullyDrawnSegments();
      case GameState.CT_STEP_COMPLETED:
      case GameState.CT_CHARACTER_COMPLETED:
        throw new RuntimeException("You should have come here. Developer: fixme!!!");
    }
    return new ArrayList<>();
  }

  @Override
  public boolean onEndedWriting() {
    return false;
  }

  /**
   * Merges the hand drawn segments stored in this.handDrawnSegments.
   *
   * @return true  if it is successfully merged.
   *//*
  private boolean mergeHandDrawnSegments() {

    if (handDrawnSegments.size() > 0) {
      ListIterator<Segment> iterator = handDrawnSegments.listIterator();
      Segment prevSegment = iterator.next();
      while (iterator.hasNext()) {
        Segment currSegment = iterator.next();
        prevSegment = prevSegment.mergeSegments(currSegment, 0.1f);
        if (prevSegment == null) {
          break;
        }
      }
      if (prevSegment != null) {
        handDrawnPath.reset();
        handDrawnPath.addPath(prevSegment.getDrawablePath(false));
        return true;
      }
    }
    return false;
  }*/
}
