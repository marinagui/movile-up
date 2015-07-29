package com.movile.up.seriestracker.loader;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;


import com.movile.up.seriestracker.listener.FavoritesListener;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    private Context mContext;
    private FavoritesListener mListener;

    public FavoritesLoaderCallback(Context context, FavoritesListener listener) {
        mContext = context;
        mListener = listener;
    }

    public Loader<Cursor> onCreateLoader(int id, Bundle bundle) {
        return new FavoritesLoader(mContext);
    }

    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListener.onFavoritesSuccess(data);
    }

    public void onLoaderReset(Loader<Cursor> loader) {}

}
