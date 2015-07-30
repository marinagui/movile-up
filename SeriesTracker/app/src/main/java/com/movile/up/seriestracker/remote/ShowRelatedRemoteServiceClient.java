package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.ShowDetailsRelatedCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.service.ShowRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/30/15.
 */
public class ShowRelatedRemoteServiceClient {
    private static final String TAG = ShowRelatedRemoteServiceClient.class.getSimpleName();

    public void loadRelatedShows(Context context, final ShowDetailsRelatedCallback mListener, String show) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getRelatedShows(show, new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                mListener.onShowsSuccess(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching show", error.getCause());
            }
        });
    }
}
