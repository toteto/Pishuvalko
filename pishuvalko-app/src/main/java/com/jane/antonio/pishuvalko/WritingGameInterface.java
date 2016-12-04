package com.jane.antonio.pishuvalko;

import com.jane.antonio.pishuvalko.models.WritableCharacter;

public interface WritingGameInterface {
  void showCharacter(WritableCharacter character);

  void setNextEnabled(boolean toEnable);

  void setPreviousEnabled(boolean toEnable);
}
