package com.azandria.whatswhere.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.azandria.whatswhere.R;


public class IntentManager {
    /**
     * Start an intent that just parses a String as a Uri and
     * launches it to the system. Typically will open in web
     * browser.
     * @param url The url to attempt to open
     * @param context A context through which to start the new activity
     */
    public static void startUrlIntent(String url, Context context, View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (view != null) {
            final Snackbar snackbar = Snackbar.make(view, R.string.Snackbar_CouldntOpenApp, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.OK, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }
}
