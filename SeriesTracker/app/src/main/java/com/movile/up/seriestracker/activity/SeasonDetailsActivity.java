package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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
import com.movile.up.seriestracker.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.up.seriestracker.view.SeasonDetailsView;

import java.text.DecimalFormat;
import java.util.List;


public class SeasonDetailsActivity extends BaseNavigationToolbarActivity implements SeasonDetailsView {
    private EpisodesAdapter mAdapter;
    private View headerView;
    private String mShow;
    private long mSeason;

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
                .load(season.images().thumb().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into((ImageView) findViewById(R.id.season_details_thumbnail));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);

        configureEpisodesList();
        configureToolbar();

        mShow = "breaking-bad";
        mSeason = (long)5;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Season "+(mSeason));
        }

        SeasonDetailsPresenter mPresenter = new SeasonDetailsPresenter(this, this);

        showLoading();
        mPresenter.loadSeasonDetails(mShow, mSeason);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_season_details, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
