package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WritableCharacter {
  private final String characterPath;
  private final String characterName;
  private final List<String> steps;

  public WritableCharacter(String characterPath, String characterName, List<String> steps) {
    this.characterPath = characterPath;
    this.characterName = characterName;
    this.steps = steps;
  }

  public String getCharacterName() {
    return characterName;
  }

  public List<String> getSteps() {
    return steps;
  }

  public String getCharacterPath() {
    return characterPath;
  }

  /**
   * Tries to open an input stream for the path of the character.
   *
   * @return the input stream if it is valid path. Null if the path can't be found.
   */
  public InputStream openInputStreamForCharacter(Context context) {
    try {
      return context.getAssets().open(characterPath, AssetManager.ACCESS_STREAMING);
    } catch (IOException e) {
      return null;
    }
  }
}
