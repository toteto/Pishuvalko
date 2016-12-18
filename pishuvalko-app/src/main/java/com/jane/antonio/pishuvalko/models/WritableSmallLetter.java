package com.jane.antonio.pishuvalko.models;

import java.io.Serializable;

public class WritableSmallLetter extends WritableCharacter implements Serializable {
  /** Path for the asset manager. */
  public static final String PATH = "characters/small_letters/";

  /**
   * Base constructor.
   *
   * @param displayName the name that will be displayed to the user.
   * @param baseFileName the name of the file in the assets folder.
   * @param fileFormat the file format of the images
   */
  public WritableSmallLetter(String displayName, String baseFileName, String fileFormat) {
    super(displayName, baseFileName, fileFormat);
  }

  @Override
  public String getCharacterFolder() {
    return PATH;
  }

}
