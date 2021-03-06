package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Season;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public interface SeasonDetailsListener {
    void onSeasonDetailsSuccess(Season season);
    void onSeasonDetailsEpisodesSuccess(List<Episode> episodes);
}
