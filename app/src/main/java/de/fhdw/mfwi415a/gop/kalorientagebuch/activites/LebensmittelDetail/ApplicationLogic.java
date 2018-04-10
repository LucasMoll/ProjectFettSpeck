package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;

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
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext,android.R.layout.simple_list_item_1,bezeichnungen);
        mGui.populateListView(arrayAdapter);
    }

    public void onPlusFabClicked() {
        mGui.setSnackbar("testtext inserted");

    }



}