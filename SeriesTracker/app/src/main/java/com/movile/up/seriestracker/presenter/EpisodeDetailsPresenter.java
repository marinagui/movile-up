package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.EpisodeDetailsCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.remote.EpisodeRemoteServiceClient;
import com.movile.up.seriestracker.view.EpisodeDetailsView;


/**
 * Created by android on 7/17/15.
 */
public class EpisodeDetailsPresenter implements EpisodeDetailsCallback {

    private EpisodeDetailsView mView;
    private Context mContext;

    public EpisodeDetailsPresenter(Context context,EpisodeDetailsView mView) {
        this.mView = mView;
        this.mContext = context;
    }

    @Override
    public void onEpisodeDetailsSuccess(Episode episode) {
        mView.displayEpisode(episode);
    }

    public void loadEpisodeDetails(String show, Long season, Long episode){
        new EpisodeRemoteServiceClient().loadEpisodeDetails(mContext, this, show, season, episode);
    }
}
