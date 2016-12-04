package com.jane.antonio.pishuvalko.views;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jane.antonio.pishuvalko.R;

public class LevelViewHolder extends RecyclerView.ViewHolder {
  private final ImageView imageView;

  public LevelViewHolder(View itemView) {
    super(itemView);
    imageView = (ImageView) itemView.findViewById(R.id.letter_photo);
  }

  public ImageView getImageView() {
    return imageView;
  }
}
