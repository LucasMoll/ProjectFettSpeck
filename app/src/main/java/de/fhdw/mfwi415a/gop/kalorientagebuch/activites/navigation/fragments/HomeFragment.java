package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home.Gui;

public class HomeFragment extends Fragment {

    View myView;

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.home_layout, container, false);
        mGui = new Gui(myView);
        mApplicationLogic = new ApplicationLogic(mGui,getActivity() );

        return myView;
    }


}
