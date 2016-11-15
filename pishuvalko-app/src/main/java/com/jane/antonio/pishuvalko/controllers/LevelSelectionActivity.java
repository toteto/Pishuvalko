package com.jane.antonio.pishuvalko.controllers;

import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.LetterImageObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LevelSelectionActivity extends AppCompatActivity {
  private static final String LOG_TAG = LevelSelectionActivity.class.getSimpleName();

  public static final String GAME_TYPE_KEY = "game_type";

  public static final int SMALL_LETTERS = 1;
  public static final int BIG_LETTERS = 2;
  public static final int NUMBERS = 3;


  private GridLayoutManager lLayout;
  private final static String bigLettersCharactersPath = "characters/bigLetters";
  private final static String smallLettersCharactersPath = "characters/smallLetters";
  private final static String numbersCharactersPath = "characters/numbers";

  List<LetterImageObject> imageList;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level_selection);

    int gameChoice = getIntent().getIntExtra(GAME_TYPE_KEY, 2);

    switch (gameChoice) {
      case SMALL_LETTERS:
        imageList = getSmallLetters();
        break;
      case BIG_LETTERS:
        imageList = getBigLetters();
        break;
      case NUMBERS:
        imageList = getNumbers();
        break;
    }

    RecyclerView rView = (RecyclerView) findViewById(R.id.recycler_view_level_selection);
    rView.setLayoutManager(new GridLayoutManager(LevelSelectionActivity.this, 3));

    RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(LevelSelectionActivity.this, imageList);
    rView.setAdapter(rcAdapter);
  }

  public List<LetterImageObject> getBigLetters() {
    List<LetterImageObject> allLetters = new ArrayList<>();
    AssetManager assetManager = getAssets();
    // load images
    try {
      String[] images = assetManager.list(bigLettersCharactersPath);
      for (String image : images) {

        Log.i(LOG_TAG, "Big letters : " + image);

        // get input stream
        InputStream ims = assetManager.open(bigLettersCharactersPath + "/" + image);

        // create drawable from stream
        Drawable d = Drawable.createFromStream(ims, null);

        // set the drawable to imageview
        allLetters.add(new LetterImageObject(d, false, getImageNameWithoutExtension(image)));
      }

    } catch (IOException ex) {
      // ?
    }
    return allLetters;
  }

  public List<LetterImageObject> getSmallLetters() {
    List<LetterImageObject> allLetters = new ArrayList<LetterImageObject>();
    AssetManager assetManager = getAssets();
    // load images
    try {
      String[] images = assetManager.list(smallLettersCharactersPath);
      for (String image : images) {

        Log.i(LOG_TAG, "Small letters : " + image);

        // get input stream
        InputStream ims = assetManager.open(smallLettersCharactersPath + "/" + image);

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

  public List<LetterImageObject> getNumbers() {
    List<LetterImageObject> allLetters = new ArrayList<LetterImageObject>();
    AssetManager assetManager = getAssets();
    // load images
    try {
      String[] images = assetManager.list(numbersCharactersPath);
      for (String image : images) {

        Log.i(LOG_TAG, "Numbers : " + image);

        // get input stream
        InputStream ims = assetManager.open(numbersCharactersPath + "/" + image);

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
