package com.jane.antonio.pishuvalko.controllers;

import android.support.annotation.NonNull;

import com.jane.antonio.pishuvalko.models.LevelItem;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

public interface CharacterSelectedListener {
  void onCharacterSelected(@NonNull WritableCharacter writableCharacter);

}
