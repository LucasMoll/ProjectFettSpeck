package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem.Gui;

public class AddKTitemFragment extends Fragment {

    View myView;

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.add_kt_item, container, false);
        mGui = new Gui(myView);
        mApplicationLogic = new ApplicationLogic(mGui,getActivity());
        getActivity().setTitle("Eintrag hinzufügen");

        return myView;
    }



}
