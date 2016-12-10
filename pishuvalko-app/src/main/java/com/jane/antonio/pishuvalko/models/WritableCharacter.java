package com.jane.antonio.pishuvalko.models;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/** An abstract class for writable characters. */
public abstract class WritableCharacter {
  private static final String LOG_TAG = WritableCharacter.class.getSimpleName();

  @Retention(RetentionPolicy.SOURCE)
  @IntDef({SHAPE_AND_STEPS, ONLY_SHAPE, NOR_SHAPE_NOR_STEPS})
  public @interface GuidesType {
  }

  public static final int SHAPE_AND_STEPS = 1;
  public static final int ONLY_SHAPE = 2;
  public static final int NOR_SHAPE_NOR_STEPS = 3;

  private static final String STEPS_SUFFIX = "_steps";
  private static final String OUTLINE_SUFFIX = "_shape";
  private static final String DISPLAY_SUFFIX = "_display";

  private final String displayName;
  private final String baseFileName;
  private final String fileFormat;

  /**
   * Base constructor.
   *
   * @param displayName the name that will be displayed to the user.
   * @param baseFileName the name of the file in the assets folder.
   * @param fileFormat the file format of the images
   */
  public WritableCharacter(String displayName, String baseFileName, String fileFormat) {
    this.displayName = displayName;
    this.baseFileName = baseFileName;
    this.fileFormat = fileFormat;
  }

  public String getDisplayName() {
    return displayName;
  }

  /** Get the assets folder of the character. ex: "characters/bigLetters". */
  public abstract String getCharacterFolder();

  public String getBaseFileName() {
    return baseFileName;
  }

  /** Returns a general path leading to the images of the instance type. */
  private StringBuilder getCharacterPathBuilder() {
    final StringBuilder stringBuilder = new StringBuilder();
    final String charFolder = getCharacterFolder();
    stringBuilder.append(charFolder);
    if (!charFolder.endsWith("/")) {
      stringBuilder.append("/");
    }
    stringBuilder.append(baseFileName);
    return stringBuilder;
  }

  /**
   * Get the character outline drawable for this {@link WritableCharacter}.
   *
   * @return the drawable, or NULL if no file found with the provided name.
   */
  @Nullable
  public Drawable getOutlineDrawable(Context context) {
    return getDrawable(context, OUTLINE_SUFFIX);
  }

  /**
   * Get the character steps drawable for this {@link WritableCharacter}.
   *
   * @return the drawable, or NULL if no file found with the provided name.
   */
  @Nullable
  public Drawable getStepsDrawable(Context context) {
    return getDrawable(context, STEPS_SUFFIX);
  }

  /**
   * Get the character display drawable for level screen.
   *
   * @return the drawable, or NULL if no file found with the provided name.
   */
  @Nullable
  public Drawable getDisplayDrawable(Context context) {
    return getDrawable(context, DISPLAY_SUFFIX);
  }


  /** Gets drawables for this {@link WritableCharacter} based on the provided suffix. */
  @Nullable
  private Drawable getDrawable(Context context, String suffix) {
    final String fullPath = getCharacterPathBuilder().append(suffix).append(".").append(fileFormat).toString();
    try (InputStream is = context.getAssets().open(fullPath)) {
      return Drawable.createFromStream(is, null);
    } catch (IOException e) {
      Log.e(LOG_TAG, "getStepsDrawable: No file found with name: " + fullPath, e);
      return null;
    }
  }
}
