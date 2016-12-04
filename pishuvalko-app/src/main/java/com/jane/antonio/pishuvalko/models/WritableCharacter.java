package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;

/** An abstract class for writable characters. */
public abstract class WritableCharacter {
  private final String displayName;
  private final String fileName;

  /**
   * Base constructor.
   *
   * @param displayName the name that will be displayed to the user.
   * @param fileName the name of the file in the assets folder.
   */
  public WritableCharacter(String displayName, String fileName) {
    this.displayName = displayName;
    this.fileName = fileName;
  }

  public String getDisplayName() {
    return displayName;
  }

  /** Get the assets folder of the character. ex: "characters/bigLetters". */
  public abstract String getCharacterFolder();

  /** Builds and returns the full path of the file for this writable character. */
  public String buildCharacterPath() {
    String charFolder = getCharacterFolder();
    if (!charFolder.endsWith("/")) {
      charFolder += "/";
    }
    return charFolder + fileName;
  }

  /**
   * Tries to open an input stream for the path of the character.
   *
   * @return the input stream if it is valid path. Null if the path can't be found.
   */
  @Nullable
  public InputStream openInputStreamForCharacter(Context context) {
    try {
      return context.getAssets().open(buildCharacterPath(), AssetManager.ACCESS_STREAMING);
    } catch (IOException e) {
      return null;
    }
  }
}
