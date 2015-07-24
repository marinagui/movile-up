package com.movile.up.seriestracker.application;

import android.app.Application;

import com.movile.up.seriestracker.activity.ShowsActivity;

/**
 * Created by android on 7/24/15.
 */
public class SeriesTrackerApplication extends Application {
    public void onCreate() {
        new ShowsActivity();
    }
}
