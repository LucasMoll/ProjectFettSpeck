package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

/**
 * Created by joel on 03.04.18.
 */

public class LebenbsmittelFragment extends android.app.Fragment {

    View myView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.lebensmittel_layout, container, false);
        return myView;
    }
}