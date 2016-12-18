package com.jane.antonio.pishuvalko.controllers;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.CharacterFetcher;
import com.jane.antonio.pishuvalko.models.HeaderItem;
import com.jane.antonio.pishuvalko.models.SolutionStorage;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.util.LinkedList;
import java.util.List;

public class ParentsActivity extends AppCompatActivity implements CharacterSelectedListener {

  private RecyclerView recyclerView;
  private CharactersAdapter adapter;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    adapter = new CharactersAdapter(this, 3);

    setContentView(R.layout.parents_activity);

    recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
    recyclerView.setLayoutManager(adapter.getLayoutManager());
    recyclerView.setAdapter(adapter);

    adapter.setItems(buildSolutionsList());
    adapter.setOnCharacterSelectedListener(this);
  }

  /** Builds the whole list of headers and character that will be displayed in the recyclerView. */
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

  /**
   * Builds a specific section with header and characters for which solution exists. If not a single solution exists for
   * the whole section, no header will be added and thous no items for this section.
   */
  @NonNull
  private List<Object> buildSolutionSection(@NonNull String title, @NonNull SolutionStorage solutionStorage,
    @NonNull List<WritableCharacter> characters) {
    List<Object> res = new LinkedList<>();
    for (WritableCharacter character : characters) {
      for (@WritableCharacter.GuidesType int guideType : GameController.GAME_GUIDE_TYPES) {
        if (solutionStorage.solutionExists(character, guideType)) {
          res.add(character);
          break; // at least one solution found, we add the curr character and continue to the next one
        }
      }
    }
    if (res.size() > 0) {
      res.add(0, new HeaderItem(title));
    }
    return res;
  }

  private void showSolutionPopUp(WritableCharacter character) {
    // TODO: 18.12.2016 display solution_popup layout
    final ImageView solutionView = new ImageView(this);
    new AlertDialog.Builder(this, R.style.SolutionPopupTheme).setView(solutionView).setPositiveButton(R.string.confirm,
      new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
          // TODO: 14.12.2016 write to storage that the solution has been accepted
          dialog.dismiss();
        }
      }).setNegativeButton(R.string.reject, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        // TODO: 14.12.2016 write to storage that the solution has been denied
      }
    }).setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
      }
    }).show();
  }

  @Override
  public void onCharacterSelected(@NonNull WritableCharacter writableCharacter) {
    // TODO: 18.12.2016 implement listenre
  }
}
