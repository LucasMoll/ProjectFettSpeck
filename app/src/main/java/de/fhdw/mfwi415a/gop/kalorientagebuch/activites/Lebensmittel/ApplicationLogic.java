package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Lebensmittel;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddLebensmittelFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.LebensmittelDetailFragment;
//import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.StatistikFragment;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;
    private ArrayList<Integer> mIndexList = new ArrayList<Integer>();

    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {
        // initialize view attributes
        getArrayAdapterAllLebensmittel();
    }

    private void initListener() {
        ClickListener cl;

        cl = new ClickListener(this);
        mGui.getmLebensmittelPlusFab().setOnClickListener(cl);
        mGui.getListViewLebensmittel().setOnItemClickListener(new OnItemClickListener(this));
    }

    private void getArrayAdapterAllLebensmittel()
    {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllLebensmittel();

        cursor.moveToFirst();
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while(!cursor.isAfterLast()) {
            mIndexList.add(cursor.getInt(cursor.getColumnIndex("ID")));
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,bezeichnungen);
        mGui.populateListView(arrayAdapter);
    }


    public void onPlusFabClicked() {
        changeFragment(new AddLebensmittelFragment(), 0);
    }

    private void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        Bundle bundle = new Bundle();
        bundle.putInt("Lebensmittel_ID", i);
        f.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, f).addToBackStack("tag").commit();
    }

    public void onListItemClicked(int listviewIndex){
        //get lebensmittelID from corresponding arraylist
        int id = mIndexList.get(listviewIndex);
        changeFragment(new LebensmittelDetailFragment(), id);
    }



}