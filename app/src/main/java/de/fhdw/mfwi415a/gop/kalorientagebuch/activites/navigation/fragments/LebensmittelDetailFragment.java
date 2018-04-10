package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail.Gui;

/**
 * Created by pschoger on 10.04.2018.
 */

public class LebensmittelDetailFragment extends android.app.Fragment {

    View myView;
    Context mContext;
    Activity mActivity;
    ListAdapter mListAdapter;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.lebensmitteldetail_layout, container, false);
        mGui = new Gui(myView);
        mApplicationLogic = new ApplicationLogic(mGui,getActivity() );
        getActivity().setTitle("Lebensmitteldetail");

        return myView;
    }
}
