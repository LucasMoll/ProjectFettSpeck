package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddLebensmittel;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.SimpleTimeZone;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.constants.Constants;

public class ApplicationLogic {


    private Gui mGui;

    public void saveLebensmittel(String bezeichnung, Double kilokalorien) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.writeNewLebensmittel(bezeichnung);
        mDbHelper.writeEinheitToLebensmittel(bezeichnung, Constants.KEYKILOKALORIEN, kilokalorien);

        mDbHelper.close();
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
        String bezeichnung = mGui.getBezeichnung();
        Double i;
        try{
            i = Double.parseDouble(mGui.getKilokalorien());
        }
        catch (Exception e)
        {
            i = 0d;
        }
        saveLebensmittel(bezeichnung, i);

        ((Activity) mContext).onBackPressed();
    }


}
