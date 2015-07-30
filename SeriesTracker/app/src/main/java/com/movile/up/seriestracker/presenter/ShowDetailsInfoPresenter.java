package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.ShowInfoCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ShowInfoRemoteServiceClient;
import com.movile.up.seriestracker.view.ShowDetailsInfoView;

/**
 * Created by android on 7/30/15.
 */
public class ShowDetailsInfoPresenter implements ShowInfoCallback {

    ShowDetailsInfoView mView;
    Context mContext;

    public ShowDetailsInfoPresenter(Context context, ShowDetailsInfoView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void onShowInfoSuccess(Show show) {
        mView.displayInfo(show);
    }

    public void loadShowInfo(String show) {
        new ShowInfoRemoteServiceClient().loadInfo(mContext,this,show);
    }
}
