package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddEinheitFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;


public class ApplicationLogic {


    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings.Gui mGui;
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
        mGui.getmButton().setOnClickListener(cl);
        mGui.getmButton_neueEinheit().setOnClickListener(cl);


    }


    public void OnButtonClicked (){

        Activity activity = (Activity) mContext;

        Bundle bundle = new Bundle();
        EinheitenFragment F = new EinheitenFragment();
        F.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, F).commit();
    }

    public void OnButtonNeueEinheitClicked () {

        Activity activity = (Activity) mContext;

        Bundle bundle = new Bundle();
        AddEinheitFragment F = new AddEinheitFragment();
        F.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, F).commit();

    }
}
