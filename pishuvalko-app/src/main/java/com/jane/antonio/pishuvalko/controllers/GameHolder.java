package com.jane.antonio.pishuvalko.controllers;

import android.graphics.PointF;
import android.support.annotation.NonNull;

import com.jane.antonio.pishuvalko.models.GameState;
import com.jane.antonio.pishuvalko.models.Segment;
import com.jane.antonio.pishuvalko.utils.CharacterFactory;
import com.jane.antonio.pishuvalko.utils.PishuvalkoUtils;
import com.jane.antonio.pishuvalko.views.WritingView;

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
  public boolean onWroteSegment(Segment segment) {
    return false;
  }

  @Override
  public boolean onEndedWriting() {
    return false;
  }
}
