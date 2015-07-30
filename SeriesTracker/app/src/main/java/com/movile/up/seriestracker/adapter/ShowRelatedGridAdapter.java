package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.ShowsClickListener;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowRelatedGridAdapter extends ArrayAdapter<Show> {

    private List<Show> shows;
    private ShowsClickListener mListener;
    private Context mContext;

    public ShowRelatedGridAdapter(Context context, ShowsClickListener listener) {
        super(context, R.layout.show_related_grid_item);
        mListener = listener;
        mContext = context;
    }

    public int getCount() {
        if (shows != null)
            return shows.size();
        return 0;
    }

    public Show getItem(int position) {
        if (shows != null)
            return shows.get(position);
        return null;
    }

    public long getItemId(int position) {
        return shows.get(position).ids().trakt();
    }

    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view == null) {
            int resource = R.layout.shows_grid_item;
            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position);

        return view;
    }

    private void populateViewFromHolder(ViewHolder holder, final int position) {

        Glide
                .with(mContext)
                .load(shows.get(position).images().poster().get(Images.ImageSize.THUMB))
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(holder.showsGridItem());

        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onShowClick(getItem(position));
            }
        });
    }

    public void updateRelatedShowsList(List<Show> shows) {
        this.shows = shows;
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        private ImageView mItem;
        private View viewRoot;
        public ViewHolder(View root) {
            mItem = (ImageView)root.findViewById(R.id.shows_grid_item_poster);
            viewRoot = root;
        }
        public ImageView showsGridItem() {
            return mItem;
        }
        public View root() {
            return viewRoot;
        }
    }

}
