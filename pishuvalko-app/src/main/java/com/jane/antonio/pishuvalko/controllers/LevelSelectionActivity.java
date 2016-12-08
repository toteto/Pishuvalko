package com.jane.antonio.pishuvalko.controllers;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.CharacterFetcher;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class LevelSelectionActivity extends AppCompatActivity {
  private static final String LOG_TAG = LevelSelectionActivity.class.getSimpleName();

  public static final String GAME_TYPE_KEY = "game_type";

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({SMALL_LETTERS, BIG_LETTERS, NUMBERS, FORMS})
  public @interface GameType {
  }

  public static final int SMALL_LETTERS = 1;
  public static final int BIG_LETTERS = 2;
  public static final int NUMBERS = 3;
  public static final int FORMS = 4;


  private GridLayoutManager lLayout;
  private static final String PATH_CAPITAL_LETTERS = "characters/bigLetters";
  private static final String PATH_LOWERCASE_LETTERS = "characters/smallLetters";
  private static final String PATH_NUMBERS = "characters/numbers";
  private static final String PATH_FORMS = "characters/forms";

  private List<WritableCharacter> imageList;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level_selection);

    int gameChoice = getIntent().getIntExtra(GAME_TYPE_KEY, 2);

    switch (gameChoice) {
      case SMALL_LETTERS:
        imageList = CharacterFetcher.getAllSmallLetters(getApplicationContext());
        break;
      case BIG_LETTERS:
        imageList = CharacterFetcher.getAllCapitalLetters(getApplicationContext());
        break;
      case NUMBERS:
        //imageList = getImageObjects(PATH_NUMBERS);
        break;
      case FORMS:
        //imageList = getImageObjects(PATH_FORMS);
        break;
    }

    RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view_level_selection);
    rView.setLayoutManager(new GridLayoutManager(LevelSelectionActivity.this, 3));

    RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(LevelSelectionActivity.this, imageList);
    rView.setAdapter(rcAdapter);
  }


  public String getImageNameWithoutExtension(String imageNameWithExtension) {
    return imageNameWithExtension.split("\\.")[0];
  }
}
