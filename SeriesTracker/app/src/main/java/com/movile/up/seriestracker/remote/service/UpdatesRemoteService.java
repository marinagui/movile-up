package com.movile.up.seriestracker.remote.service;

import com.movile.up.seriestracker.model.ShowUpdate;
import retrofit.http.GET;

/**
 * Created by android on 7/23/15.
 */
public interface UpdatesRemoteService {

        @GET("/latestUpdate.json")
        ShowUpdate getLatest();

}
