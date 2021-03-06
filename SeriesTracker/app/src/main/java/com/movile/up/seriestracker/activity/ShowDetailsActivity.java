package com.movile.up.seriestracker.activity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.ShowViewPagerAdapter;
import com.movile.up.seriestracker.database.db_flow.dao.FavoriteDAO;
import com.movile.up.seriestracker.model.Favorite;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.up.seriestracker.view.ShowDetailsView;

import java.text.DecimalFormat;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView {

    public static final String EXTRA_SHOW = "show_details_extra_show";

    private ShowViewPagerAdapter adapterViewPager;
    private FloatingActionButton favoriteView;
    ShowDetailsPresenter mPresenter;
    private String mShow, mTitle;
    private boolean favorited = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        getIntentExtra();

        mPresenter = new ShowDetailsPresenter(this, this);
        showLoading();
        mPresenter.loadShowDetails(mShow);
        mPresenter.loadFavorite(mShow);

        configureToolbar();

        favoriteView = (FloatingActionButton) findViewById(R.id.show_details_favorite);
        favoriteView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteClick();
            }
        });

        ViewPager vpSeries = (ViewPager) findViewById(R.id.series_view_pager);
        adapterViewPager = new ShowViewPagerAdapter(getSupportFragmentManager(), this, mShow);
        vpSeries.setAdapter(adapterViewPager);
    }

    @Override
    public void displayShow(Show show) {

        mTitle = show.title();
        getSupportActionBar().setTitle(mTitle);

        ((TextView)findViewById(R.id.show_details_rating)).setText(new DecimalFormat("#.#").format(show.rating()));
        ((TextView)findViewById(R.id.show_details_year)).setText(show.year().toString());
        Glide
                .with(this)
                .load(show.images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(((ImageView) findViewById(R.id.show_details_screenshot)));

        hideLoading();
    }

    private void getIntentExtra(){
        mShow = getIntent().getExtras().getString(EXTRA_SHOW);
    }

    @Override
    public void displayFavorite(Favorite favorite) {
        if (favorite != null) {
            favoriteView.setImageResource(R.drawable.show_details_favorite_on);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.show_details_favorite_on));
            favorited = true;
        } else {
            favoriteView.setImageResource(R.drawable.show_details_favorite_off);
            favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.show_details_favorite_off));
            favorited = false;
        }
    }

    private void onFavoriteClick() {

        ObjectAnimator startX = ObjectAnimator.ofFloat(favoriteView, "scaleX", 1, 0);
        startX.setDuration(200);
        ObjectAnimator startY = ObjectAnimator.ofFloat(favoriteView, "scaleY", 1, 0);
        startY.setDuration(200);

        AnimatorSet setFirst = new AnimatorSet();
        setFirst.playTogether(startX, startY);
        setFirst.setDuration(200);
        setFirst.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if (favorited == false) {
                    favoriteView.setImageResource(R.drawable.show_details_favorite_on);
                    favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.show_details_favorite_on));
                    mPresenter.addFavorite(new Favorite(mShow, mTitle));
                    favorited = true;
                } else {
                    favoriteView.setImageResource(R.drawable.show_details_favorite_off);
                    favoriteView.setBackgroundTintList(getResources().getColorStateList(R.color.show_details_favorite_off));
                    mPresenter.deleteFavorite(mShow);
                    favorited = false;
                }

                ObjectAnimator finishX = ObjectAnimator.ofFloat(favoriteView, "scaleX", 0, 1);
                finishX.setDuration(200);
                ObjectAnimator finishY = ObjectAnimator.ofFloat(favoriteView, "scaleY", 0, 1);
                finishY.setDuration(200);

                AnimatorSet setLast = new AnimatorSet();
                setLast.playTogether(finishX, finishY);
                setLast.setDuration(200);
                setLast.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        setFirst.start();

    }
}
