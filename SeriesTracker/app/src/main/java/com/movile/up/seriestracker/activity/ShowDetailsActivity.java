package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.SeriesAdapter;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends FragmentActivity{
    private SeriesAdapter adapterViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        ViewPager vpSeries = (ViewPager) findViewById(R.id.series_view_pager);
        adapterViewPager = new SeriesAdapter(getSupportFragmentManager(), this);
        vpSeries.setAdapter(adapterViewPager);
    }
}
