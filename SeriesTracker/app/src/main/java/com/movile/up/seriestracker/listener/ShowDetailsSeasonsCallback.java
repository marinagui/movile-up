package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Season;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public interface ShowDetailsSeasonsCallback {
    void onShowDetailsSeasonsSuccess(List<Season> seasons);
}
