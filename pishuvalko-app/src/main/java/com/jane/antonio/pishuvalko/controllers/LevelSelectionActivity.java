package com.jane.antonio.pishuvalko.controllers;

import android.content.res.AssetManager;
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

    private GridLayoutManager lLayout;
    private final static String charactersPath= "characters/letters";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_selection);

        List<LetterImageObject> imageList = getAllItemList();
        lLayout = new GridLayoutManager(LevelSelectionActivity.this, 3);

        RecyclerView rView = (RecyclerView)findViewById(R.id.recycler_view_level_selection);

        rView.setLayoutManager(lLayout);

        RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(LevelSelectionActivity.this, imageList);
        rView.setAdapter(rcAdapter);
    }

    public List<LetterImageObject> getAllItemList() {
        List<LetterImageObject> allLetters = new ArrayList<LetterImageObject>();
        AssetManager assetManager = getAssets();
        // load images
        try {
            String[] images = assetManager.list("characters/letters");
            for (String image : images){

                // get input stream
                InputStream ims = assetManager.open(charactersPath + "/" + image);

                // create drawable from stream
                Drawable d = Drawable.createFromStream(ims, null);

                // set the drawable to imageview
                allLetters.add(new LetterImageObject(d));
            }

        } catch(IOException ex) {
            // ?
        }
        return allLetters;

    }
}
