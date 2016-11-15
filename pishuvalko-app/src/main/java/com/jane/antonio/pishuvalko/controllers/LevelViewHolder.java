package com.jane.antonio.pishuvalko.controllers;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jane.antonio.pishuvalko.R;

/**
 * Created by janedzumerko on 11/13/16.
 */
public class LevelViewHolder extends RecyclerView.ViewHolder{
    public ImageView countryPhoto;

    public LevelViewHolder(View itemView) {
        super(itemView);
        countryPhoto = (ImageView)itemView.findViewById(R.id.letter_photo);
    }


}
