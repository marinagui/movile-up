package com.movile.up.seriestracker.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.SeasonsAdapter;
import com.movile.up.seriestracker.listener.OnSeasonClickListener;

/**
 * Created by android on 7/21/15.
 */
public class ShowSeasonsFragment extends android.support.v4.app.Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;

        view = inflater.inflate(R.layout.show_seasons_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        RecyclerView rv = (RecyclerView) getActivity().findViewById(R.id.seasons_recycler);
        rv.setLayoutManager(
                new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        SeasonsAdapter adapter = new SeasonsAdapter(getActivity(), (OnSeasonClickListener)getActivity());
        rv.setAdapter(adapter);
    }
}
