package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.SeasonDetailsListener;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.remote.EpisodeRemoteServiceClient;
import com.movile.up.seriestracker.view.SeasonDetailsView;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class SeasonDetailsPresenter implements SeasonDetailsListener {

    private SeasonDetailsView mView;
    private Context mContext;

    public SeasonDetailsPresenter(Context context,SeasonDetailsView mView) {
        this.mView = mView;
        this.mContext = context;
    }

    @Override
    public void onSeasonDetailsSuccess(List<Episode> episodes) {
        mView.displayEpisodes(episodes);
    }

    public void loadSeasonDetails(String show, Long season){
        new EpisodeRemoteServiceClient().loadSeasonDetails(mContext, this, show, season);
    }
}
