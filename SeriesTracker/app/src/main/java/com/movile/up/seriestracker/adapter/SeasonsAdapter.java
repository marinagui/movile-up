package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Season;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public class SeasonsAdapter extends RecyclerView.Adapter<SeasonsAdapter.ViewHolder> {

    private List<Season> seasons;
    private Context mContext;
    private OnSeasonClickListener mListener;

    public SeasonsAdapter(Context context, OnSeasonClickListener clickListener) {
        mContext = context;
        mListener = clickListener;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.show_season_item, parent, false);
        return new ViewHolder(v);
    }

    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.titleView().setText("Season " + seasons.get(position).number().toString());
        holder.titleView().setText(seasons.get(position).episodeCount().toString() + " episodes");

        Glide
                .with(mContext)
                .load(seasons.get(position).images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(holder.thumbnailView());

        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSeasonClick(getItem(position));
            }
        });
    }

    public int getItemCount() {
        if (seasons != null)
            return seasons.size();
        return 0;
    }

    public Season getItem(int position) {
        if (seasons != null)
            return seasons.get(position);
        return null;
    }

    public void updateSeasonsList(List<Season> seasons) {
        this.seasons = seasons;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView episodesView, titleView;
        private ImageView thumbnailView;

        private View viewRoot;

        public ViewHolder(View root) {
            super(root);
            episodesView = (TextView)root.findViewById(R.id.show_details_season_title);
            titleView = (TextView)root.findViewById(R.id.show_details_season_episodes);
            thumbnailView = (ImageView)root.findViewById(R.id.show_details_season_thumbnail);
            viewRoot = root;
        }

        public TextView episodesView() {
            return episodesView;
        }
        public TextView titleView() {
            return titleView;
        }
        public ImageView thumbnailView() {
            return thumbnailView;
        }
        public View root() {
            return viewRoot;
        }
    }

}

