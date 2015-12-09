package com.azandria.whatswhere.places;

import android.support.v4.app.Fragment;

import com.azandria.datadude.visual.SingleFragmentActivity;

public class PlaceActivity extends SingleFragmentActivity {
    @Override
    protected Fragment getRootFragment() {
        return PlaceFragment.newInstance();
    }
}
