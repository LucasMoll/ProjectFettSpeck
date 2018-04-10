package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Lebensmittel.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Lebensmittel.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
//import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.MyCursorAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Nahrungsmittel;

/**
 * Created by joel on 03.04.18.
 */

public class LebenbsmittelFragment extends android.app.Fragment {

    View myView;
    Context mContext;
    Activity mActivity;
    ListAdapter mListAdapter;
    private Gui mGui;
    private ApplicationLogic mApplicationLogic;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.lebensmittel_layout, container, false);
        mGui = new Gui(myView);
        mApplicationLogic = new ApplicationLogic(mGui,getActivity() );
        getActivity().setTitle("Lebensmittel");

        return myView;
    }

}