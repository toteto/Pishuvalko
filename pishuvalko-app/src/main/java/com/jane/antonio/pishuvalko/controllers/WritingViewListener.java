package com.jane.antonio.pishuvalko.controllers;

import android.graphics.PointF;
import android.support.annotation.Nullable;

import com.jane.antonio.pishuvalko.models.Line;
import com.jane.antonio.pishuvalko.models.Segment;

import java.util.List;

/**
 * Interface that provides the necessary methods for listening for writing updates made by the user.
 */
public interface WritingViewListener {
  /**
   * Method that will be called when the user starts writing/drawing on the view.
   *
   * @param point the point that the user started writing from. Should be in the same scale as the points in the model.
   * @return null if the given point is invalid for the listener.
   */
  @Nullable
  PointF onStartedWriting(PointF point);

  /**
   * Method that will be called when the user is writing on the screen. Note: It will be called very frequently so no
   * processor heavy operations allowed.
   *
   * @param line the current point that the user has moved to. Note: Needs to be in the scale of current character.
   * @return list of approximated segments that should be displayed on the view. Note: The segments are in the scale of
   * the current character.
   */
  List<Segment> onWroteSegment(Line line);

  /** Method that will be called once the user ends drawing on the view. */
  boolean onEndedWriting();
}
