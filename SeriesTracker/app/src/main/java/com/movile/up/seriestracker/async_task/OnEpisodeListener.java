package com.movile.up.seriestracker.async_task;

import com.movile.up.seriestracker.model.Episode;
/**
 * Created by Marina on 16/07/2015.
 */

 public interface OnEpisodeListener {
    public void onLoadEpisodeSuccess(Episode episode);
 }
