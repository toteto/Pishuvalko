package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jane.antonio.pishuvalko.R;

import java.util.List;

/**
 * Created by janedzumerko on 11/13/16.
 */

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
    public void onBindViewHolder(LevelViewHolder holder, final int position) {
        LetterImageObject letterImageObject = imageList.get(position);
        holder.countryPhoto.setImageDrawable(letterImageObject.getImage());

        holder.countryPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WritingGameActivity.class);
                intent.putExtra(WritingGameActivity.LETTER_SELECT_KEY, imageList.get(position).getName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.imageList.size();
    }
}