package com.movile.up.seriestracker.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.receiver.ShowUpdateReceiver;
import com.movile.up.seriestracker.remote.UpdatesRemoteServiceClient;

/**
 * Created by android on 7/23/15.
 */
public class UpdatesService extends IntentService {

    public UpdatesService(){
        super("UpdatesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        ShowUpdate update = new UpdatesRemoteServiceClient().getLatestUpdate(this);

        SharedPreferences preferences = this.getSharedPreferences("Updates Preferences", Context.MODE_PRIVATE);
        String latestUpdate = preferences.getString("last", null);


        if (latestUpdate == null || latestUpdate.equals(update.date())) {
            Intent intentBroadcast = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");
            intentBroadcast.putExtra(ShowUpdateReceiver.EXTRA_UPDATE, update);
            sendBroadcast(intentBroadcast);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("last", update.date());
            editor.commit();
        }
    }
}
