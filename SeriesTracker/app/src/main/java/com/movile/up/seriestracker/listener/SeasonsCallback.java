package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Season;

import java.util.List;

/**
 * Created by Marina on 24/07/2015.
 */
public interface SeasonsCallback {
    void onSeasonsSuccess(List<Season> seasons);
}
