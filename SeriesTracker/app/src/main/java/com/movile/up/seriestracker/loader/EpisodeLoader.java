package com.movile.up.seriestracker.loader;

import android.content.AsyncTaskLoader;

import com.movile.up.seriestracker.control.FetchLocalEpisodeDetails;
import com.movile.up.seriestracker.model.Episode;

/**
 * Created by android on 7/16/15.
 */
public class EpisodeLoader extends AsyncTaskLoader<Episode> {
    @Override
    public Episode loadInBackground() {
        return new FetchLocalEpisodeDetails().get(mContext);
    }

}
