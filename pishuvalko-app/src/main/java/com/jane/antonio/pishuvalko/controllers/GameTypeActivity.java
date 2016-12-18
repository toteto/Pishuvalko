package com.jane.antonio.pishuvalko.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jane.antonio.pishuvalko.R;

public class GameTypeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button smallLetters;
    private Button bigLetters;
    private Button numbers;
    private Button forms;
    private View btnClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_type);
        smallLetters = (Button) findViewById(R.id.bt_smallLetters);
        bigLetters = (Button) findViewById(R.id.bt_bigLetters);
        numbers = (Button) findViewById(R.id.bt_numbers);
        forms = (Button) findViewById(R.id.bt_forms);
        btnClose = findViewById(R.id.btn_close);


        smallLetters.setOnClickListener(this);
        bigLetters.setOnClickListener(this);
        numbers.setOnClickListener(this);
        forms.setOnClickListener(this);

        btnClose.setOnClickListener(new View.OnClickListener() {
            // go back to previous activity
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }

    @Override
    public void onClick(View v) {
    // Perform action on click
        Intent intent = new Intent(getApplicationContext(), LevelSelectionActivity.class);
        switch (v.getId()) {
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
            case R.id.bt_forms:
                // start activity with forms
                intent.putExtra(LevelSelectionActivity.GAME_TYPE_KEY, LevelSelectionActivity.FORMS);
                break;

        }
        startActivity(intent);
    }
}
