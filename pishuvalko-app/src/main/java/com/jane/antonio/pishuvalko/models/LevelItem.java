package com.jane.antonio.pishuvalko.models;

public class LevelItem {
  private final WritableCharacter writableCharacter;
  private final Boolean locked;
  private final Boolean solved;
  @ISolutionStorage.SolutionState
  private final int solutionState;

  public LevelItem(WritableCharacter writableCharacter, Boolean solved, @ISolutionStorage.SolutionState int solutionState) {
    this.writableCharacter = writableCharacter;
    this.solved = solved;
    this.solutionState = solutionState;
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

  @ISolutionStorage.SolutionState
  public int getSolutionState() {
    return solutionState;
  }
}
