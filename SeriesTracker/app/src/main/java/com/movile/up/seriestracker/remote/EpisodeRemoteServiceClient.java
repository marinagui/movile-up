package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.EpisodeDetailsCallback;
import com.movile.up.seriestracker.listener.SeasonDetailsListener;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.remote.service.EpisodeRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/17/15.
 */
public class EpisodeRemoteServiceClient {
    private static final String TAG = EpisodeRemoteServiceClient.class.getSimpleName();

    public void loadEpisodeDetails(Context context, final EpisodeDetailsCallback mListener, String show, Long season, Long episode) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mListener.onEpisodeDetailsSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }


    public void loadSeasonDetails(Context context, final SeasonDetailsListener mListener, String show, Long season) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getSeasonDetails(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {
                mListener.onSeasonDetailsEpisodesSuccess(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }

}
