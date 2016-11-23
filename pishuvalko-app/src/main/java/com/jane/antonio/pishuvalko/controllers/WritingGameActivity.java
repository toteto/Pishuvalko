package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.utils.CharacterFactory;
import com.jane.antonio.pishuvalko.views.WritingView;

/**
 * Activity that will be responsible for controlling and displaying of the writing game.
 */
public class WritingGameActivity extends AppCompatActivity {

  protected static final String WRITABLE_CHARACTER_INTENT_KEY = "letter_select";

  private WritingView writingView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.writing_game_activity);

    writingView = (WritingView) findViewById(R.id.writingView);
    writingView.setCurrentCharacter(CharacterFactory.getF());
  }

  private final OnWritingChangeListener writingChangeListener = new OnWritingChangeListener() {
    @Override
    public void onWritingChange(WritableCharacter character, Path pathDrawn) {

    }
  };

  public static Intent getStartingIntent(Context context, WritableCharacter writableCharacter) {
    Intent intent = new Intent(context, WritingGameActivity.class);
    intent.putExtra(WRITABLE_CHARACTER_INTENT_KEY, writableCharacter);
    return intent;
  }
}
