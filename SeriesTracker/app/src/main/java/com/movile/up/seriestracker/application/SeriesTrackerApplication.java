package com.movile.up.seriestracker.application;

import android.app.Application;

import com.movile.up.seriestracker.activity.ShowsActivity;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by android on 7/24/15.
 */
public class SeriesTrackerApplication extends Application {
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
