package com.movile.up.seriestracker.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.up.seriestracker.R;

/**
 * Created by android on 7/21/15.
 */
public class ShowInfoFragment extends android.support.v4.app.Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        View view;
        view = inflater.inflate(R.layout.show_info_fragment, container, false);
        return view;
    }
}
