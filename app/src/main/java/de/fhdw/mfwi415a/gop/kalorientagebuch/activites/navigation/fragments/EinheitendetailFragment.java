package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail.AppLogic;

public class EinheitendetailFragment extends android.app.Fragment {

    View myView;

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.einheitdetail_layout, container, false);


        Bundle args = getArguments();
        int einheitid = -1;

        if(args != null)
            einheitid = args.getInt("Einheit_ID");

        if(einheitid == 0)
            return myView;

        getActivity().setTitle("Einheit bearbeiten");

        mGui = new Gui(myView);
        mApplicationLogic = new ApplicationLogic(mGui,getActivity(), einheitid);
        return myView;

    }

}
