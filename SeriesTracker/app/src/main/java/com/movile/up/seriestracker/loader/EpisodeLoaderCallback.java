package com.movile.up.seriestracker.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.up.seriestracker.async_task.OnEpisodeListener;
import com.movile.up.seriestracker.model.Episode;

/**
 * Created by android on 7/16/15.
 */
public class EpisodeLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {
    private OnEpisodeListener mListener;
    private Context mContext;
    private String mUrl;

    public EpisodeLoaderCallback(Context context, OnEpisodeListener listener, String url) {
        mContext = context;
        mListener = listener;
        mUrl = url;
    }

    public Loader<Episode> onCreateLoader(int id, Bundle bundle) {
        
    }

    public void onLoadFinished(Loader<Episode> loader, Episode episode) {
        mListener.onLoadEpisodeSuccess(episode);
    }

    public void onLoaderReset(Loader<Episode> loader) {

    }
}
