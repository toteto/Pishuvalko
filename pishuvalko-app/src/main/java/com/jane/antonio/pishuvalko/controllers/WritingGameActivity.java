package com.jane.antonio.pishuvalko.controllers;

import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.utils.CharacterFactory;
import com.jane.antonio.pishuvalko.views.WritingView;

/** Activity that will be responsible for controlling and displaying of the writing game. */
public class WritingGameActivity extends AppCompatActivity {

  public static final String LETTER_SELECT_KEY = "letter_select";

  private WritingView writingView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.writing_game_activity);

    writingView = (WritingView) findViewById(R.id.writingView);
    writingView.setCurrentCharacter(CharacterFactory.getP());
  }

  private final OnWritingChangeListener writingChangeListener = new OnWritingChangeListener() {
    @Override
    public void onWritingChange(WritableCharacter character, Path pathDrawn) {

    }
  };
}
