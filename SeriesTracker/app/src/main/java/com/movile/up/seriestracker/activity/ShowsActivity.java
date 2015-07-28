package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridView;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationDrawerActivity;
import com.movile.up.seriestracker.adapter.FavoritesAdapter;
import com.movile.up.seriestracker.adapter.ShowsGridAdapter;
import com.movile.up.seriestracker.listener.ShowsClickListener;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowsPresenter;
import com.movile.up.seriestracker.view.ShowsView;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowsActivity extends BaseNavigationDrawerActivity implements ShowsView, ShowsClickListener {

    private ShowsPresenter mPresenter;
    private ShowsGridAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_grid_activity);

        configureNavigation();
        configureShowsGrid();
        configureFavoritesList();

        mPresenter = new ShowsPresenter(this,this);
        mPresenter.loadShows();
    }

    private void configureShowsGrid () {
        GridView gridview = (GridView) findViewById(R.id.shows_grid_view);
        mAdapter = new ShowsGridAdapter(this,this);
        gridview.setAdapter(mAdapter);
    }

    private void configureFavoritesList () {
        ListView favoritesList = (ListView) findViewById(R.id.favorites_list_drawer);
        View empty = LayoutInflater.from(this)
                .inflate(R.layout.favorites_list_content_empty, favoritesList, false);

        //FavoritesAdapter adapter = new FavoritesAdapter(this,this);

        favoritesList.setEmptyView(empty);
        //favoritesList.setAdapter(adapter);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.updateShowsList(shows);
    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW, show.ids().slug());
        startActivity(intent);
    }
}
