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


    public ApplicationLogic(Gui gui, Context context, int EinheitID) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
        currentEinheit_ID = EinheitID;

    }

    private void initGui() {

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
