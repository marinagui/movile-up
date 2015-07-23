package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.ShowViewPagerAdapter;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.up.seriestracker.view.ShowDetailsView;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView {

    public static final String EXTRA_SHOW = "show";

    private ShowViewPagerAdapter adapterViewPager;
    ShowDetailsPresenter mPresenter;
    private String mShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        mShow = "breaking-bad";
        mPresenter = new ShowDetailsPresenter(this, this);
        showLoading();
        mPresenter.loadShowDetails(mShow);

        configureToolbar();

        ViewPager vpSeries = (ViewPager) findViewById(R.id.series_view_pager);
        adapterViewPager = new ShowViewPagerAdapter(getSupportFragmentManager(), this, this.EXTRA_SHOW);
        vpSeries.setAdapter(adapterViewPager);
    }

    @Override
    public void displayShow(Show show) {
        getSupportActionBar().setTitle(show.title());
        hideLoading();
    }
}
