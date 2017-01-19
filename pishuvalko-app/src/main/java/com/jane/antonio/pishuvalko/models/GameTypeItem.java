package com.jane.antonio.pishuvalko.models;

import android.graphics.drawable.Drawable;

import com.jane.antonio.pishuvalko.controllers.LevelSelectionActivity;

/** Rushing to make this project work, this model is in no way extension of {@link WritableCharacter} */
public class GameTypeItem extends WritableCharacter {
  private final Drawable drawable;
  private final String text;
  private final int gameType;

  public GameTypeItem(Drawable drawable, String text, int gameType) {
    super(text, "", "");
    this.drawable = drawable;
    this.text = text;
    this.gameType = gameType;
  }

  public Drawable getDrawable() {
    return drawable;
  }

  public String getText() {
    return text;
  }

  public int getGameType() {
    return gameType;
  }

  @Override
  public String getCharacterFolder() {
    return null;
  }
}
