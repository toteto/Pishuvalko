package com.jane.antonio.pishuvalko.models;

import android.graphics.drawable.Drawable;

public class SolutionItem {
  private final Drawable solution;
  private final String displayName;

  public SolutionItem(Drawable solution, String displayName) {
    this.solution = solution;
    this.displayName = displayName;
  }

  public Drawable getSolution() {
    return solution;
  }

  public String getDisplayName() {
    return displayName;
  }
}
