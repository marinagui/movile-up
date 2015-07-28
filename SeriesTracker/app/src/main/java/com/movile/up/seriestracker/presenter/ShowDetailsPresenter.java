package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.async_task.FavoriteAddAsyncTask;
import com.movile.up.seriestracker.async_task.FavoriteRemoveAsyncTask;
import com.movile.up.seriestracker.listener.OnFavoriteListener;
import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.loader.FavoriteLoaderCallback;
import com.movile.up.seriestracker.model.Favorite;
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

    public void loadFavorite(String show) {
        ((ShowDetailsActivity)mContext).getLoaderManager().initLoader(0,null,new FavoriteLoaderCallback(mContext, new OnFavoriteListener() {
            @Override
            public void onLoadFavoriteSuccess(Favorite favorite) {
                mView.displayFavorite(favorite);
            }
        }, show)).forceLoad();
    }

    public void addFavorite(Favorite favorite){
        new FavoriteAddAsyncTask().execute(favorite);
    }

    public void deleteFavorite(String show){
        new FavoriteRemoveAsyncTask().execute(show);
    }
}
