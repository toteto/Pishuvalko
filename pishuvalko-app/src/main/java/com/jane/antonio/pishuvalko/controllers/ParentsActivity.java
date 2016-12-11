package com.jane.antonio.pishuvalko.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.jane.antonio.pishuvalko.R;


public class ParentsActivity extends AppCompatActivity {
  private SolutionAdapter adapter;
  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.parents_activity);
  }
}
