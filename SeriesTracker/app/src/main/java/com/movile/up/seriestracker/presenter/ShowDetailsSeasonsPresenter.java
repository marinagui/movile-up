package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.ShowDetailsSeasonsCallback;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ShowRemoteServiceClient;
import com.movile.up.seriestracker.view.ShowDetailsSeasonsView;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowDetailsSeasonsPresenter implements ShowDetailsSeasonsCallback {
    private ShowDetailsSeasonsView mView;
    private Context mContext;

    public ShowDetailsSeasonsPresenter(Context context, ShowDetailsSeasonsView view) {
        mContext = context;
        mView = view;
    }

    @Override
    public void onShowDetailsSeasonsSuccess(List<Season> seasons) {
        mView.displaySeasons(seasons);
    }

    public void loadShowSeasons () {

    }
}
