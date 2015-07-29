package com.movile.up.seriestracker.presenter;


import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;

import com.movile.up.seriestracker.listener.FavoritesListener;
import com.movile.up.seriestracker.loader.FavoritesLoaderCallback;
import com.movile.up.seriestracker.view.FavoritesView;

/**
 * Created by Marina on 28/07/2015.
 */
public class FavoritesPresenter implements FavoritesListener {

    private FavoritesView mView;
    private Context mContext;

    public FavoritesPresenter(Context context,FavoritesView mView) {
        this.mView = mView;
        this.mContext = context;
    }

    @Override
    public void onFavoritesSuccess(Cursor favorites) {
        mView.displayFavorites(favorites);
    }

    public void loadFavorites(LoaderManager loaderManager){
        loaderManager.initLoader(
                0, null, new FavoritesLoaderCallback(mContext, this)
        ).forceLoad();
    }
}
