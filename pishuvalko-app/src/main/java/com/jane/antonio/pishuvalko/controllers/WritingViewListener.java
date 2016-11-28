package com.jane.antonio.pishuvalko.controllers;

import com.jane.antonio.pishuvalko.models.Segment;

/**
 * Interface that provides the necessary methods for listening for writing updates made by the user.
 */
public interface WritingViewListener {
  /** Method that will be called when the user starts writing/drawing on the view. */
  void onStartedWriting();

  /**
   * Method that will be called when the user is writing on the screen. Note: It will be called very frequently so no
   * processor heavy operations allowed.
   */
  void onWroteSegment(Segment segment);

  /** Method that will be called once the user ends drawing on the view. */
  void onEndedWriting();
}
