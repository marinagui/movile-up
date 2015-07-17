package com.movile.up.seriestracker.presenter;

import android.graphics.Bitmap;

import com.movile.up.seriestracker.listener.OnEpisodeListener;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.view.EpisodeDetailsView;

import javax.security.auth.callback.Callback;

/**
 * Created by android on 7/17/15.
 */
public class EpisodeDetailsPresenter implements OnEpisodeListener {

    @Override
    public void onLoadEpisodeSuccess(Episode episode) {
        //chama o display do view
    }

    @Override
    public void onLoadImageSuccess(Bitmap image) {

    }
}
