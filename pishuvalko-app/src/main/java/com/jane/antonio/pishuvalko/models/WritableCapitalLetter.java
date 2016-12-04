package com.jane.antonio.pishuvalko.models;

public class WritableCapitalLetter extends WritableCharacter {
  /** Path for the asset manager. */
  public static final String PATH = "characters/capital_letters/";

  /**
   * Base constructor.
   *
   * @param displayName the name that will be displayed to the user.
   * @param fileName the name of the file in the assets folder.
   */
  public WritableCapitalLetter(String displayName, String fileName) {
    super(displayName, fileName);
  }

  @Override
  public String getCharacterFolder() {
    return PATH;
  }
}
