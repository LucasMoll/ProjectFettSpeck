package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail.ClickListener;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.HomeFragment;

public class ApplicationLogic {


    private Gui mGui;
    private Context mContext;
    private int currentEinheit_ID;
    private int delFlg = 0;
    private String aktuelleBezeichnung;
    private String aktuelleKurzBezeichnung;



    public ApplicationLogic(Gui gui, Context context, int EinheitID) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
        currentEinheit_ID = EinheitID;

    }

    private void initGui() {


        setBezeichnungen();

    }

    private void initListener() {

        ClickListener cl;

        cl = new ClickListener(this);
        mGui.getmButton_Save().setOnClickListener(cl);
        mGui.getmButton_Delete().setOnClickListener(cl);


    }

    private void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        FragmentManager fragmentManager = activity.getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.content_frame, f).addToBackStack("tag");
        ft.detach(f).attach(f).commitAllowingStateLoss();

    }

    private void setBezeichnungen(){

        getBezeichnung();
        getKurzBezeichnung();


        String textBezeichnung;
        String textKurzbezeichnung;

        textBezeichnung = "Aktuelle Bezeichnung:" + aktuelleBezeichnung;
        textKurzbezeichnung = "Aktuelle Bezeichnung:" + aktuelleKurzBezeichnung;

        mGui.getmBezeichnungText().setText(textBezeichnung);
        mGui.getmKurzText().setText(textKurzbezeichnung);

    }

    private void getBezeichnung() {


        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        Cursor cursor = mDBHelper.getData("SELECT * FROM Einheit WHERE ID=\"" + currentEinheit_ID + "\";",null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            aktuelleBezeichnung = cursor.getString(cursor.getColumnIndex("Bezeichnung"));
        }

        cursor.close();
        mDBHelper.close();

        mGui.getmBezeichnungText().setText(aktuelleBezeichnung);


    }

    private void getKurzBezeichnung() {
        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        Cursor cursor = mDBHelper.getData("SELECT * FROM Einheit WHERE ID=\"" + currentEinheit_ID + "\";", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {

            aktuelleKurzBezeichnung = cursor.getString(cursor.getColumnIndex("Kurzbezeichnung"));
        }

        cursor.close();
        mDBHelper.close();


        mGui.getmKurzText().setText(aktuelleKurzBezeichnung);


    }

    public void onSaveClicked() {

        delFlg = 0;

        String insert1 = "UPDATE Einheit SET Bezeichnung=\""+ mGui.getmBezeichnung().getText().toString() +"\", Kurzbezeichnung=\""+ mGui.getmKurzBezeichnung().getText().toString() +"\", delFlg=\""+ delFlg + "\"WHERE ID =" + currentEinheit_ID;
        Log.d("Test", insert1);
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        mDbHelper.writeData(insert1, "AddEinheit");

        changeFragment(new EinheitenFragment(), 0);

    }

    public void onDeleteClicked() {

        delFlg = 1;

        String insert1 = "UPDATE Einheit SET Bezeichnung=\""+ mGui.getmBezeichnung().getText().toString() +"\", Kurzbezeichnung=\""+ mGui.getmKurzBezeichnung().getText().toString() +"\", delFlg=\""+ delFlg + "\"WHERE ID =" + currentEinheit_ID;
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        mDbHelper.writeData(insert1, "DeleteEinheit");

        changeFragment(new EinheitenFragment(), 0);


    }

}
