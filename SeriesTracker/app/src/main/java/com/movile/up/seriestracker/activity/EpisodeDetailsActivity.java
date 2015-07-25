package com.movile.up.seriestracker.activity;

import android.os.PersistableBundle;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Date;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.presenter.EpisodeDetailsPresenter;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.view.EpisodeDetailsView;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;


public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView{

    public static final String EXTRA_SHOW = "episode_details_show";
    public static final String EXTRA_SEASON = "episode_details_season";
    public static final String EXTRA_EPISODE = "episode_details_episode";
    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();

    private String mShow;
    private long mSeason, mEpisode;

    private EpisodeDetailsPresenter mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        getIntentExtra();

        configureToolbar();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("S"+mSeason+"E"+mEpisode);
        }

        mPresenter = new EpisodeDetailsPresenter(this,this);
        showLoading();
        mPresenter.loadEpisodeDetails(mShow,mSeason,mEpisode);
    }

    @Override
    public void displayEpisode(Episode episode) {
        try {
            ((TextView) findViewById(R.id.episode_details_title)).setText(episode.season()+"x"+episode.number()+" - "+episode.title());
            ((TextView) findViewById(R.id.episode_details_summary)).setText(episode.overview());

            Date formattedDate = FormatUtil.formatDate(episode.firstAired());
            ((TextView) findViewById(R.id.episode_details_dateTime)).setText(FormatUtil.formatDate(formattedDate));

            Glide
                    .with(EpisodeDetailsActivity.this)
                    .load(episode.images().screenshot().get(Images.ImageSize.FULL))
                    .placeholder(R.drawable.highlight_placeholder)
                    .centerCrop()
                    .into((ImageView) findViewById(R.id.episode_details_screenshot));

            hideLoading();
        } catch(Exception e) {
            Log.e(TAG, "Error setting values", e.getCause());
        }
    }

    private void getIntentExtra () {
        mShow = getIntent().getExtras().getString("EXTRA_SHOW");
        mSeason = getIntent().getExtras().getLong("EXTRA_SEASON");
        mEpisode = getIntent().getExtras().getLong("EXTRA_EPISODE");
    }

    @Override
    protected void onStart() {
        super.onStart();
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
