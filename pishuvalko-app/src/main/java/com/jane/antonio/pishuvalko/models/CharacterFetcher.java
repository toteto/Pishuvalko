package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.jane.antonio.pishuvalko.controllers.LevelSelectionActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static com.jane.antonio.pishuvalko.controllers.LevelSelectionActivity.BIG_LETTERS;
import static com.jane.antonio.pishuvalko.controllers.LevelSelectionActivity.FORMS;
import static com.jane.antonio.pishuvalko.controllers.LevelSelectionActivity.NUMBERS;
import static com.jane.antonio.pishuvalko.controllers.LevelSelectionActivity.SMALL_LETTERS;

/** Class that will generate the character objects. */
public final class CharacterFetcher {
  private static final String LOG_TAG = CharacterFetcher.class.getSimpleName();

  private CharacterFetcher() {
  }

  /** Get list of {@link WritableCharacter} for the provided gametype. */
  @NonNull
  public static List<WritableCharacter> getCharacters(@NonNull Context context,
    @LevelSelectionActivity.GameType int type) {
    switch (type) {
      case SMALL_LETTERS:
        return getAllSmallLetters(context);
      case BIG_LETTERS:
        return getAllCapitalLetters(context);
      case NUMBERS:
        return getAllNumbers(context);
      case FORMS:
        return getAllForms(context);
      default:
        return getAllCapitalLetters(
          context); // FIXME: 08.12.2016 to be removed when the rest of the methods are defined
    }
  }

  /**
   * @return a list of all capital letters that are defined in the corresponding config.csv file. If no file was defined
   * or it was empty, an empty list is returned.
   */
  @NonNull
  public static List<WritableCharacter> getAllCapitalLetters(@NonNull Context context) {
    List<WritableCharacter> list = new ArrayList<>(40);
    for (String[] attrs : getConfig(context, WritableCapitalLetter.PATH)) {
      list.add(new WritableCapitalLetter(attrs[0], attrs[1], attrs[2]));
    }
    return list;
  }

  /**
   * @return a list of all small letters that are defined in the corresponding config.csv file. If no file was defined
   * or it was empty, an empty list is returned.
   */
  @NonNull
  public static List<WritableCharacter> getAllSmallLetters(@NonNull Context context) {
    List<WritableCharacter> list = new ArrayList<>(40);
    for (String[] attrs : getConfig(context, WritableSmallLetter.PATH)) {
      list.add(new WritableSmallLetter(attrs[0], attrs[1], attrs[2]));
    }
    return list;
  }

  /**
   * @return a list of all numbers that are defined in the corresponding config.csv file. If no file was defined
   * or it was empty, an empty list is returned.
   */
  @NonNull
  public static List<WritableCharacter> getAllNumbers(@NonNull Context context) {
    List<WritableCharacter> list = new ArrayList<>(40);
    for (String[] attrs : getConfig(context, WritableNumbers.PATH)) {
      list.add(new WritableNumbers(attrs[0], attrs[1], attrs[2]));
    }
    return list;
  }

  /**
   * @return a list of all forms that are defined in the corresponding config.csv file. If no file was defined
   * or it was empty, an empty list is returned.
   */
  @NonNull
  public static List<WritableCharacter> getAllForms(@NonNull Context context) {
    List<WritableCharacter> list = new ArrayList<>(40);
    for (String[] attrs : getConfig(context, WritableForm.PATH)) {
      list.add(new WritableForm(attrs[0], attrs[1], attrs[2]));
    }
    return list;
  }

  /**
   * Gets the config file for the provided path.
   *
   * @param path to the folder of the config.csv. Ex: {@link WritableCapitalLetter#PATH}
   */
  @NonNull
  private static List<String[]> getConfig(@NonNull Context context, @NonNull String path) {
    List<String[]> lines = new LinkedList<>();
    try (BufferedReader reader = new BufferedReader(
      new InputStreamReader(context.getAssets().open(path + "config.csv")))) {
      String line = reader.readLine();
      while (line != null && !line.isEmpty()) {
        lines.add(line.split(","));
        line = reader.readLine();
      }
    } catch (IOException e) {
      Log.e(LOG_TAG, "getConfig: No config file found in this path: " + path + "/" + "config.csv", e);
    }
    return lines;
  }

}

