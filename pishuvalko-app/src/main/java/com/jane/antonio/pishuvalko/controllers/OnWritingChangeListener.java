package com.jane.antonio.pishuvalko.controllers;

import android.graphics.Path;

import com.jane.antonio.pishuvalko.models.WritableCharacter;

/**
 * Interface that provides the necessary methods for listening for writing updates made by the user.
 */
public interface OnWritingChangeListener {
  void onWritingChange(WritableCharacter character, Path pathDrawn);
}
