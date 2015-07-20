package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.EpisodesAdapter;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.presenter.SeasonDetailsPresenter;
import com.movile.up.seriestracker.view.SeasonDetailsView;

import java.util.List;


public class SeasonDetailsActivity extends AppCompatActivity implements SeasonDetailsView {

    private SeasonDetailsPresenter mPresenter;

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        ListView view = (ListView) findViewById(R.id.episodes_list_view);
        //view.addHeaderView(headerView, null, false);
        //view.setEmptyView(emptyView);
        //view.addFooterView(footerView, null, false);

        EpisodesAdapter adapter = new EpisodesAdapter(this);
        adapter.updateEpisodesList(episodes);
        view.setAdapter(adapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_details_activity);

        mPresenter = new SeasonDetailsPresenter(this,this);
        mPresenter.loadSeasonDetails("breaking-bad",(long)5);
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
