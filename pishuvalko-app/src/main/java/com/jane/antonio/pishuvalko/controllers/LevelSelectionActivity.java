package com.jane.antonio.pishuvalko.controllers;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.LetterImageObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
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

  private List<LetterImageObject> imageList;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level_selection);

    int gameChoice = getIntent().getIntExtra(GAME_TYPE_KEY, 2);

    switch (gameChoice) {
      case SMALL_LETTERS:
        imageList = getImageObjects(PATH_LOWERCASE_LETTERS);
        break;
      case BIG_LETTERS:
        imageList = getImageObjects(PATH_CAPITAL_LETTERS);
        break;
      case NUMBERS:
        imageList = getImageObjects(PATH_NUMBERS);
        break;
      case FORMS:
        imageList = getImageObjects(PATH_FORMS);
        break;
    }

    RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view_level_selection);
    rView.setLayoutManager(new GridLayoutManager(LevelSelectionActivity.this, 3));

    RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(LevelSelectionActivity.this, imageList);
    rView.setAdapter(rcAdapter);
  }

  public List<LetterImageObject> getImageObjects(String PATH) {
    List<LetterImageObject> allLetters = new ArrayList<LetterImageObject>();
    AssetManager assetManager = getAssets();
    // load images
    try {
      String[] images = assetManager.list(PATH);
      for (String image : images) {

        Log.i(LOG_TAG, "Object : " + image);

        // get input stream
        InputStream ims = assetManager.open(PATH + "/" + image);

        // create drawable from stream
        Drawable d = Drawable.createFromStream(ims, null);

        // set the drawable to imageview
        allLetters.add(new LetterImageObject(d, true, getImageNameWithoutExtension(image)));
      }

    } catch (IOException ex) {
      // ?
    }
    return allLetters;

  }

  public String getImageNameWithoutExtension(String imageNameWithExtension) {
    return imageNameWithExtension.split("\\.")[0];
  }
}
