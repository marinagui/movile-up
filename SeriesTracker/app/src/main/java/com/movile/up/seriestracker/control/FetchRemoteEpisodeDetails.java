package com.movile.up.seriestracker.control;

import android.content.Context;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.converter.ModelConverter;

/**
 * Created by android on 7/16/15.
 */
public class FetchRemoteEpisodeDetails {

    private static final String TAG = FetchLocalEpisodeDetails.class.getSimpleName();

    public Episode get(Context context, String url) {
        Episode episode = null;
        InputStreamReader reader = null;

        try {
            // ?extended=full,images
            HttpURLConnection connection = configureConnection(context, url);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading remote content", e);
        } finally {
            if(reader != null)
                reader.close();
        }

        return episode;
    }

    public HttpURLConnection configureConnection(Context context, String url) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setReadTimeout(context.getResources().getInteger(R.integer.api_timeout_read));
            connection.setConnectTimeout(context.getResources().getInteger(R.integer.api_timeout_connect));
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("trakt-api-version", context.getString(R.string.api_version));
            connection.setRequestProperty("trakt-api-key", context.getString(R.string.api_key));

            return connection;

        } catch (Exception e) {}

        return null;
    }

}
