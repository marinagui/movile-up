package com.movile.up.seriestracker.async_task;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.up.seriestracker.control.FetchLocalEpisodeDetails;
import com.movile.up.seriestracker.model.Episode;

public class EpisodeAsyncTask extends AsyncTask<Void, Void, Episode> {
    private OnEpisodeListener mListener;
    private Context mContext;

    public EpisodeAsyncTask(Context context, OnEpisodeListener listener) {
        mContext = context;
        mListener = listener;
    }

    protected Episode doInBackground(Void... params) {
        return new FetchLocalEpisodeDetails().get(mContext);
    }

    protected void onPostExecute(Episode episode) {
        mListener.onLoadEpisodeSuccess(episode);
    }
}