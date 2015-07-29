package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.database.db_flow.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.db_flow.entity.FavoriteEntity$Adapter;
import com.movile.up.seriestracker.listener.OnClickFavoriteListener;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesAdapter extends CursorAdapter {

    private OnClickFavoriteListener mListener;

    public FavoritesAdapter(Context context, OnClickFavoriteListener clickListener) {
        super(context,null,0);
        mListener = clickListener;
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder holder;
        View view = LayoutInflater.from(context).inflate(R.layout.favorites_list_item, parent, false);
        holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        FavoriteEntity$Adapter adapter=new FavoriteEntity$Adapter();
        final FavoriteEntity entity = new FavoriteEntity();
        adapter.loadFromCursor(cursor, entity);

        ViewHolder holder = (ViewHolder)view.getTag();
        holder.title().setText(entity.title());
        holder.root().setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mListener.onFavoriteClick(new Favorite(entity.slug(), entity.title()));
            }
        });
    }

    public static class ViewHolder {
        private TextView titleView;
        private View root;
        public ViewHolder(View root) {
            titleView = (TextView)root.findViewById(R.id.favorited_show_title);
            this.root = root;
        }
        public TextView title() { return titleView; }
        public View root() { return root; }
    }

    public void setCursor(Cursor cursor) {
        changeCursor(cursor);
    }

}


