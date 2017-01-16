package com.jane.antonio.pishuvalko.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.CharacterFetcher;
import com.jane.antonio.pishuvalko.models.LevelItem;
import com.jane.antonio.pishuvalko.models.SolutionStorage;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LevelSelectionActivity extends AppCompatActivity implements CharacterSelectedListener {
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
  private RecyclerView recyclerView;
  private CharactersAdapter charactersAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level_selection);

    //noinspection WrongConstant
    selectedGameType = getIntent().getIntExtra(GAME_TYPE_KEY, BIG_LETTERS);

    charactersAdapter = new CharactersAdapter(this, 3);
    recyclerView = (RecyclerView) findViewById(R.id.recycler_view_level_selection);
    recyclerView.setLayoutManager(charactersAdapter.getLayoutManager());
    recyclerView.setAdapter(charactersAdapter);
    charactersAdapter.setOnCharacterSelectedListener(this);
  }

  @Override
  protected void onResume() {
    super.onResume();
    final List<Object> tmp = new ArrayList<>();
    tmp.addAll(buildLevelItems(CharacterFetcher.getCharacters(this, selectedGameType)));
    charactersAdapter.setItems(tmp);
  }

  @NonNull
  private List<LevelItem> buildLevelItems(@NonNull List<WritableCharacter> characters) {
    List<LevelItem> items = new LinkedList<>();
    // TODO: 19.12.2016 build level items here
    SolutionStorage solutionStorage = new SolutionStorage(this);
    for (WritableCharacter character : characters) {
      LevelItem item = new LevelItem(character, solutionStorage.solutionExists(character),
        solutionStorage.isSolutionApproved(character));
      items.add(item);
    }
    return items;
  }

  @Override
  public void onCharacterSelected(@NonNull WritableCharacter writableCharacter) {
    final Intent intent = new Intent(WritingGameActivity.getStartingIntent(this, selectedGameType, writableCharacter));
    startActivity(intent);
  }
}
