package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.LetterImageObject;
import com.jane.antonio.pishuvalko.utils.CharacterFactory;
import com.jane.antonio.pishuvalko.views.LevelViewHolder;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<LevelViewHolder> {

  private List<LetterImageObject> imageList;
  private Context context;

  public RecyclerViewAdapter(Context context, List<LetterImageObject> imageList) {
    this.imageList = imageList;
    this.context = context;
  }

  @Override
  public LevelViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view_level_selection, parent, false);
    LevelViewHolder rcv = new LevelViewHolder(layoutView);
    return rcv;
  }

  @Override
  public void onBindViewHolder(LevelViewHolder holder, int position) {
    LetterImageObject letterImageObject = imageList.get(position);
    holder.countryPhoto.setImageDrawable(letterImageObject.getImage());
    holder.countryPhoto.setOnClickListener(clickListener);
    holder.countryPhoto.setTag(position);
  }

  private final View.OnClickListener clickListener = new View.OnClickListener() {
    @Override
    public void onClick(View view) {
      int position = (Integer) view.getTag();
      if (position == 0) {
        context.startActivity(WritingGameActivity.getStartingIntent(context, /* for testing purposes*/CharacterFactory.getA()));
      } else {
        context.startActivity(WritingGameActivity.getStartingIntent(context, /* for testing purposes*/CharacterFactory.getP()));

      }
    }
  };

  @Override
  public int getItemCount() {
    return this.imageList.size();
  }
}