package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.views.LevelViewHolder;

import java.util.List;

public class LevelsAdapter extends RecyclerView.Adapter<LevelViewHolder> {

  private final List<WritableCharacter> characters;
  private final Context context;
  private LevelSelectedListener levelSelectedListener;

  public LevelsAdapter(@NonNull Context context, @NonNull List<WritableCharacter> characters) {
    this.characters = characters;
    this.context = context;
  }

  @Override
  public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_level_selection, parent,
      false);
    return new LevelViewHolder(layoutView);
  }

  @Override
  public void onBindViewHolder(LevelViewHolder holder, int position) {
    WritableCharacter writableCharacter = characters.get(position);
    holder.getImageView().setImageDrawable(writableCharacter.getDisplayDrawable(context));
    holder.getImageView().setOnClickListener(clickListener);
    holder.getImageView().setTag(position);
  }

  private final View.OnClickListener clickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      int position = (Integer) view.getTag();
      if (levelSelectedListener != null) {
        levelSelectedListener.onLevelSelected(position);
      }
    }
  };

  public void setLevelSelectedListener(LevelSelectedListener levelSelectedListener) {
    this.levelSelectedListener = levelSelectedListener;
  }

  @Override
  public int getItemCount() {
    return characters.size();
  }
}