package com.jane.antonio.pishuvalko.models;

import com.jane.antonio.pishuvalko.views.WritingView;

/**
 * Simple model that represents the progress update for the {@link WritableCharacter} and {@link WritingView}.
 */
public class Progress {
  private final int index;
  private final float progress;

  /**
   * @param index    the index of the path that is currently being drawn.
   * @param progress the progress of the path with the provided index.
   */
  public Progress(int index, float progress) {
    this.index = index;
    this.progress = progress;
  }

  public int getIndex() {
    return index;
  }

  public float getProgress() {
    return progress;
  }
}
