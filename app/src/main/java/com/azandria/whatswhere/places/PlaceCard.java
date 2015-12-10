package com.azandria.whatswhere.places;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class PlaceCard {

    public String mTitle;
    public String mButtonText;
    public String mContent;
    public String mImageUrl;
    public String mUrl;

    public static List<PlaceCard> fromList(JSONArray array) throws JSONException {
        List<PlaceCard> cards = new LinkedList<>();
        if (array != null) {
            for (int i = 0; i < array.length(); i++) {
                PlaceCard card = PlaceCard.from(array.getJSONObject(i));
                cards.add(card);
            }
        }
        return cards;
    }

    public static PlaceCard from(JSONObject json) throws JSONException {
        PlaceCard card = null;

        if (json != null) {
            card = new PlaceCard();

            card.mButtonText = json.optString("button_text");
            card.mContent = json.optString("content");
            card.mImageUrl = json.optString("image");
            card.mTitle = json.optString("title");
            card.mUrl = json.optString("url");
        }

        return card;
    }
}
