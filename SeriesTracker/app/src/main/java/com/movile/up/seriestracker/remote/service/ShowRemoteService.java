package com.movile.up.seriestracker.remote.service;

import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.util.ApiConfiguration;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by android on 7/22/15.
 */
public interface ShowRemoteService {
    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}?extended=full,images")
    void getShowDetails(
            @Path("show") String show,
            Callback<Show> callback);


    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/related?limit=6&extended=full,images")
    void getRelatedShows(
            @Path("show") String show,
            Callback<List<Show>> callback);
}
