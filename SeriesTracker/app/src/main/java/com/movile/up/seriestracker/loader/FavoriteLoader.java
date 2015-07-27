package com.movile.up.seriestracker.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.up.seriestracker.database.dao.FavoriteDAO;
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
        FavoriteDAO favDao = new FavoriteDAO(mContext);
        return favDao.query(slug);
    }
}
