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
    private OnCardClickedListener mCardClickedListener;

    // prevents weird state caused by click listener not being set
    // until after views are already bound.
    private OnCardClickedListener mCardClickedListenerWrapper = new OnCardClickedListener() {
        @Override
        public void onCardClick(PlaceCard card) {
            if (mCardClickedListener != null) {
                mCardClickedListener.onCardClick(card);
            }
        }
    };

    // endregion
    ///////////

    ///////////
    // region Public Methods

    public void setCards(List<PlaceCard> cards) {
        this.mCards = cards;
        notifyDataSetChanged();
    }

    public void setOnCardClickedListener(OnCardClickedListener listener) {
        mCardClickedListener = listener;
    }

    // endregion
    ///////////

    ///////////
    // region Parent Class Overrides

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_place_card, parent, false);
        return new CardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        PlaceCard card = mCards.get(position);

        holder.mImageCard.setContent(card.mContent);
        holder.mImageCard.setTitle(card.mTitle);
        holder.mImageCard.setButton(card.mButtonText);
        holder.mImageCard.setImageUrl(card.mImageUrl);

        holder.setOnCardClickedListener(card, mCardClickedListenerWrapper);
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
        private PlaceCard mCard;
        private OnCardClickedListener mCardClickedListener;

        public CardViewHolder(View itemView) {
            super(itemView);
            mImageCard = (ImageCard) itemView.findViewById(R.id.list_item_place_card_ImageCard);

            // Listener added inside the ViewHolder to ensure it's not wasted
            // every time a view is re-bound.
            mImageCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mCardClickedListener != null && mCard != null) {
                        mCardClickedListener.onCardClick(mCard);
                    }
                }
            });
        }

        public void setOnCardClickedListener(PlaceCard card, OnCardClickedListener listener) {
            mCard = card;
            mCardClickedListener = listener;
        }
    }

    public interface OnCardClickedListener {
        void onCardClick(PlaceCard card);
    }

    // endregion
    ///////////
}
