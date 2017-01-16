package com.jane.antonio.pishuvalko.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jane.antonio.pishuvalko.R;

public class SettingsActivity extends AppCompatActivity {

    Button language;
    Button about;
    Button share;
    View btnClose;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        language = (Button) findViewById(R.id.settings_bt_language);
        about = (Button) findViewById(R.id.settings_bt_about);
        share = (Button) findViewById(R.id.settings_bt_share);

        btnClose = findViewById(R.id.btn_close);


        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Show dialog here", Toast.LENGTH_SHORT).show();
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                String appLink = "https://play.google.com";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Пишувалко");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, appLink);
                sharingIntent.setType("text/plain");
                startActivity(Intent.createChooser(sharingIntent, getString(R.string.settings_share_via)));
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingsActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

    }
}
