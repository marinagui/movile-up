package com.movile.up.seriestracker.async_task;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database.db_flow.dao.FavoriteDAO;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by Marina on 27/07/2015.
 */
public class FavoriteAddAsyncTask extends AsyncTask<Favorite, Void, Void> {
    @Override
    protected Void doInBackground(Favorite... params) {
        new FavoriteDAO().save(params[0]);
        return null;
    }
}
