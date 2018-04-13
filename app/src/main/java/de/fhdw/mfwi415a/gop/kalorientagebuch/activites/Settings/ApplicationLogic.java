package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddEinheitFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.ProfilFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.ProfilbearbeitenFragment;


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


    }


    private void initListener() {

        ClickListener cl;
        cl = new ClickListener(this);
        mGui.getmButton_name().setOnClickListener(cl);
        mGui.getmButton().setOnClickListener(cl);

    }

    public void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        FragmentManager fragmentManager = activity.getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.content_frame, f).addToBackStack("tag");;
        ft.detach(f).attach(f).commitAllowingStateLoss();
    }

    public void alleEinheitenClicked(){

        changeFragment(new EinheitenFragment(),0);

    }

    public void profilChangeClicked(){

        changeFragment(new ProfilbearbeitenFragment(),0);

    }

}
