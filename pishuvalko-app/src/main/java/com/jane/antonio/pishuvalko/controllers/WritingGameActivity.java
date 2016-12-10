package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.WritingGameInterface;
import com.jane.antonio.pishuvalko.models.CharacterFetcher;
import com.jane.antonio.pishuvalko.models.SolutionStorage;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.views.WritingImageView;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;

/**
 * Activity that will be responsible for controlling and displaying of the writing game.
 */
public class WritingGameActivity extends AppCompatActivity implements View.OnClickListener, WritingGameInterface {
  /** Key used for storing the character that will be displayed. */
  private static final String GAME_TYPE_KEY = "game_type";
  private static final String CHARACTER_INDEX_KEY = "index";
  private WritingImageView writingImageView;
  private View btnClose;
  private View btnErase;
  private View btnConfirm;
  private View btnColors;

  private GameController gameController;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gameController = new GameController(this, new SolutionStorage(this));
    setContentView(R.layout.writing_game_activity);
    writingImageView = (WritingImageView) findViewById(R.id.writingImageView);
    btnClose = findViewById(R.id.btn_close);
    btnErase = findViewById(R.id.btnErase);
    btnConfirm = findViewById(R.id.btnConfirm);
    btnColors = findViewById(R.id.btnColors);

    btnClose.setOnClickListener(this);
    btnConfirm.setOnClickListener(this);
    btnColors.setOnClickListener(this);
    btnErase.setOnClickListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
  }

  @Override
  protected void onStart() {
    super.onStart();
    List<WritableCharacter> list = CharacterFetcher.getCharacters(this, readGameType(getIntent()));
    int index = readCharacterIndex(getIntent());
    gameController.onStart(writingImageView, list, index);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameController.onStop();
  }

  @Override
  public void onClick(View view) {
    if (btnConfirm.equals(view)) {
      gameController.onConfirm();
    } else if (btnColors.equals(view)) {
      showColorSelector();
    } else if (btnClose.equals(view)) {
      gameController.onClose();
    } else if (btnErase.equals(view)) {
      gameController.onErase();
    }
  }

  private void showColorSelector() {
    Random rnd = new SecureRandom();
    int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
    gameController.onColorSelected(color);
  }

  @Override
  public void setEraseEnabled(boolean toEnable) {
    btnErase.setEnabled(toEnable);
  }

  @Override
  public void exit() {
    finish();
  }

  /**
   * Reads the {@link com.jane.antonio.pishuvalko.controllers.LevelSelectionActivity.GameType} from the provided
   * intent.
   */
  @SuppressWarnings("ResourceType")
  @LevelSelectionActivity.GameType
  private static int readGameType(@NonNull Intent intent) {
    return intent.getIntExtra(GAME_TYPE_KEY, LevelSelectionActivity.BIG_LETTERS);
  }

  /** Reads the char index from the provided intent. */
  private static int readCharacterIndex(@NonNull Intent intent) {
    return intent.getIntExtra(CHARACTER_INDEX_KEY, 0);
  }

  /**
   * Get the starting intent for this activity with all needed parameters.
   *
   * @param gameType the type of the game that the user has selected
   * @param index the character index of the gameType list user has selected
   * @return intent that is ready to start this activity
   */
  public static Intent getStartingIntent(@NonNull Context context, @LevelSelectionActivity.GameType int gameType,
    int index) {
    final Intent intent = new Intent(context, WritingGameActivity.class);
    intent.putExtra(GAME_TYPE_KEY, gameType);
    intent.putExtra(CHARACTER_INDEX_KEY, index);
    return intent;
  }
}
