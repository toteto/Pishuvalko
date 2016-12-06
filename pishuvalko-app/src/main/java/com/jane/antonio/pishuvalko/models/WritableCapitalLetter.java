package com.jane.antonio.pishuvalko.models;

public class WritableCapitalLetter extends WritableCharacter {
  /** Path for the asset manager. */
  public static final String PATH = "characters/capital_letters/";

  /**
   * Base constructor.
   *
   * @param displayName the name that will be displayed to the user.
   * @param baseFileName the name of the file in the assets folder.
   * @param fileFormat the file format of the images
   */
  public WritableCapitalLetter(String displayName, String baseFileName, String fileFormat) {
    super(displayName, baseFileName, fileFormat);
  }

  @Override
  public String getCharacterFolder() {
    return PATH;
  }
}
