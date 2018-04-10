package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

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

        showallGerichte();

    }

    private void showallGerichte() {

        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        Cursor cursor = mDBHelper.getAllGerichte();

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

    private void setmListViewText(ArrayList<String> gerichte) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, gerichte );
        mGui.getmListView().setAdapter(arrayAdapter);
    }

    private void initListener() {

        mGui.getmListView().setOnItemClickListener(new OnItemClickListener(this));

    }

    public void OnListItemClicked() {



    }
}