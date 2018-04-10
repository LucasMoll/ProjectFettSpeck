package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues.Gui;

public class EinheitenFragment extends android.app.Fragment {

    View myView;
    private ApplicationLogic mApplicationLogic;
    private Gui mGui;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.einheiten_layout, container, false);
        getActivity().setTitle("Alle Einheiten");
        mGui = new Gui(myView);
        mApplicationLogic = new ApplicationLogic(mGui,getActivity());
        return myView;
    }
}
