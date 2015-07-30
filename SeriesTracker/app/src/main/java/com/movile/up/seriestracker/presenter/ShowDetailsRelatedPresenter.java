package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.ShowDetailsRelatedCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ShowRelatedRemoteServiceClient;
import com.movile.up.seriestracker.view.ShowDetailsRelatedView;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public class ShowDetailsRelatedPresenter implements ShowDetailsRelatedCallback {

    private ShowDetailsRelatedView mView;
    private Context mContext;

    public ShowDetailsRelatedPresenter(Context context, ShowDetailsRelatedView view) {
        mContext = context;
        mView = view;
    }

    public void loadRelatedShows(String show) {
        new ShowRelatedRemoteServiceClient().loadRelatedShows(mContext,this,show);
    }

    @Override
    public void onShowsSuccess(List<Show> shows) {
        mView.displayRelatedShows(shows);
    }
}
