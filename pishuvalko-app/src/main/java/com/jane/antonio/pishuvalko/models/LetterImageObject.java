package com.jane.antonio.pishuvalko.models;

import android.graphics.drawable.Drawable;

public class LetterImageObject {

    private Drawable image;
    private boolean isLocked;
    private String name;

    /**
     * Constructor for creating Letter object
     * @param image the image file
     * @param isLocked if it is true, it should show locked image, else show image of the letter
     * @param name the name of the Letter, for example for letter 'A' the name is 'A', for number 1 the name is '1'
     */
    public LetterImageObject(Drawable image, boolean isLocked, String name) {
        this.image = image; this.isLocked = isLocked; this.name = name;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
