package com.movile.up.seriestracker.remote;

import android.content.Context;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.remote.service.UpdatesRemoteService;


import retrofit.RestAdapter;


/**
 * Created by android on 7/24/15.
 */
public class UpdatesRemoteServiceClient {

    public ShowUpdate getLatestUpdate(Context context) {

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_updates)).build();
        UpdatesRemoteService service = mAdapter.create(UpdatesRemoteService.class);

        return service.getLatest();
    }
}
