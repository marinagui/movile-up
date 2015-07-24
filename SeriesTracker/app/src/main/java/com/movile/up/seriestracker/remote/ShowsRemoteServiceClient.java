package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.ShowsCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.service.ShowsRemoteService;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/23/15.
 */
public class ShowsRemoteServiceClient {


    private static final String TAG = ShowsRemoteServiceClient.class.getSimpleName();

    public void loadShows(Context context, final ShowsCallback mListener) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        ShowsRemoteService service = mAdapter.create(ShowsRemoteService.class);

        service.getShows(new Callback<List<Show>>() {
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
