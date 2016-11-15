package com.jane.antonio.pishuvalko.controllers;

import android.content.res.AssetManager;
import android.support.annotation.Nullable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.jane.antonio.pishuvalko.R;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by janedzumerko on 11/13/16.
 */

public class LevelSelectionActivity extends AppCompatActivity {

    public static final String GAME_TYPE_KEY = "game_type";

    public static final int SMALL_LETTERS = 1;
    public static final int BIG_LETTERS = 2;
    public static final int NUMBERS = 3;



    private GridLayoutManager lLayout;
    private final static String bigLettersCharactersPath= "characters/bigLetters";
    private final static String smallLettersCharactersPath= "characters/smallLetters";
    private final static String numbersCharactersPath= "characters/numbers";

    List<LetterImageObject> imageList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        int gameChoice = getIntent().getIntExtra(GAME_TYPE_KEY, 2);



        switch(gameChoice) {
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

        lLayout = new GridLayoutManager(LevelSelectionActivity.this, 3);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view_level_selection);

        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(LevelSelectionActivity.this, imageList);
        rView.setAdapter(rcAdapter);
    }

    public List<LetterImageObject> getBigLetters() {
        List<LetterImageObject> allLetters = new ArrayList<LetterImageObject>();
        AssetManager assetManager = getAssets();
        // load images
        try {
            String[] images = assetManager.list(bigLettersCharactersPath);
            for (String image : images){

                Log.i("divya","Big letters : " + image);

                // get input stream
                InputStream ims = assetManager.open(bigLettersCharactersPath + "/" + image);

                // create drawable from stream
                Drawable d = Drawable.createFromStream(ims, null);

                // set the drawable to imageview
                allLetters.add(new LetterImageObject(d, false, getImageNameWithoutExtension(image)));
            }

        } catch(IOException ex) {
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
            for (String image : images){

                Log.i("divya","Small letters : " + image);

                // get input stream
                InputStream ims = assetManager.open(smallLettersCharactersPath + "/" + image);

                // create drawable from stream
                Drawable d = Drawable.createFromStream(ims, null);

                // set the drawable to imageview
                allLetters.add(new LetterImageObject(d, true, getImageNameWithoutExtension(image)));
            }

        } catch(IOException ex) {
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
            for (String image : images){

                Log.i("divya","Numbers : " + image);

                // get input stream
                InputStream ims = assetManager.open(numbersCharactersPath + "/" + image);

                // create drawable from stream
                Drawable d = Drawable.createFromStream(ims, null);

                // set the drawable to imageview
                allLetters.add(new LetterImageObject(d, true, getImageNameWithoutExtension(image)));
            }

        } catch(IOException ex) {
            // ?
        }
        return allLetters;

    }

    public String getImageNameWithoutExtension(String imageNameWithExtension) {
        return imageNameWithExtension.split("\\.")[0];
    }
}
