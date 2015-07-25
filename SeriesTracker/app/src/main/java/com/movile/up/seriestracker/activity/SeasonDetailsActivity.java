package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.EpisodesAdapter;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.listener.SeasonDetailsClickEpisode;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.up.seriestracker.view.SeasonDetailsView;

import java.text.DecimalFormat;
import java.util.List;


public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView,SeasonDetailsClickEpisode {

    public static final String EXTRA_SHOW = "season_details_extra_show";
    public static final String EXTRA_SEASON = "season_details_extra_season";

    private static final String TAG = SeasonDetailsActivity.class.getSimpleName();

    private EpisodesAdapter mAdapter;
    private View headerView;
    private String mShow;
    private long mSeason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);

        configureEpisodesList();
        configureToolbar();

        getIntentExtra();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Season "+mSeason);
        }

        SeasonDetailsPresenter mPresenter = new SeasonDetailsPresenter(this, this);

        showLoading();
        mPresenter.loadSeasonDetails(mShow, mSeason);
    }

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        mAdapter.updateEpisodesList(episodes);
        hideLoading();
    }

    private void configureEpisodesList () {
        ListView episodesList = (ListView) findViewById(R.id.episodes_list_view);
        headerView = LayoutInflater.from(this)
                .inflate(R.layout.season_details_header, episodesList, false);

        mAdapter = new EpisodesAdapter(this,this);

        episodesList.addHeaderView(headerView, null, false);
        episodesList.setAdapter(mAdapter);
    }

    @Override
    public void onEpisodeClick(Episode episode) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW, mShow);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_SEASON, mSeason);
        intent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODE, episode.number());
        startActivity(intent);
    }

    @Override
    public void displaySeason(Season season) {
        ((TextView)headerView.findViewById(R.id.season_details_rating)).setText(new DecimalFormat("#.#").format(season.rating()));

        Glide
                .with(SeasonDetailsActivity.this)
                .load(season.images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.season_details_screenshot));

        Glide
                .with(SeasonDetailsActivity.this)
                .load(season.images().thumb().get(Images.ImageSize.THUMB))
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.season_details_thumbnail));
    }


    private void getIntentExtra() {
        mShow = getIntent().getExtras().getString(EXTRA_SHOW);
        mSeason = getIntent().getExtras().getLong(EXTRA_SEASON);
    }
}
