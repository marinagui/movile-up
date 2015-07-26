package com.movile.up.seriestracker.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.model.ShowUpdate;

/**
 * Created by android on 7/23/15.
 */
public class ShowUpdateReceiver extends BroadcastReceiver {

    private static final String TAG = ShowUpdateReceiver.class.getSimpleName();
    public static final String EXTRA_UPDATE = "extra_update";

    public void onReceive(Context context, Intent intent) {

        ShowUpdate update = (ShowUpdate) intent.getExtras().get(EXTRA_UPDATE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(update.title())
                .setContentText(update.message())
                .setContentIntent(this.getPendingIntent(context,update))
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(update.message()));

        Notification notification = builder.build();

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, notification);

        Log.d(TAG, update.message());

    }



    private PendingIntent getPendingIntent (Context mContext, ShowUpdate update) {
        Intent intent = new Intent(mContext, ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW, update.show());
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
        stackBuilder.addParentStack(ShowDetailsActivity.class);
        stackBuilder.addNextIntent(intent);

        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
    }


}