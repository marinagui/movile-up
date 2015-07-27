package com.movile.up.seriestracker.database.dao;

import android.content.Context;
import android.database.Cursor;

import com.movile.up.seriestracker.database.db_flow.FavoriteEntity;
import com.movile.up.seriestracker.database.db_flow.FavoriteEntity$Table;
import com.movile.up.seriestracker.model.Favorite;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;

/**
 * Created by android on 7/27/15.
 */
public class FavoriteDAODBFlow {
    private Context mContext;

    public FavoriteDAODBFlow(Context context) {
        mContext = context;
    }

    public void save(Favorite favorite) {
        FavoriteEntity entity = new FavoriteEntity(favorite.slug(), favorite.title());
        entity.save();
    }

    public Cursor all() {
        return new Select().from(FavoriteEntity.class).queryCursorList().getCursor();
    }

    /*
    public FavoriteEntity query(String slug) {
        FavoriteEntity entity = new Select()
                .from(FavoriteEntity.class)
                .where(Condition.column(FavoriteEntity$Table.SLUG).eq(slug))
                .querySingle();

        return entity;
    }*/
}
