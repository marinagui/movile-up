package com.movile.up.seriestracker.activity;

import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import java.util.Date;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.async_task.*;
import com.movile.up.seriestracker.loader.EpisodeLoaderCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.util.FormatUtil;


public class EpisodeDetailsActivity extends ActionBarActivity {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String url;
        url = this.getString(R.string.api_url_base);
        url += "/shows/breaking-bad/seasons/1/episodes/1?extended=full,images";

        getLoaderManager().initLoader(0, null, new EpisodeLoaderCallback(this, new OnEpisodeListener() {

            @Override
            public void onLoadEpisodeSuccess(Episode episode) {
                ((TextView)findViewById(R.id.episode_details_title)).setText(episode.title());
                try {
                    Date formattedDate = FormatUtil.formatDate(episode.firstAired());
                    ((TextView)findViewById(R.id.episode_details_dateTime)).setText(FormatUtil.formatDate(formattedDate));
                } catch (Exception e) {}

                ((TextView)findViewById(R.id.episode_details_summary)).setText(episode.overview());
            }
        }, url)
        ).forceLoad();


        Log.d(TAG, "onStart()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putString("episodeTitle", "title episode");
        Log.d(TAG, "onSaveInstanceState()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.get("episodeTitle");
        Log.d(TAG,savedInstanceState.getString("episodeTitle"));
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}
