package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ShowRemoteServiceClient;
import com.movile.up.seriestracker.view.ShowDetailsView;

/**
 * Created by android on 7/22/15.
 */
public class ShowDetailsPresenter implements ShowDetailsCallback {

    private ShowDetailsView mView;
    private Context mContext;

    public ShowDetailsPresenter(Context context,ShowDetailsView mView) {
        this.mView = mView;
        this.mContext = context;
    }

    @Override
    public void onShowDetailsSuccess(Show show) {
        mView.displayShow(show);
    }

    public void loadShowDetails(String show){
        new ShowRemoteServiceClient().loadShowDetails(mContext, this, show);
    }
}
