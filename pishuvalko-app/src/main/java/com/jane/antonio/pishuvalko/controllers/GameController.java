package com.jane.antonio.pishuvalko.controllers;

import android.support.annotation.Nullable;

import com.jane.antonio.pishuvalko.WritingGameInterface;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.LinkedList;

public class GameController {
  private final WritingGameInterface gameInterface;

  public GameController(WritingGameInterface gameInterface) {
    this.gameInterface = gameInterface;
  }


  public void onStart(@Nullable final WritableCharacter writableCharacter) {
    if (writableCharacter == null) {
      gameInterface.showCharacter(new WritableCharacter("characters/bigLetters/A.png", "A", new LinkedList<String>()));
    }
  }

  public void onStop() {

  }

  public void onNextClicked() {

  }

  public void onPreviusClicked() {

  }

  public void onCloseClicked() {

  }
}
