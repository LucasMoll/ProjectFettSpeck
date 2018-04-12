package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Profil;

import android.content.Context;
import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Profil.ApplicationLogic;

public class ClickListener implements View.OnClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Profil.ApplicationLogic mApplicationLogic;
    private Context mContext;


    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.profil_save:
                mApplicationLogic.onSaveClicked();
                break;
        }



    }
}
