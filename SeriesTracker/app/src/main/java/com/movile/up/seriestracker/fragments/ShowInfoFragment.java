package com.movile.up.seriestracker.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowDetailsInfoPresenter;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.view.ShowDetailsInfoView;

import java.util.Date;

/**
 * Created by android on 7/21/15.
 */
public class ShowInfoFragment extends android.support.v4.app.Fragment implements ShowDetailsInfoView {

    public static final String SHOW_ARGUMENT = "show";
    private static final String TAG = ShowInfoFragment.class.getSimpleName();
    ShowDetailsInfoPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view;
        view = inflater.inflate(R.layout.show_info_fragment, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new ShowDetailsInfoPresenter(this.getActivity(),this);
        mPresenter.loadShowInfo(getArguments().getString(SHOW_ARGUMENT));
    }

    @Override
    public void displayInfo(Show show) {
        try {
            ((TextView) getActivity().findViewById(R.id.show_details_info_summary)).setText(show.overview());

            Date formattedDate = FormatUtil.formatDate(show.firstAired());

            ((TextView) getActivity().findViewById(R.id.show_details_info_details)).setText(
                    "Broadcasting: "+FormatUtil.formatDate(formattedDate)+"\n"+
                    "Status: "+show.status()+"\n"+
                    "Seasons: "+"\n"+
                    "Started in: "+show.year()+"\n"+
                    "Country: "+show.country()+"\n"+
                    "Homepage: "+show.network()
            );
        } catch(Exception e) {
            Log.e(TAG, "Error setting values", e.getCause());
        }

    }
}
