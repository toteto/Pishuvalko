package com.jane.antonio.pishuvalko.controllers;

import android.content.Intent;
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

public class LevelSelectionActivity extends AppCompatActivity implements LevelSelectedListener {
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

  @GameType
  private int selectedGameType = BIG_LETTERS;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level_selection);

    //noinspection WrongConstant
    selectedGameType = getIntent().getIntExtra(GAME_TYPE_KEY, BIG_LETTERS);

    final List<WritableCharacter> characterList = CharacterFetcher.getCharacters(this, selectedGameType);
    final LevelsAdapter levelsAdapter = new LevelsAdapter(this, characterList);
    final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view_level_selection);
    recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
    recyclerView.setAdapter(levelsAdapter);

    levelsAdapter.setLevelSelectedListener(this);
  }

  @Override
  public void onLevelSelected(int characterIndex) {
    final Intent intent = new Intent(WritingGameActivity.getStartingIntent(this, selectedGameType, characterIndex));
    startActivity(intent);
  }
}
