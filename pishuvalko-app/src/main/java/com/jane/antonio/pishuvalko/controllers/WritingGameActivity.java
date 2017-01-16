package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.content.Intent;
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
import com.kizitonwose.colorpreference.ColorDialog;
import com.kizitonwose.colorpreference.ColorShape;

import java.lang.ref.WeakReference;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Activity that will be responsible for controlling and displaying of the writing game.
 */
public class WritingGameActivity extends AppCompatActivity
        implements View.OnClickListener, WritingGameInterface, ColorDialog.OnColorSelectedListener {
  /**
   * Key used for storing the character that will be displayed.
   */
  private static final String GAME_TYPE_KEY = "game_type";
  private static final String SELECTED_CHARACTER_KEY = "index";
  private WritingImageView writingImageView;
  private View btnClose;
  private View btnErase;
  private View btnConfirm;
  private View btnColors;
  private View gifClapping;

  private GameController gameController;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gameController = new GameController(this, new SolutionStorage(this));
    setContentView(R.layout.writing_game_activity);
    writingImageView = (WritingImageView) findViewById(R.id.writingImageView);
    btnClose = findViewById(R.id.btn_close);
    btnErase = findViewById(R.id.ivErase);
    btnConfirm = findViewById(R.id.ivConfirm);
    btnColors = findViewById(R.id.ivColors);
    gifClapping = findViewById(R.id.gifClapping);

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
    WritableCharacter character = readCharacter(getIntent());
    int index = list.indexOf(character);
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
    new ColorDialog.Builder(this).setColorShape(ColorShape.CIRCLE).setColorChoices(R.array.color_choices).show();
  }

  @Override
  public void onColorSelected(int i, String s) {
    gameController.onColorSelected(i);
  }

  @Override
  public void setEraseEnabled(boolean toEnable) {
    btnErase.setEnabled(toEnable);
  }

  @Override
  public void exit() {
    finish();
  }

  @Override
  public void showClapping(int duration) {
    gifClapping.setVisibility(View.VISIBLE);
    final WeakReference<View> reference = new WeakReference<>(gifClapping);
    gifClapping.postDelayed(new Runnable() {
      @Override
      public void run() {
        if (reference.get() != null) {
          reference.get().setVisibility(View.GONE);
        }
      }
    }, TimeUnit.SECONDS.toMillis(duration));
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

  /**
   * Reads the char index from the provided intent.
   */
  private static WritableCharacter readCharacter(@NonNull Intent intent) {
    return (WritableCharacter) intent.getSerializableExtra(SELECTED_CHARACTER_KEY);
  }

  /**
   * Get the starting intent for this activity with all needed parameters.
   *
   * @param gameType the type of the game that the user has selected
   * @return intent that is ready to start this activity
   */
  public static Intent getStartingIntent(@NonNull Context context, @LevelSelectionActivity.GameType int gameType,
                                         @NonNull WritableCharacter character) {
    final Intent intent = new Intent(context, WritingGameActivity.class);
    intent.putExtra(GAME_TYPE_KEY, gameType);
    intent.putExtra(SELECTED_CHARACTER_KEY, character);
    return intent;
  }
}
