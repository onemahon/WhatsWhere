package com.azandria.whatswhere.views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

import com.azandria.whatswhere.R;


public class ExpandableTextView extends TextView {

    ///////////
    // region Constants

    private static final int COLLAPSED_LINES_DEFAULT = 4;
    private static final int COLLAPSE_ANIMATION_DURATION = 200;

    // TODO this needs to be calculated more carefully, in order to accomodate text of greatly varying lengths
    private static final int MAX_EXPANDED_LINE_COUNT = 30;

    // endregion
    ///////////

    ///////////
    // region Member Variables

    private int mCollapsedLines;

    // endregion
    ///////////

    ///////////
    // region Constructors
    
    public ExpandableTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public ExpandableTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ExpandableTextView(Context context) {
        super(context);
        init(context, null);
    }
    
    // endregion
    ///////////
    
    ///////////
    // region Private Methods

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray a = context.getTheme().obtainStyledAttributes(
                    attrs, R.styleable.ImageCard, 0, 0);

            try {
                mCollapsedLines = a.getInteger(R.styleable.ExpandableTextView_collapsedMaxLines, COLLAPSED_LINES_DEFAULT);
            } finally {
                a.recycle();
            }
        }
    }

    private void switchCollapsedState() {
        if (getMaxLines() == mCollapsedLines) {
            expandTextView();
        } else {
            collapseTextView();
        }
    }

    private void expandTextView() {
        ObjectAnimator animation = ObjectAnimator.ofInt(this, "maxLines", MAX_EXPANDED_LINE_COUNT);
        animation.setDuration(COLLAPSE_ANIMATION_DURATION).start();
    }

    private void collapseTextView(){
        ObjectAnimator animation = ObjectAnimator.ofInt(this, "maxLines", mCollapsedLines);
        animation.setDuration(COLLAPSE_ANIMATION_DURATION).start();
    }

    // endregion
    ///////////

    ///////////
    // region Lifecycle

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        setMaxLines(mCollapsedLines);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                switchCollapsedState();
            }
        });
    }

    // endregion
    ///////////
}
