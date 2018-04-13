package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Statistik.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Statistik.Gui;

/**
 * Created by joel on 02.04.18.
 */

public class StatistikFragment extends android.app.Fragment {

    View view;

    Gui mGui;
    ApplicationLogic appLogic;


    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.statistik_layout, container, false);

        Activity activity = getActivity();
        activity.setTitle("Statistik");

        mGui = new Gui(view);

        appLogic = new ApplicationLogic(mGui, view.getContext());

        return view;
    }
}
