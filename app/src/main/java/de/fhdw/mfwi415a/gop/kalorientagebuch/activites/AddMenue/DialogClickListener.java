package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;

import android.content.DialogInterface;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.ApplicationLogic;

public class DialogClickListener implements DialogInterface.OnClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.ApplicationLogic mApplicationLogic;

    public DialogClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        switch (i){

            case DialogInterface.BUTTON_POSITIVE:
                mApplicationLogic.onOkClicked();
                i=0;
        }

    }
}

