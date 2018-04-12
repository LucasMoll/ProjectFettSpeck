package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddEinheit;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;

public class ApplicationLogic {


    private Gui mGui;
    private Context mContext;
    private int Einheit_ID;
    private int delFlg = 0;




    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {
        Einheit_ID = getMaxEinheitiD()+1;

    }


    private void initListener() {

        ClickListener cl;

        cl = new ClickListener(this);
        mGui.getmButton_Save().setOnClickListener(cl);

    }

    private int getMaxEinheitiD(){

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getMaxEinheit_ID();

        ArrayList<Integer> id = new ArrayList<Integer>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            id.add(cursor.getInt(cursor.getColumnIndex("max(ID)")));
            cursor.moveToNext();
        }

        cursor.close();

        return id.get(0);

    }

    private void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        FragmentManager fragmentManager = activity.getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.content_frame, f).addToBackStack("tag");
        ft.detach(f).attach(f).commitAllowingStateLoss();
    }

    public void onSaveClicked() {
        Log.d("TEST", "onSaveClicked");
        String insert1 = "INSERT INTO Einheit (ID,Bezeichnung,Kurzbezeichnung,delFlg) VALUES" + " ("+ Einheit_ID+ ", \"" + mGui.getmBezeichnung().getText().toString() +"\", \"" + mGui.getmKurzBezeichnung().getText().toString() +"\", \"" + delFlg + "\")";

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();


        Log.d("TEST", insert1);

        mDbHelper.writeData(insert1, "AddEinheit");

        changeFragment(new EinheitenFragment(), 0);

    }

}
