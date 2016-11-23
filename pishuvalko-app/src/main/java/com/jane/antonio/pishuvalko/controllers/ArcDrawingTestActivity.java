package com.jane.antonio.pishuvalko.controllers;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.SeekBar;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.Arc;
import com.jane.antonio.pishuvalko.models.Step;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.views.WritingView;

import java.util.ArrayList;
import java.util.List;

public class ArcDrawingTestActivity extends AppCompatActivity {
  private SeekBar seekLeft;
  private SeekBar seekRight;
  private SeekBar seekTop;
  private SeekBar seekBottom;
  private SeekBar seekStartAngle;
  private SeekBar seekSweepAngle;

  private WritingView writingView;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.arc_drawing_test_layout);

    seekLeft = (SeekBar) findViewById(R.id.seekLeft);
    seekRight = (SeekBar) findViewById(R.id.seekRight);
    seekTop = (SeekBar) findViewById(R.id.seekTop);
    seekBottom = (SeekBar) findViewById(R.id.seekBottom);
    seekStartAngle = (SeekBar) findViewById(R.id.seekStartAngle);
    seekSweepAngle = (SeekBar) findViewById(R.id.seekSweepAngle);

    writingView = (WritingView) findViewById(R.id.writingView);

    seekLeft.setOnSeekBarChangeListener(seekBarChangeListener);
    seekRight.setOnSeekBarChangeListener(seekBarChangeListener);
    seekTop.setOnSeekBarChangeListener(seekBarChangeListener);
    seekBottom.setOnSeekBarChangeListener(seekBarChangeListener);
    seekStartAngle.setOnSeekBarChangeListener(seekBarChangeListener);
    seekSweepAngle.setOnSeekBarChangeListener(seekBarChangeListener);
  }

  private final SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
      List<Step> steps = new ArrayList<>();
      steps.add(new Step(
        new Arc(seekLeft.getProgress() * 3, seekTop.getProgress() * 3, seekRight.getProgress() * 3, seekBottom.getProgress() * 3,
          seekStartAngle.getProgress() * 3, seekSweepAngle.getProgress() * 3)));
      writingView.setCurrentCharacter(new WritableCharacter("arc", steps, 100, 100));
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
  };
}