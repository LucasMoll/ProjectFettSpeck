package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;


public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }


    private Context mContext;


    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.button_alleEinheiten:
                mApplicationLogic.OnButtonClicked();
                break;

            case R.id.button_neueEinheit:
                mApplicationLogic.OnButtonNeueEinheitClicked();
                break;
        }



    }
}
