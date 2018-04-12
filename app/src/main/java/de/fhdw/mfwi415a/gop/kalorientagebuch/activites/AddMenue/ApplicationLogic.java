package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.OnItemClickListener;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;

public class ApplicationLogic {


    private Gui mGui;
    private Context mContext;
    private ArrayList <Integer> mIDList = new ArrayList<Integer>();

    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {
        showAllLebensmittel();
    }

    private void initListener() {
        mGui.getmLebensmittelliste().setOnItemClickListener(new OnItemClickListener(this));
    }

    private void showAllLebensmittel(){
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
        mGui.getmLebensmittelliste().setAdapter(arrayAdapter);
    }

    public void onListClicked(int i) {


    }

}
