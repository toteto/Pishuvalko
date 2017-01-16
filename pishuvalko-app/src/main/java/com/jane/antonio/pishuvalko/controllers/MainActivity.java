package com.jane.antonio.pishuvalko.controllers;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jane.antonio.pishuvalko.R;

import java.lang.ref.WeakReference;
import java.util.Locale;
import java.util.Random;

/**
 * Activity that will serve as the main navigation for the application.
 */
public class MainActivity extends AppCompatActivity {

  private Button startGame;
  private Button goToSettings;
  private Button exit;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    startGame = (Button) findViewById(R.id.bt_startGame);
    goToSettings = (Button) findViewById(R.id.bt_goToSettings);
    exit = (Button) findViewById(R.id.bt_exit);

    startGame.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), GameTypeActivity.class);
        startActivity(intent);
      }
    });

    goToSettings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
      }
    });

    exit.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int which) {
            switch (which) {
              case DialogInterface.BUTTON_POSITIVE:
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                break;
              case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
            }
          }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener).setNegativeButton("No",
                dialogClickListener).show();

      }
    });

    findViewById(R.id.btnParents).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        showParentsQuizDialog();
      }
    });
  }

  private void showParentsQuizDialog() {
    final EditText etAnswer = new EditText(this);
    etAnswer.setInputType(InputType.TYPE_CLASS_NUMBER);
    etAnswer.setTextColor(ContextCompat.getColor(this, android.R.color.white));
    final Random rnd = new Random();
    final int x = rnd.nextInt(10) + 3;
    final int y = rnd.nextInt(10) + 3;
    final WeakReference<EditText> etAnswerWeakReference = new WeakReference<>(etAnswer);
    new AlertDialog.Builder(this, R.style.SolutionPopupTheme)
            .setTitle(R.string.confirm_that_you_are_the_parent)
            .setMessage(String.format(Locale.getDefault(), "%s\n%d * %d", getString(R.string.please_answer), x, y))
            .setView(etAnswer)
            .setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                if (etAnswerWeakReference.get() != null) {
                  try {
                    final int answer = Integer.parseInt(etAnswerWeakReference.get().getText().toString().trim());
                    if (answer == x * y) {
                      startActivity(new Intent(MainActivity.this, ParentsActivity.class));
                    } else {
                      showIncorrectParentsAnswer();
                    }
                  } catch (NumberFormatException e) {
                    showIncorrectParentsAnswer();
                  } finally {
                    dialogInterface.dismiss();
                  }
                }
              }
            })
            .setNeutralButton(R.string.close, new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
              }
            })
            .show();
  }

  private void showIncorrectParentsAnswer() {
    Toast.makeText(this, getString(R.string.incorrect_answer), Toast.LENGTH_SHORT).show();
  }
}
