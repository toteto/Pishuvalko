package com.jane.antonio.pishuvalko.controllers;

import android.graphics.drawable.Drawable;

/**
 * Created by janedzumerko on 11/13/16.
 */

public class LetterImageObject {

    private Drawable image;

    public LetterImageObject(Drawable image) {
        this.image = image;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }
}
