package com.movile.up.seriestracker.remote.service;

import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.util.ApiConfiguration;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by Marina on 24/07/2015.
 */
public interface SeasonRemoteService {
    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons?extended=full,images")
    void getSeasons(
            @Path("show") String show,
            Callback<List<Season>> callback);
}