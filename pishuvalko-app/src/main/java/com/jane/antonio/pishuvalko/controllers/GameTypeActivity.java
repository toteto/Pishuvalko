package com.jane.antonio.pishuvalko.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jane.antonio.pishuvalko.R;

/**
 * Created by janedzumerko on 11/13/16.
 */

public class GameTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button smallLetters;
    private Button bigLetters;
    private Button numbers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
        smallLetters = (Button) findViewById(R.id.bt_smallLetters);
        bigLetters = (Button) findViewById(R.id.bt_bigLetters);
        numbers = (Button) findViewById(R.id.bt_numbers);

        smallLetters.setOnClickListener(this);
        bigLetters.setOnClickListener(this);
        numbers.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
    // Perform action on click
        Intent intent = new Intent(getApplicationContext(), LevelSelectionActivity.class);
        switch(v.getId()) {
            case R.id.bt_smallLetters:
                // start activity with small letters
                intent.putExtra(LevelSelectionActivity.GAME_TYPE_KEY, LevelSelectionActivity.SMALL_LETTERS);
                break;
            case R.id.bt_bigLetters:
                // start activity with big letters
                intent.putExtra(LevelSelectionActivity.GAME_TYPE_KEY, LevelSelectionActivity.BIG_LETTERS);
                break;
            case R.id.bt_numbers:
                // start activity with numbers
                intent.putExtra(LevelSelectionActivity.GAME_TYPE_KEY, LevelSelectionActivity.NUMBERS);
                break;
        }
        startActivity(intent);
    }
}
