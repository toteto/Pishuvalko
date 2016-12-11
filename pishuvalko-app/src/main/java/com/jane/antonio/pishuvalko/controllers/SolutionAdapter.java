package com.jane.antonio.pishuvalko.controllers;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.HeaderItem;
import com.jane.antonio.pishuvalko.models.SolutionItem;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class SolutionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  @Retention(RetentionPolicy.SOURCE)
  @IntDef({SOLUTION, HEADER})
  public @interface ViewType {
  }

  private static final int SOLUTION = 1;
  private static final int HEADER = 2;

  private final List<Object> items;

  public SolutionAdapter() {
    items = new ArrayList<>();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (HEADER == viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
      return new HeaderViewHolder(view);
    } else if (SOLUTION == viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.solution_item, parent, false);
      return new SolutionViewHolder(view);
    } else {
      throw new InvalidParameterException("Unsupported viewType:" + viewType);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case HEADER:
        ((HeaderViewHolder) holder).bind((HeaderItem) items.get(position));
        break;
      case SOLUTION:
        ((SolutionViewHolder) holder).bind((SolutionItem) items.get(position));
        break;
    }
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public void setItems(@NonNull List<Object> newItems) {
    items.clear();
    items.addAll(newItems);
  }

  @ViewType
  @Override
  public int getItemViewType(int position) {
    final Object item = items.get(position);
    if (item instanceof SolutionItem) {
      return SOLUTION;
    }
    if (item instanceof HeaderItem) {
      return HEADER;
    }
    throw new InvalidParameterException("Unsupported viewType at position:" + position);
  }

  public static class SolutionViewHolder extends RecyclerView.ViewHolder {

    public SolutionViewHolder(@NonNull View itemView) {
      super(itemView);
    }

    public SolutionViewHolder bind(@NonNull SolutionItem solution) {
      return this;
    }
  }

  public static class HeaderViewHolder extends RecyclerView.ViewHolder {

    public HeaderViewHolder(@NonNull View itemView) {
      super(itemView);
    }

    public HeaderViewHolder bind(@NonNull HeaderItem header) {
      return this;
    }
  }
}