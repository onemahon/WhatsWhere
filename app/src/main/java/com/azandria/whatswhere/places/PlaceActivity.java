package com.azandria.whatswhere.places;

import android.support.v4.app.Fragment;

import com.azandria.datadude.visual.BaseActivity;


public class PlaceActivity extends BaseActivity {
    @Override
    protected Fragment getRootFragment() {
        return PlaceFragment.newInstance();
    }
}
