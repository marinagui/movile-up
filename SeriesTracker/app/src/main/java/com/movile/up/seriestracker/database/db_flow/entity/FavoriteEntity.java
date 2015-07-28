package com.movile.up.seriestracker.database.db_flow.entity;

import android.provider.BaseColumns;

import com.movile.up.seriestracker.database.db_flow.SeriesTrackerDatabase;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by android on 7/27/15.
 */
@Table(databaseName = SeriesTrackerDatabase.NAME)
public class FavoriteEntity extends BaseModel {
    @Column(name = BaseColumns._ID)
    @PrimaryKey(autoincrement = true)
    Long id;

    @Column
    String slug;

    @Column
    String title;

    public FavoriteEntity(String slug, String title) {
        this.slug = slug;
        this.title = title;
    }

    public FavoriteEntity() {
    }

    public String slug() {
        return slug;
    }

    public String title() {
        return title;
    }
}
