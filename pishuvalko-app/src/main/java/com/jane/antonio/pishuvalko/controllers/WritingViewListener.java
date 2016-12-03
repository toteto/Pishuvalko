package com.jane.antonio.pishuvalko.controllers;

import android.graphics.PointF;
import android.support.annotation.Nullable;

import com.jane.antonio.pishuvalko.models.Segment;

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
   */
  boolean onWroteSegment(Segment segment);

  /** Method that will be called once the user ends drawing on the view. */
  boolean onEndedWriting();
}
