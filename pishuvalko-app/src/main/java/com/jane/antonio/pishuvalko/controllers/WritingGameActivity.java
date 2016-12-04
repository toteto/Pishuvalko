package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jane.antonio.pishuvalko.R;

/**
 * Activity that will be responsible for controlling and displaying of the writing game.
 */
public class WritingGameActivity extends AppCompatActivity {

  protected static final String WRITABLE_CHARACTER_INTENT_KEY = "letter_select";

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.writing_game_activity);
  }

  @Override
  protected void onStart() {
    super.onStart();
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  public static Intent getStartingIntent(Context context) {
    Intent intent = new Intent(context, WritingGameActivity.class);
    return intent;
  }
}
