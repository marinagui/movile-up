package com.movile.up.seriestracker.loader;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.up.seriestracker.listener.OnFavoriteListener;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/27/15.
 */
public class FavoriteLoaderCallback implements LoaderManager.LoaderCallbacks<Favorite> {
    private Context mContext;
    private OnFavoriteListener mListener;
    private String mSlug;

    public FavoriteLoaderCallback(Context context, OnFavoriteListener listener, String slug) {
        mContext = context;
        mListener = listener;
        mSlug = slug;
    }

    public Loader<Favorite> onCreateLoader(int id, Bundle bundle) {
        return new FavoriteLoader(mContext,mSlug);
    }

    public void onLoadFinished(Loader<Favorite> loader, Favorite favorite) {
        mListener.onLoadFavoriteSuccess(favorite);
    }

    public void onLoaderReset(Loader<Favorite> loader) {

    }
}