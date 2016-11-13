package com.jane.antonio.pishuvalko.controllers;

import android.app.Application;

public class App extends Application {
  private static App instance;

  public static App getInstance() {
    if (instance == null) {
      instance = new App();
    }
    return instance;
  }
}
