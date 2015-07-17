package com.movile.up.seriestracker.async_task;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;

import com.movile.up.seriestracker.listener.OnEpisodeListener;

import java.io.IOException;
import java.net.URL;

/**
 * Created by android on 7/17/15.
 */
public class RemoteImageAsyncTask extends AsyncTask<String, Void, Bitmap> {

    private OnEpisodeListener mListener;
    private Context mContext;
    private static final String TAG = RemoteImageAsyncTask.class.getSimpleName();

    public RemoteImageAsyncTask(Context context, OnEpisodeListener listener) {
        mListener = listener;
        mContext = context;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        String url = params[0];
        Bitmap bitmap = null;
        try {
            bitmap = BitmapFactory.decodeStream(new URL(url).openStream());
        } catch (IOException e) {
            Log.e(TAG, "Error fetching image from " + url, e);
        }
        return bitmap;
    }
    @Override
    protected void onPostExecute(Bitmap bitmap) {
        mListener.onLoadImageSuccess(bitmap);
    }
}
