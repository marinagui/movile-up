package com.movile.up.seriestracker.listener;

import android.database.Cursor;

/**
 * Created by Marina on 28/07/2015.
 */
public interface FavoritesListener {
    void onFavoritesSuccess(Cursor favorites);
}
