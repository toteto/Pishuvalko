package com.jane.antonio.pishuvalko.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.jane.antonio.pishuvalko.R;

public class SettingsActivity extends AppCompatActivity {

    Switch sound_switch;
    TextView sound;
    TextView language;
    ImageView share_view;
    private View btnClose;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        sound = (TextView) findViewById(R.id.txtv_sound);
        language = (TextView) findViewById(R.id.txtv_langauge);
        share_view = (ImageView) findViewById(R.id.share_button);
        sound_switch = (Switch) findViewById(R.id.soundSwitch);
        btnClose = findViewById(R.id.btn_close);

        //set the switch to last choice
        //sound_switch.setChecked(true);

        //attach a listener to check for changes in state
        sound_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {

                if(isChecked){
                    Toast.makeText(getApplicationContext(), "ON", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "OFF", Toast.LENGTH_SHORT).show();
                }

            }
        });

        language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Show dialog here", Toast.LENGTH_SHORT).show();
            }
        });

        share_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                String appLink = "https://play.google.com";
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Pishuvalko");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, appLink);
                sharingIntent.setType("text/plain");
                startActivity(Intent.createChooser(sharingIntent, "Spodeli preku ..."));
            }
        });

        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    }
}
