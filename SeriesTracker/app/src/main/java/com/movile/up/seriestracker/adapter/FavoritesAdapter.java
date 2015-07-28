package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.database.db_flow.entity.FavoriteEntity$Adapter;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesAdapter extends CursorAdapter {

    public FavoritesAdapter(Context context, Cursor cursor, int flags) {
        super(context,cursor,flags);
    }

    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder holder;
        View view = LayoutInflater.from(context).inflate(R.layout.favorites_list_item, parent, false);
        holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    public void bindView(View view, Context context, Cursor cursor) {
        //FavoriteEntity$Adapter adapter=new FavoriteEntity$Adapter();
        //adapter.loadFromCursor(cursor,);

        ((ViewHolder)view.getTag()).title().setText("just for test");
    }

    public static class ViewHolder {
        private TextView titleView;
        private View root;
        public ViewHolder(View root) {
            titleView = (TextView)root.findViewById(R.id.favorites_title);
            this.root = root;
        }
        public TextView title() { return titleView; }
        public View root() { return root; }
    }
}


