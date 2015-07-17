package com.movile.up.seriestracker.listener;

import android.graphics.Bitmap;

import com.movile.up.seriestracker.model.Episode;
/**
 * Created by Marina on 16/07/2015.
 */

 public interface OnEpisodeListener {
    void onLoadEpisodeSuccess(Episode episode);
    void onLoadImageSuccess(Bitmap image);
 }
