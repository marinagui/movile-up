package com.movile.up.seriestracker.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.up.seriestracker.control.FetchRemoteEpisodeDetails;
import com.movile.up.seriestracker.model.Episode;

/**
 * Created by android on 7/16/15.
 */
public class EpisodeLoader extends AsyncTaskLoader<Episode> {

    private Context mContext;
    private String mUrl;

    public EpisodeLoader(Context context, String url){
        super(context);
        mContext = context;
        mUrl = url;
    }

    @Override
    public Episode loadInBackground() {
        return new FetchRemoteEpisodeDetails().get(mContext, mUrl);
    }

}
