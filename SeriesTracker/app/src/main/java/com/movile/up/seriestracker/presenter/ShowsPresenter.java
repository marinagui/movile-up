package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.ShowsCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ShowsRemoteServiceClient;
import com.movile.up.seriestracker.view.ShowsView;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowsPresenter implements ShowsCallback {
    private ShowsView mView;
    private Context mContext;


    public ShowsPresenter(Context context,ShowsView view) {
        this.mView = view;
        this.mContext = context;
    }

    @Override
    public void onShowsSuccess(List<Show> shows) {
        mView.displayShows(shows);
    }

    public void loadShows() {
        new ShowsRemoteServiceClient().loadShows(mContext, this);
    }
}
