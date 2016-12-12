package com.jane.antonio.pishuvalko.controllers;

import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.HeaderItem;
import com.jane.antonio.pishuvalko.models.SolutionItem;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/** Adapter that is capable of displaying {@link HeaderItem} and {@link SolutionItem}. */
public class SolutionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  @Retention(RetentionPolicy.SOURCE)
  @IntDef({SOLUTION, HEADER})
  public @interface ViewType {
  }

  private static final int SOLUTION = 1;
  private static final int HEADER = 2;

  private final List<Object> items;

  /** . */
  public SolutionAdapter() {
    items = new ArrayList<>();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (HEADER == viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.header_item, parent, false);
      view.setClickable(false);
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

  /** Set the items that will be used in this adapter. */
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
    private final ImageView ivSolution;
    private final TextView tvLabel;

    /** . */
    public SolutionViewHolder(@NonNull View itemView) {
      super(itemView);
      ivSolution = (ImageView) itemView.findViewById(R.id.imageView);
      tvLabel = (TextView) itemView.findViewById(R.id.textView);
    }

    /** Bind the views in this viewholder to the provided item. */
    public void bind(@NonNull SolutionItem solution) {
      ivSolution.setImageDrawable(solution.getSolution());
      tvLabel.setText(solution.getDisplayName());
    }
  }

  public static class HeaderViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvTitle;

    /** . */
    public HeaderViewHolder(@NonNull View itemView) {
      super(itemView);
      tvTitle = (TextView) itemView.findViewById(R.id.textView);
    }

    /** Bind the views in this viewholder to the provided item. */
    public void bind(@NonNull HeaderItem header) {
      tvTitle.setText(header.getTitle());
    }
  }
}