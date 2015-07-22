package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.fragments.ShowInfoFragment;
import com.movile.up.seriestracker.fragments.ShowSeasonsFragment;

/**
 * Created by android on 7/21/15.
 */
public class ShowsAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private Context mContext;

    public ShowsAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        mContext = context;
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new ShowInfoFragment();
            case 1:
                return new ShowSeasonsFragment();
            default:
                return null;
        }
    }

    public int getCount() {
        return NUM_ITEMS;
    }

    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.serie_details_info);
            case 1:
                return mContext.getString(R.string.serie_details_seasons);
            default:
                return null;
        }
    }

}
