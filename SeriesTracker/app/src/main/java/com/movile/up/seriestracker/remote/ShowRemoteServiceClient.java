package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.listener.SeasonDetailsListener;
import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/22/15.
 */
public class ShowRemoteServiceClient {

    private static final String TAG = EpisodeRemoteServiceClient.class.getSimpleName();

    public void loadShowDetails(Context context, final ShowDetailsCallback mListener, final String show) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getShowDetails(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                mListener.onShowDetailsSuccess(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }
}
