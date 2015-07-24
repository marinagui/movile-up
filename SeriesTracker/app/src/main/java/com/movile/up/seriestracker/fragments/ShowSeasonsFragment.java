package com.movile.up.seriestracker.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.SeasonDetailsActivity;
import com.movile.up.seriestracker.adapter.SeasonsAdapter;
import com.movile.up.seriestracker.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.ShowDetailsSeasonsPresenter;
import com.movile.up.seriestracker.view.ShowDetailsSeasonsView;

import java.util.List;

/**
 * Created by android on 7/21/15.
 */
public class ShowSeasonsFragment extends android.support.v4.app.Fragment implements ShowDetailsSeasonsView, OnSeasonClickListener {
    public static final String SHOW_ARGUMENT = "show";
    private ShowDetailsSeasonsPresenter mPresenter;
    private SeasonsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_seasons_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mPresenter = new ShowDetailsSeasonsPresenter(this.getActivity(),this);

        RecyclerView rv = (RecyclerView) getActivity().findViewById(R.id.seasons_recycler);
        rv.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mAdapter = new SeasonsAdapter(getActivity(), this);
        rv.setAdapter(mAdapter);

        mPresenter.loadShowSeasons(getArguments().getString(SHOW_ARGUMENT));
    }

    @Override
    public void displaySeasons(List<Season> seasons) {
        mAdapter.updateSeasonsList(seasons);
    }

    @Override
    public void onSeasonClick(Season season) {
        Intent intent = new Intent(this.getActivity(), SeasonDetailsActivity.class);
        intent.putExtra(SeasonDetailsActivity.EXTRA_SHOW, getArguments().getString(SHOW_ARGUMENT));
        intent.putExtra(SeasonDetailsActivity.EXTRA_SEASON,season.number());
        startActivity(intent);
    }
}
