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

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.lebensmittel_layout, container, false);

        getActivity().setTitle("Lebensmittel");

        mActivity = getActivity();
        mContext = mActivity;

        populateListView();
        populateTextView();
        return myView;
    }

    private void populateTextView()
    {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllLebensmittel();

        cursor.moveToFirst();
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }
        cursor.close();

        //TextView myTextView = (TextView) myView.findViewById(R.id.testtextelementXXX);
        //myTextView.setText(bezeichnungen.get(0));
    }

    private void populateListView()
    {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllEinheiten();

        cursor.moveToFirst();
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }
        cursor.close();

        ListView myListView = (ListView) myView.findViewById(R.id.lebensmittelListView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,bezeichnungen);
        myListView.setAdapter(arrayAdapter);
    }

}