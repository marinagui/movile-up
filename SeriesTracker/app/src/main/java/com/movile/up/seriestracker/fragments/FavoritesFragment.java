package com.movile.up.seriestracker.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.adapter.FavoritesAdapter;
import com.movile.up.seriestracker.listener.OnClickFavoriteListener;
import com.movile.up.seriestracker.model.Favorite;
import com.movile.up.seriestracker.presenter.FavoritesPresenter;
import com.movile.up.seriestracker.view.FavoritesView;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesFragment extends Fragment implements FavoritesView, OnClickFavoriteListener {

    private FavoritesAdapter mAdapter;
    private FavoritesPresenter mPresenter;

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.loadFavorites(getLoaderManager());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorites_fragment, container, false);
        mPresenter = new FavoritesPresenter(this.getActivity(),this);
        configureFavoritesList(view);
        return view;
    }

    @Override
    public void displayFavorites(Cursor favorites) {
        ImageView tv = (ImageView) this.getActivity().findViewById(R.id.favorites_image);

        if (favorites.getCount() == 0)
            tv.setImageResource(R.drawable.favorites_header_tv_unhappy);
        else
            tv.setImageResource(R.drawable.favorites_header_tv_happy);

        mAdapter.swapCursor(favorites);
    }

    @Override
    public void onFavoriteClick(Favorite favorite) {
        Intent intent = new Intent(this.getActivity(), ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW,favorite.slug());
        startActivity(intent);
    }

    private void configureFavoritesList (View view) {
        ListView favoritesList = (ListView) view.findViewById(R.id.favorites_list_drawer);
        mAdapter = new FavoritesAdapter(this.getActivity(),this);
        favoritesList.setEmptyView(view.findViewById(R.id.favorites_list_empty));
        favoritesList.setAdapter(mAdapter);
    }
}
