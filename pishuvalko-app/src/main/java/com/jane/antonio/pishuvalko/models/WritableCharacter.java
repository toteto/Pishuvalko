package com.jane.antonio.pishuvalko.models;

import java.io.Serializable;
import java.util.List;

/**
 * Model that describes the character that will be written by the user.
 */
public class WritableCharacter implements Serializable {
  private static final long serialVersionUID = -7117773315662436706L;

  private final String name;
  private final List<Step> steps;
  private final float height;
  private final float width;

  public WritableCharacter(String name, List<Step> steps, float height, float width) {
    this.name = name;
    this.steps = steps;
    this.height = height;
    this.width = width;
  }

  public String getName() {
    return name;
  }

  public float getHeight() {
    return height;
  }

  public float getWidth() {
    return width;
  }

  public List<Step> getSteps() {
    return steps;
  }
}
