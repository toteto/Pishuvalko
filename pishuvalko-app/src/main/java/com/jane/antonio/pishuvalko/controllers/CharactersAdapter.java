package com.jane.antonio.pishuvalko.controllers;

import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jane.antonio.pishuvalko.R;
import com.jane.antonio.pishuvalko.models.GameTypeItem;
import com.jane.antonio.pishuvalko.models.HeaderItem;
import com.jane.antonio.pishuvalko.models.ISolutionStorage;
import com.jane.antonio.pishuvalko.models.LevelItem;
import com.jane.antonio.pishuvalko.models.WritableCharacter;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

public class CharactersAdapter extends RecyclerView.Adapter {
  private final List<Object> items;
  private CharacterSelectedListener characterSelectedListener;
  private final GridLayoutManager layoutManager;
  private int itemsPerRow;
  private Context context;

  public CharactersAdapter(@NonNull Context context, @IntRange(from = 1) int itemsPerRow) {
    this.itemsPerRow = itemsPerRow;
    items = new ArrayList<>();
    layoutManager = new GridLayoutManager(context, itemsPerRow);
    layoutManager.setSpanSizeLookup(spanSizeLookup);
    this.context = context;
  }

  @SuppressWarnings("FieldCanBeLocal")
  private final GridLayoutManager.SpanSizeLookup spanSizeLookup = new GridLayoutManager.SpanSizeLookup() {
    @Override
    public int getSpanSize(int position) {
      return getItemViewType(position) == R.layout.header_item ? itemsPerRow : 1;
    }
  };

  public void setItems(@NonNull List<Object> items) {
    this.items.clear();
    this.items.addAll(items);
    notifyDataSetChanged();
  }

  public boolean removeCharacter(@NonNull WritableCharacter character) {
    int index = items.indexOf(character);
    if (index != -1) {
      items.remove(index);
      notifyItemRemoved(index);
      return true;
    } else {
      return false;
    }
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    final View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
    if (R.layout.header_item == viewType) {
      view.setClickable(false);
      return new HeaderViewHolder(view);
    } else if (R.layout.character_item == viewType) {
      final CharacterViewHolder holder = new CharacterViewHolder(view);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          final WritableCharacter item = (WritableCharacter) items.get(holder.getAdapterPosition());
          characterSelectedListener.onCharacterSelected(item);
        }
      });
      return holder;
    } else if (R.layout.level_item == viewType) {
      final LevelViewHolder levelViewHolder = new LevelViewHolder(view, context);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          final LevelItem levelItem = (LevelItem) items.get(levelViewHolder.getAdapterPosition());
          characterSelectedListener.onCharacterSelected(levelItem.getWritableCharacter());
        }
      });
      return levelViewHolder;
    } else if (R.layout.game_type_item == viewType) {
      final GameTypeViewHolder gameTypeViewHolder = new GameTypeViewHolder(view);
      view.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          final GameTypeItem gameType = ((GameTypeItem) items.get(gameTypeViewHolder.getAdapterPosition()));
          characterSelectedListener.onCharacterSelected(gameType);
        }
      });
      return gameTypeViewHolder;
    } else {
      throw new InvalidParameterException("Unsupported viewType:" + viewType);
    }
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    switch (getItemViewType(position)) {
      case R.layout.header_item:
        ((HeaderViewHolder) holder).bind((HeaderItem) items.get(position));
        break;
      case R.layout.character_item:
        ((CharacterViewHolder) holder).bind((WritableCharacter) items.get(position));
        break;
      case R.layout.level_item:
        ((LevelViewHolder) holder).bind((LevelItem) items.get(position));
        break;
      case R.layout.game_type_item:
        ((GameTypeViewHolder) holder).bind(((GameTypeItem) items.get(position)));
    }
  }

  @Override
  public int getItemViewType(int position) {
    Object item = items.get(position);
    if (item instanceof HeaderItem) {
      return R.layout.header_item;
    } else if (item instanceof GameTypeItem) {
      return R.layout.game_type_item;
    }else if (item instanceof WritableCharacter) {
      return R.layout.character_item;
    } else if (item instanceof LevelItem) {
      return R.layout.level_item;
    } else {
      return super.getItemViewType(position);
    }
  }

  @Override
  public int getItemCount() {
    return items.size();
  }

  public GridLayoutManager getLayoutManager() {
    return layoutManager;
  }

  public void setOnCharacterSelectedListener(CharacterSelectedListener listener) {
    this.characterSelectedListener = listener;
  }

  public static class CharacterViewHolder extends RecyclerView.ViewHolder {
    protected final ImageView ivDisplayImage;
    protected final TextView tvLabel;

    /**
     * .
     */
    public CharacterViewHolder(@NonNull View itemView) {
      super(itemView);
      ivDisplayImage = (ImageView) itemView.findViewById(R.id.tvDisplayImage);
      tvLabel = (TextView) itemView.findViewById(R.id.tvLabel);
    }

    /**
     * Bind the views in this viewholder to the provided item.
     */
    public void bind(@NonNull WritableCharacter character) {
      ivDisplayImage.setImageDrawable(character.getDisplayDrawable(ivDisplayImage.getContext()));
      tvLabel.setText(character.getDisplayName());
    }
  }

  public static class HeaderViewHolder extends RecyclerView.ViewHolder {
    private final TextView tvTitle;

    /**
     * .
     */
    public HeaderViewHolder(@NonNull View itemView) {
      super(itemView);
      tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
    }

    /**
     * Bind the views in this viewholder to the provided item.
     */
    public void bind(@NonNull HeaderItem header) {
      tvTitle.setText(header.getTitle());
    }
  }

  public static class LevelViewHolder extends RecyclerView.ViewHolder {
    // TODO: 19.12.2016 views of the layout
    private ImageView letterImage;
    private ImageView levelImage;
    private Context context;

    public LevelViewHolder(View itemView, @NonNull Context context) {
      super(itemView);
      letterImage = (ImageView) itemView.findViewById(R.id.letter_image);
      levelImage = (ImageView) itemView.findViewById(R.id.level_image);
      this.context = context;
    }

    public void bind(@NonNull LevelItem levelItem) {
      letterImage.setImageDrawable(levelItem.getWritableCharacter().getDisplayDrawable(context));
      if (levelItem.getSolutionState() == ISolutionStorage.SOLUTION_APPROVED) {
        levelImage.setImageResource(R.drawable.thumbs_up);
      } else {
        levelImage.setImageDrawable(null);
      }
    }
  }

  public static class GameTypeViewHolder extends CharacterViewHolder {

    public GameTypeViewHolder(@NonNull View itemView) {
      super(itemView);
    }

    public void bind(@NonNull GameTypeItem gameTypeItem) {
      ivDisplayImage.setImageDrawable(gameTypeItem.getDrawable());
      tvLabel.setText(gameTypeItem.getText());
    }
  }
}
