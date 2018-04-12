package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddLebensmittel;

import android.view.View;


public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        mApplicationLogic.onClickedSave();

    }
}
