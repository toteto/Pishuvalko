package com.jane.antonio.pishuvalko.controllers;

import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jane.antonio.pishuvalko.models.WritableCharacter;

/** Activity that will be responsible for controlling and displaying of the writing game. */
public class WritingGameActivity extends AppCompatActivity {


  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  private final OnWritingChangeListener writingChangeListener = new OnWritingChangeListener() {
    @Override
    public void onWritingChange(WritableCharacter character, Path pathDrawn) {

    }
  };
}
