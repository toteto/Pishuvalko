package com.jane.antonio.pishuvalko.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.CharacterFetcher;
import com.jane.antonio.pishuvalko.models.HeaderItem;
import com.jane.antonio.pishuvalko.models.ISolutionStorage;
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

  private View btnClose;

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

    btnClose = findViewById(R.id.btn_close);



    btnClose.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });

  }

  @Override
  protected void onResume() {
    super.onResume();
    charactersAdapter.setItems(buildLevelItems(CharacterFetcher.getCharacters(this, selectedGameType)));
  }



  @NonNull
  private List<Object> buildLevelItems(@NonNull List<WritableCharacter> characters) {
    final List<LevelItem> unsolvedItems = new LinkedList<>();
    final List<LevelItem> solvedItems = new LinkedList<>();
    final SolutionStorage solutionStorage = new SolutionStorage(this);
    for (WritableCharacter character : characters) {
      LevelItem item = new LevelItem(character, solutionStorage.solutionExists(character),
              solutionStorage.isSolutionApproved(character));
      if (solutionStorage.solutionExists(character) || solutionStorage.isSolutionApproved(character) == ISolutionStorage.SOLUTION_APPROVED) {
        solvedItems.add(item);
      } else {
        unsolvedItems.add(item);
      }
    }
    final List<Object> listItems = new ArrayList<>(solvedItems.size() + unsolvedItems.size() + 2);
    if (unsolvedItems.size() > 0) {
      listItems.add(new HeaderItem(getString(R.string.unsolved)));
      listItems.addAll(unsolvedItems);
    }

    if (solvedItems.size() > 0) {
      listItems.add(new HeaderItem(getString(R.string.solved)));
      listItems.addAll(solvedItems);
    }

    return listItems;
  }

  @Override
  public void onCharacterSelected(@NonNull WritableCharacter writableCharacter) {
    final Intent intent = new Intent(WritingGameActivity.getStartingIntent(this, selectedGameType, writableCharacter));
    startActivity(intent);
  }
}
