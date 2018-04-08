package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem;

import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem.Gui;
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
        showAllGerichte();

    }

    private void initListener() {
        ClickListener cl;

        cl = new ClickListener(this);
    }

    private void showAllGerichte(){
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllGerichte();

        ArrayList<String> gerichte = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            gerichte.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }

        setmListViewText(gerichte);
        cursor.close();

    }
    private void setmListViewText(ArrayList<String> arrayList){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arrayList );
        mGui.getmMenueListe().setAdapter(arrayAdapter);
    }

}
