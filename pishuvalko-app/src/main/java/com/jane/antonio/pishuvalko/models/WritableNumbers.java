package com.jane.antonio.pishuvalko.models;

/**
 * Created by janedzumerko on 12/9/16.
 */

public class WritableNumbers extends WritableCharacter {
    /** Path for the asset manager. */
    public static final String PATH = "characters/numbers/";

    /**
     * Base constructor.
     *
     * @param displayName the name that will be displayed to the user.
     * @param baseFileName the name of the file in the assets folder.
     * @param fileFormat the file format of the images
     */
    public WritableNumbers(String displayName, String baseFileName, String fileFormat) {
        super(displayName, baseFileName, fileFormat);
    }

    @Override
    public String getCharacterFolder() {
        return PATH;
    }
}
