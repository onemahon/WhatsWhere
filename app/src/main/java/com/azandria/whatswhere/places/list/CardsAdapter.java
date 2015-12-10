package com.azandria.whatswhere.places.list;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.azandria.whatswhere.R;
import com.azandria.whatswhere.places.PlaceCard;
import com.azandria.whatswhere.views.ImageCard;

import java.util.List;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.CardViewHolder> {

    ///////////
    // region Member Variables

    private List<PlaceCard> mCards;

    // endregion
    ///////////

    ///////////
    // region Public Methods

    public void setCards(List<PlaceCard> cards) {
        this.mCards = cards;
        notifyDataSetChanged();
    }

    // endregion
    ///////////

    ///////////
    // region Parent Class Overrides

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_place_card, parent, false);

        CardViewHolder viewHolder = new CardViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        PlaceCard card = mCards.get(position);

        holder.mImageCard.setContent(card.mContent);
        holder.mImageCard.setTitle(card.mTitle);
        holder.mImageCard.setButton(card.mButtonText);
        holder.mImageCard.setImageUrl(card.mImageUrl);
    }

    @Override
    public int getItemCount() {
        if (mCards == null) {
            return 0;
        } else {
            return mCards.size();
        }
    }

    // endregion
    ///////////

    ///////////
    // region Inner Classes

    public static class CardViewHolder extends RecyclerView.ViewHolder {

        private ImageCard mImageCard;

        public CardViewHolder(View itemView) {
            super(itemView);
            mImageCard = (ImageCard) itemView.findViewById(R.id.list_item_place_card_ImageCard);
        }
    }

    // endregion
    ///////////
}
