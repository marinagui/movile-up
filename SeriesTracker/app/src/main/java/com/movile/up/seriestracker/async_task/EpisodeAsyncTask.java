package com.movile.up.seriestracker.async_task;

import android.os.AsyncTask;

import com.movile.up.seriestracker.model.Episode;

public class EpisodeAsyncTask extends AsyncTask<Void, Void, Episode> {
    private OnOperationListener mListener;

    public EpisodeAsyncTask(OnOperationListener listener) {
        mListener = listener;
    }

    protected void onPreExecute() { }

    protected Episode doInBackground(Void... params) {

    }

    protected void onPostExecute(Episode result) {
        mListener.onOperationSuccess();
    }
}