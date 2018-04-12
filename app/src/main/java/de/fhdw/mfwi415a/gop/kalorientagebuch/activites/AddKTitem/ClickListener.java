package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem;

import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        /*switch (view.getId())
        {
            case R.id.add_kt_item_save:
                mApplicationLogic.onSaveClicked();
        }*/

        mApplicationLogic.onSaveClicked();


    }
}

