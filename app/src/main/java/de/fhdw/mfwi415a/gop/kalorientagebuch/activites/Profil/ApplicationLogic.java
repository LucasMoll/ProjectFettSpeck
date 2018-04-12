package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Profil;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Profil.ClickListener;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Profil.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.ProfilFragment;

public class ApplicationLogic {


    private Gui mGui;
    private Context mContext;
    private int benutzerid = 1;




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
        mGui.getmButton_Save().setOnClickListener(cl);

    }


    private void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        FragmentManager fragmentManager = activity.getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.content_frame, f).addToBackStack("tag");
        ft.detach(f).attach(f).commitAllowingStateLoss();
    }

    public void onSaveClicked() {
        Log.d("TEST", "onSaveClicked");
        String insert1 = "UPDATE Einstellungen SET Benutzername=\""+ mGui.getmName().getText().toString() +"\", Email=\""+ mGui.getmEmail().getText().toString() +"\", Tageslimit=\""+ mGui.getmkcal().getText().toString() + "\"WHERE ID =" + benutzerid;
        Log.d("Test", insert1);
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();


        Log.d("TEST", insert1);

        mDbHelper.writeData(insert1, "changeProfil");

        changeFragment(new ProfilFragment(), 0);

    }

}
