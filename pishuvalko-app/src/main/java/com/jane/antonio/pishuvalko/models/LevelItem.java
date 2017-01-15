package com.jane.antonio.pishuvalko.models;

public class LevelItem {
  private final WritableCharacter writableCharacter;
  private final Boolean locked;
  private final Boolean solved;
  private final Boolean approvedFromParent;

  public LevelItem(WritableCharacter writableCharacter, Boolean solved, Boolean approvedFromParent) {
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

  public Boolean isApprovedFromParent() {
    return approvedFromParent;
  }
}
