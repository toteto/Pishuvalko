package com.jane.antonio.pishuvalko.controllers;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.CharacterFetcher;
import com.jane.antonio.pishuvalko.models.HeaderItem;
import com.jane.antonio.pishuvalko.models.SolutionItem;
import com.jane.antonio.pishuvalko.models.SolutionStorage;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.LinkedList;
import java.util.List;

public class ParentsActivity extends AppCompatActivity {
  private RecyclerView recyclerView;
  private SolutionAdapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter = new SolutionAdapter();

    setContentView(R.layout.parents_activity);

    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setAdapter(adapter);

    adapter.setItems(buildSolutionsList());
  }

  private List<Object> buildSolutionsList() {
    List<Object> items = new LinkedList<>();
    final SolutionStorage solutionStorage = new SolutionStorage(this);

    // Add capital letters
    items.addAll(buildSolutionSection(getString(R.string.capital_letters), solutionStorage,
      CharacterFetcher.getAllCapitalLetters(this)));

    // Add lower case letters
    items.addAll(buildSolutionSection(getString(R.string.lower_case_letters), solutionStorage,
      CharacterFetcher.getAllSmallLetters(this)));

    // Add number characters
    items.addAll(
      buildSolutionSection(getString(R.string.numbers), solutionStorage, CharacterFetcher.getAllNumbers(this)));

    // Add forms shapes
    items.addAll(buildSolutionSection(getString(R.string.forms), solutionStorage, CharacterFetcher.getAllForms(this)));

    return items;
  }

  @NonNull
  private List<Object> buildSolutionSection(@NonNull String title, @NonNull SolutionStorage solutionStorage,
    @NonNull List<WritableCharacter> characters) {
    List<Object> res = new LinkedList<>();
    Bitmap solution;
    for (WritableCharacter character : characters) {
      solution = solutionStorage.readSolution(character);
      if (solution != null) {
        res.add(new SolutionItem(new BitmapDrawable(getResources(), solution), character.getDisplayName()));
      }
    }
    if (res.size() > 0) {
      res.add(0, new HeaderItem(title));
    }
    return res;
  }
}
