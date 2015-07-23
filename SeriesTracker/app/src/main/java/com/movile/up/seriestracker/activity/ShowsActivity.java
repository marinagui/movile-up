package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.ShowsGridAdapter;
import com.movile.up.seriestracker.listener.ShowsClickListener;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowsPresenter;
import com.movile.up.seriestracker.view.ShowsView;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowsActivity extends AppCompatActivity implements ShowsView, ShowsClickListener {

    private static final String TAG = ShowsActivity.class.getSimpleName();

    private ShowsPresenter mPresenter;
    private ShowsGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_grid_activity);

        configureShowsGrid();

        mPresenter = new ShowsPresenter(this,this);
        mPresenter.loadShows();
    }

    private void configureShowsGrid () {
        GridView gridview = (GridView) findViewById(R.id.shows_grid_view);
        mAdapter = new ShowsGridAdapter(this,this);
        gridview.setAdapter(mAdapter);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.updateShowsList(shows);
    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, EpisodeDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW, show.title());
        startActivity(intent);
    }
}
