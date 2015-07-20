package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.SeasonDetailsListener;
import com.movile.up.seriestracker.model.Episode;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class EpisodesAdapter extends ArrayAdapter<Episode> {

    private List<Episode> episodes;
    private Context mContext;
    //private SeasonDetailsListener mListener;

    public EpisodesAdapter(Context context) { //, SeasonDetailsListener clickListener
        super(context,R.layout.season_details_episode);
        mContext  = context;
        //mListener = clickListener;
    }

    public int getCount() {
        if (episodes != null)
            return episodes.size();
        return 0;
    }

    public Episode getItem(int position) {
        if (episodes != null)
            return episodes.get(position);
        return null;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        int type = getItemViewType(position);
        if (view == null) {
            int resource = R.layout.season_details_episode;
            //if (type == TYPE_TBA) {
            //    resource = R.layout.episode_item_tba;
            //}
            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position, type);

        return view;
    }

    public void updateEpisodesList(List<Episode> e) {
        episodes = e;
        notifyDataSetChanged();
    }

    private void populateViewFromHolder(ViewHolder holder, int position, int type) {
        ((TextView)holder.numberView()).setText(episodes.get(position).number().toString());
        ((TextView)holder.titleView()).setText(episodes.get(position).title());
    }

    public int getViewTypeCount() {
        return 1;
    }
    public int getItemViewType(int position) {
        return 0;
    }

    public static class ViewHolder {
        private View numberView;
        private View titleView;
        public ViewHolder(View root) {
            numberView = root.findViewById(R.id.season_details_number);
            titleView = root.findViewById(R.id.season_details_title);
        }
        public View numberView() {
            return numberView;
        }
        public View titleView() {
            return titleView;
        }
    }
}
