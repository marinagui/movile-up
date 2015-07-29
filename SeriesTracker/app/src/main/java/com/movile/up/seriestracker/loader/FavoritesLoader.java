package com.movile.up.seriestracker.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import com.movile.up.seriestracker.database.db_flow.dao.FavoriteDAO;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesLoader extends AsyncTaskLoader<Cursor> {

    public FavoritesLoader(Context context) {
        super(context);
    }

    @Override
    public Cursor loadInBackground() {
        return new FavoriteDAO().all();
    }
}
