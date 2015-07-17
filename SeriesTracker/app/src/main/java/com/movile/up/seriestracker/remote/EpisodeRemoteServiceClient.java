package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.location.GpsStatus;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.Episode;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/17/15.
 */
public class EpisodeRemoteServiceClient {
    private static final String TAG = EpisodeRemoteServiceClient.class.getSimpleName();

    public void loadEpisodeDetails(Context context, String show, Long season, Long episode) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mCallback.onLoadEpisodeSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }


}
