package com.azandria.whatswhere.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.azandria.whatswhere.R;
import com.azandria.whatswhere.places.Place;


public class IntentManager {

    /**
     * Builds and starts an Intent to open the Place in
     * a maps app.
     * @param place The place to open in Maps.
     * @param context A context through which to build & start the intent.
     * @param view A view to start a Snackbar message, if necessary. Optional.
     */
    public static void startMapIntent(Place place, Context context, View view) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(place.mMapUri);

        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else if (view != null) {
            final Snackbar snackbar = Snackbar.make(view, R.string.Snackbar_NoMapsApp, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.OK, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }

    /**
     * Start an intent that just parses a String as a Uri and
     * launches it to the system. Typically will open in web
     * browser.
     * @param url The url to attempt to open
     * @param context A context through which to start the new activity
     */
    public static void startUrlIntent(String url, Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(intent);
    }

    /**
     * Create an intent to open the Hoppe flight finder app. Note:
     * does not do any pre-filled searching, because being able to
     * search for a destination in that app depends on the user having
     * already set up an account.
     * @param context The context through which to start the new activity.
     * @param view A view that can be associated with a Snackbar error message.
     */
    public static void startHopperIntent(final Context context, View view) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage("com.hopper.mountainview.play");

            if (intent != null) {
                context.startActivity(intent);
            } else {
                showHopperError(view);
            }
        } catch (ActivityNotFoundException e) {
            showHopperError(view);
        }
    }


    ///////////
    // region Private Methods

    private static void showHopperError(final View view) {
        if (view != null) {
            final Snackbar snackbar = Snackbar.make(view, R.string.Snackbar_NoHopper, Snackbar.LENGTH_LONG);
            snackbar.setAction(R.string.Download, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("market://details?id=com.hopper.mountainview.play"));
                    view.getContext().startActivity(intent);

                    snackbar.dismiss();
                }
            });
            snackbar.show();
        }
    }

    // endregion
    ///////////
}
