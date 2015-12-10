package com.azandria.whatswhere.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TextView;

import com.azandria.whatswhere.R;
import com.squareup.picasso.Picasso;

/**
 * A custom implementation of a CardView that just includes a
 * full-width ImageView and a right-aligned TextView below it,
 * each of which can be set via custom attributes.
 */
public class ImageCard extends CardView {

    ///////////
    // region Member Variables

    private int mImageDrawableResource;
    private String mImageUrl;
    private String mTitle;
    private String mButtonText;

    private ImageView mImage;
    private TextView mTitleView;
    private TextView mButtonView;
    private TextView mContentText;

    // endregion
    ///////////

    ///////////
    // region Default Constructors

    public ImageCard(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ImageCard(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ImageCard(Context context) {
        super(context);
        init(context, null);
    }

    // endregion
    ///////////

    ///////////
    // region Public Methods

    public void setContent(String text) {
        if (mContentText != null) {
            mContentText.setText(text);

            int visibility = (TextUtils.isEmpty(text)) ? GONE : VISIBLE;
            mContentText.setVisibility(visibility);
        }
    }

    public void setTitle(String title) {
        mTitle = title;
        mTitleView.setText(mTitle);
    }

    public void setButton(String buttonText) {
        mButtonText = buttonText;
        mButtonView.setText(mButtonText);
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
        loadImage();
    }

    // endregion
    ///////////

    ///////////
    // region Private Methods

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_image_card, this);

        mContentText = (TextView) findViewById(R.id.view_image_card_Content);
        mImage = (ImageView) findViewById(R.id.view_image_card_Image);
        mTitleView = (TextView) findViewById(R.id.view_image_card_Title);
        mButtonView = (TextView) findViewById(R.id.view_image_card_Button);

        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.ImageCard, 0, 0);

            try {
                mImageDrawableResource = a.getResourceId(R.styleable.ImageCard_imageDrawableResource, 0);
                mTitle = a.getString(R.styleable.ImageCard_titleText);
                mButtonText = a.getString(R.styleable.ImageCard_buttonText);
            } finally {
                a.recycle();
            }
        }
    }

    private void loadImage() {
        if (!TextUtils.isEmpty(mImageUrl)) {
            Picasso.with(getContext()).load(mImageUrl).into(mImage);
        } else if (mImageDrawableResource != 0) {
            mImage.setImageResource(mImageDrawableResource);
        } else {
            mImage.setImageDrawable(null);
        }
    }

    // endregion
    ///////////


    ///////////
    // region Lifecycle

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (mButtonText != null) {
            mButtonView.setText(mButtonText);
        }

        if (mImageDrawableResource != 0) {
            mImage.setImageResource(mImageDrawableResource);
        }

        if (mTitle != null) {
            mTitleView.setText(mTitle);
        }
    }

    // endregion
    ///////////
}
