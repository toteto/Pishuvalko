package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** Class that will generate the character objects. */
public final class CharacterFetcher {
  private static final String LOG_TAG = CharacterFetcher.class.getSimpleName();

  private CharacterFetcher() {
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
   * Gets the config file for the provided path.
   *
   * @param path to the folder of the config.csv. Ex: {@link WritableCapitalLetter#PATH}
   */
  @NonNull
  private static List<String[]> getConfig(@NonNull Context context, @NonNull String path) {
    List<String[]> lines = new LinkedList<>();
    try (BufferedReader reader = new BufferedReader(
      new InputStreamReader(context.getAssets().open(path + "/" + "config.csv")))) {
      String line = reader.readLine();
      while (!line.isEmpty()) {
        lines.add(line.split(","));
      }
    } catch (IOException e) {
      Log.e(LOG_TAG, "getConfig: No config file found in this path: " + path + "/" + "config.csv", e);
    }
    return lines;
  }

}

