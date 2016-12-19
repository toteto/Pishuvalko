package com.jane.antonio.pishuvalko.models;

public class LevelItem {
  private final WritableCharacter writableCharacter;
  private final boolean locked;
  private final boolean solved;
  private final boolean approvedFromParent;

  public LevelItem(WritableCharacter writableCharacter, boolean solved, boolean approvedFromParent) {
    this.writableCharacter = writableCharacter;
    this.solved = solved;
    this.approvedFromParent = approvedFromParent;
    locked = false;
  }

  public WritableCharacter getWritableCharacter() {
    return writableCharacter;
  }

  public boolean isLocked() {
    return locked;
  }

  public boolean isSolved() {
    return solved;
  }

  public boolean isApprovedFromParent() {
    return approvedFromParent;
  }
}
