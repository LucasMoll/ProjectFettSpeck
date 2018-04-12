package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddLebensmittel;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;

public class ApplicationLogic {


    private Gui mGui;

    public void saveLebensmittel(String bezeichnung) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.writeNewLebensmittel(bezeichnung);
        mDbHelper.close();

        ((Activity) mContext).onBackPressed();
    }


    private Context mContext;
    //private

    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }


    private void initGui() {


    }

    private void initListener() {
        ClickListener cl;
        cl = new ClickListener(this);
       mGui.getSaveButton().setOnClickListener(cl);
    }

    public void onClickedSave() {
        saveLebensmittel(mGui.getBezeichnung());
    }
}
