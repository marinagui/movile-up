package com.movile.up.seriestracker.activity;

import android.graphics.Bitmap;
import android.os.PersistableBundle;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.interfaces.EpisodeRemoteService;
import com.movile.up.seriestracker.listener.OnEpisodeListener;
import com.movile.up.seriestracker.loader.EpisodeLoaderCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.util.FormatUtil;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class EpisodeDetailsActivity extends AppCompatActivity {

    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);
    }

    @Override
    protected void onStart() {
        super.onStart();

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(this.getString(R.string.api_url_base)).build();
        final OnEpisodeListener mCallback = new OnEpisodeListener() {
            @Override
            public void onLoadEpisodeSuccess(Episode episode) {

                try {
                    ((TextView) findViewById(R.id.episode_details_title)).setText(episode.title());
                    ((TextView) findViewById(R.id.episode_details_summary)).setText(episode.overview());

                    Date formattedDate = FormatUtil.formatDate(episode.firstAired());
                    ((TextView) findViewById(R.id.episode_details_dateTime)).setText(FormatUtil.formatDate(formattedDate));

                    Glide
                            .with(EpisodeDetailsActivity.this)
                            .load(episode.images().screenshot().get(Images.ImageSize.FULL))
                            .placeholder(R.drawable.highlight_placeholder)
                            .centerCrop()
                            .into((ImageView) findViewById(R.id.episode_details_screenshot));
                } catch(Exception e) {}
            }

            @Override
            public void onLoadImageSuccess(Bitmap image) {

            }
        };

        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);
        service.getEpisodeDetails("breaking-bad", (long)1, (long)1, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mCallback.onLoadEpisodeSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });


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
