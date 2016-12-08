package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.LetterImageObject;
import com.jane.antonio.pishuvalko.models.WritableCharacter;
import com.jane.antonio.pishuvalko.views.LevelViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<LevelViewHolder> {

  private List<WritableCharacter> imageList;
  private Context context;

  public RecyclerViewAdapter(Context context, List<WritableCharacter> imageList) {
    this.imageList = imageList;
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
    WritableCharacter writableCharacter = imageList.get(position);
    holder.getImageView().setImageDrawable(writableCharacter.getDisplayDrawable(context));
    holder.getImageView().setOnClickListener(clickListener);
    holder.getImageView().setTag(position);
  }

  private final View.OnClickListener clickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      int position = (Integer) view.getTag();
      Toast.makeText(context, "Item on position " + position + " clicked", Toast.LENGTH_SHORT).show();
    }
  };

  @Override
  public int getItemCount() {
    return this.imageList.size();
  }
}