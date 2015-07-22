package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.SeasonsAdapter;
import com.movile.up.seriestracker.adapter.ShowsAdapter;
import com.movile.up.seriestracker.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.up.seriestracker.view.ShowDetailsView;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends FragmentActivity implements OnSeasonClickListener, ShowDetailsView {
    private ShowsAdapter adapterViewPager;
    private SeasonsAdapter mAdapter;
    private String mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        ViewPager vpSeries = (ViewPager) findViewById(R.id.series_view_pager);
        adapterViewPager = new ShowsAdapter(getSupportFragmentManager(), this);
        vpSeries.setAdapter(adapterViewPager);

        mShow = "breaking-bad";

        ShowDetailsPresenter mPresenter = new ShowDetailsPresenter(this, this);

        mPresenter.loadShowDetails(mShow);

    }

    @Override
    public void onSeasonClick(Season season) {

    }

    @Override
    public void displayShow(Show show) {

    }
}
