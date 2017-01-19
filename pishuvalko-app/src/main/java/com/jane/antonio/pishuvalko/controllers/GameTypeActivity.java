package com.jane.antonio.pishuvalko.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.GameTypeItem;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.ArrayList;
import java.util.List;

public class GameTypeActivity extends AppCompatActivity {
  private CharactersAdapter gameTypesAdapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_game_type);
    gameTypesAdapter = new CharactersAdapter(this, 2);
    gameTypesAdapter.setOnCharacterSelectedListener(gameTypeSelectedListener);
    RecyclerView rvGameTypes = (RecyclerView) findViewById(R.id.rvGameTypes);
    rvGameTypes.setAdapter(gameTypesAdapter);
    rvGameTypes.setLayoutManager(gameTypesAdapter.getLayoutManager());


    View btnClose = findViewById(R.id.btn_close);
    btnClose.setOnClickListener(new View.OnClickListener() {
      // go back to previous activity
      @Override
      public void onClick(View view) {
        onBackPressed();
      }
    });
  }

  @Override
  protected void onResume() {
    super.onResume();
    gameTypesAdapter.setItems(buildGameTypesList());
  }

  private List<Object> buildGameTypesList() {
    final List<Object> res = new ArrayList<>(4);
    res.add(new GameTypeItem(ContextCompat.getDrawable(this, R.drawable.game_type_capital_letters),
      getString(R.string.capital_letters), LevelSelectionActivity.BIG_LETTERS));
    res.add(new GameTypeItem(ContextCompat.getDrawable(this, R.drawable.game_type_lowercase_letters),
      getString(R.string.lower_case_letters), LevelSelectionActivity.SMALL_LETTERS));
    res.add(new GameTypeItem(ContextCompat.getDrawable(this, R.drawable.game_type_numbers_letters),
      getString(R.string.numbers), LevelSelectionActivity.NUMBERS));
    res.add(
      new GameTypeItem(ContextCompat.getDrawable(this, R.drawable.game_type_forms_letters), getString(R.string.forms),
        LevelSelectionActivity.FORMS));

    return res;
  }

  private final CharacterSelectedListener gameTypeSelectedListener = new CharacterSelectedListener() {
    @Override
    public void onCharacterSelected(@NonNull WritableCharacter writableCharacter) {
      final GameTypeItem gameType = ((GameTypeItem) writableCharacter);
      final Intent intent = new Intent(GameTypeActivity.this, LevelSelectionActivity.class);
      intent.putExtra(LevelSelectionActivity.GAME_TYPE_KEY, gameType.getGameType());
      startActivity(intent);
    }
  };
}
