package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.fragments.ShowInfoFragment;
import com.movile.up.seriestracker.fragments.ShowSeasonsFragment;

/**
 * Created by android on 7/21/15.
 */
public class ShowViewPagerAdapter extends FragmentPagerAdapter {

    private static int NUM_ITEMS = 2;
    private String mShow;
    private Context mContext;

    public ShowViewPagerAdapter(FragmentManager fragmentManager, Context context, String show) {
        super(fragmentManager);
        mContext = context;
        mShow = show;
    }

    public Fragment getItem(int position) {

        if (position == 0) {
            return new ShowInfoFragment();
        } else if (position == 1) {

            ShowSeasonsFragment fragment = new ShowSeasonsFragment();

            Bundle arguments  = new Bundle();
            arguments.putString(ShowSeasonsFragment.SHOW_ARGUMENT,mShow);
            fragment.setArguments(arguments);

            return fragment;

        }
        return null;
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
