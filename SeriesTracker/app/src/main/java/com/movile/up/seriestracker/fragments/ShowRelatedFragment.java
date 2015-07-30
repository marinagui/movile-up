package com.movile.up.seriestracker.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.GridView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.adapter.ShowRelatedGridAdapter;
import com.movile.up.seriestracker.listener.ShowsClickListener;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowDetailsRelatedPresenter;
import com.movile.up.seriestracker.view.ShowDetailsRelatedView;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public class ShowRelatedFragment extends android.support.v4.app.Fragment implements ShowDetailsRelatedView, ShowsClickListener {

    public static final String SHOW_ARGUMENT = "show";
    private ShowDetailsRelatedPresenter mPresenter;
    private ShowRelatedGridAdapter mAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        configureShowsGrid();

        mPresenter = new ShowDetailsRelatedPresenter(this.getActivity(), this);
        mPresenter.loadRelatedShows(getArguments().getString(SHOW_ARGUMENT));
    }

    private void configureShowsGrid () {
        GridView gridview = (GridView) getActivity().findViewById(R.id.show_details_related_shows);
        mAdapter = new ShowRelatedGridAdapter(this.getActivity(),this);
        gridview.setAdapter(mAdapter);
    }

    @Override
    public void displayRelatedShows(List<Show> relatedShows) {
        mAdapter.updateRelatedShowsList(relatedShows);
    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this.getActivity(), ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW, show.ids().slug());
        startActivity(intent);
    }
}
