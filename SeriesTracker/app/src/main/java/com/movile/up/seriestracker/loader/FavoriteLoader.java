package com.movile.up.seriestracker.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.up.seriestracker.database.db_flow.dao.FavoriteDAO;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/27/15.
 */
public class FavoriteLoader extends AsyncTaskLoader<Favorite> {

    private Context mContext;
    private String slug;

    public FavoriteLoader(Context context, String slug) {
        super(context);
        mContext = context;
        this.slug = slug;
    }
    @Override
    public Favorite loadInBackground() {
        return new FavoriteDAO().query(slug);
    }
}
