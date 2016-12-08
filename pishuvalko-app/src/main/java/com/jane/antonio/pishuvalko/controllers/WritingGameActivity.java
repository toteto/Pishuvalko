package com.jane.antonio.pishuvalko.controllers;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.WritingGameInterface;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.views.WritingImageView;

/**
 * Activity that will be responsible for controlling and displaying of the writing game.
 */
public class WritingGameActivity extends AppCompatActivity implements View.OnClickListener, WritingGameInterface {
  /** Key used for storing the character that will be displayed. */
  public static final String CHARACTER_INTENT_KEY = "char_selected";
  private WritingImageView writingImageView;
  private View btnClose;
  private View btnPrevious;
  private View btnNext;

  private GameController gameController;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    gameController = new GameController(this);
    setContentView(R.layout.writing_game_activity);
    writingImageView = (WritingImageView) findViewById(R.id.writingImageView);
    btnPrevious = findViewById(R.id.btn_prev_level);
    btnNext = findViewById(R.id.btn_next_level);
    btnClose = findViewById(R.id.btn_close);

    btnPrevious.setOnClickListener(this);
    btnNext.setOnClickListener(this);
    btnClose.setOnClickListener(this);
  }

  @Override
  protected void onResume() {

    super.onResume();
  }

  @Override
  protected void onStart() {
    super.onStart();
    gameController.onStart(null);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    gameController.onStop();
  }

  @Override
  public void onClick(View view) {
    if (btnNext.equals(view)) {
      gameController.onNextClicked();
    } else if (btnPrevious.equals(view)) {
      gameController.onPreviousClicked();
    } else if (btnClose.equals(view)) {
      gameController.onCloseClicked();
    }
  }

  @Override
  public void showCharacter(WritableCharacter character) {
    Drawable shape = character.getOutlineDrawable(this);
    Drawable steps = character.getStepsDrawable(this);
    if (shape != null && steps != null) {
      writingImageView.setShapeAndStepsLayers(shape, steps);
    } else {
      throw new RuntimeException("No images found to display in the writing view.");
    }
  }

  @Override
  public void setNextEnabled(boolean toEnable) {
    btnNext.setEnabled(toEnable);
  }

  @Override
  public void setPreviousEnabled(boolean toEnable) {
    btnPrevious.setEnabled(toEnable);
  }
}
