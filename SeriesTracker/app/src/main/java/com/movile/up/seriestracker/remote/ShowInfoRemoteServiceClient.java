package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.listener.ShowInfoCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.service.ShowRemoteService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/30/15.
 */
public class ShowInfoRemoteServiceClient {
    private static final String TAG = ShowInfoRemoteServiceClient.class.getSimpleName();

    public void loadInfo(Context context, final ShowInfoCallback mListener, final String show) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();
        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getShowDetails(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                mListener.onShowInfoSuccess(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching show info", error.getCause());
            }
        });
    }
}
