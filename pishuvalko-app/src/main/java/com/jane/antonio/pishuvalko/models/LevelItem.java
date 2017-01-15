package com.jane.antonio.pishuvalko.models;

import android.support.annotation.Nullable;

public class LevelItem {
  private final WritableCharacter writableCharacter;
  private final Boolean locked;
  private final Boolean solved;
  @Nullable
  private final Boolean approvedFromParent;

  public LevelItem(WritableCharacter writableCharacter, Boolean solved, @Nullable Boolean approvedFromParent) {
    this.writableCharacter = writableCharacter;
    this.solved = solved;
    this.approvedFromParent = approvedFromParent;
    locked = false;
  }

  public WritableCharacter getWritableCharacter() {
    return writableCharacter;
  }

  public Boolean isLocked() {
    return locked;
  }

  public Boolean isSolved() {
    return solved;
  }

  @Nullable
  public Boolean isApprovedFromParent() {
    return approvedFromParent;
  }
}
