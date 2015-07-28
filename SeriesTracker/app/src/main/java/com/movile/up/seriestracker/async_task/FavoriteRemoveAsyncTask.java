package com.movile.up.seriestracker.async_task;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database.db_flow.dao.FavoriteDAO;


/**
 * Created by Marina on 27/07/2015.
 */
public class FavoriteRemoveAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... params) {
        new FavoriteDAO().delete(params[0]);
        return null;
    }
}
