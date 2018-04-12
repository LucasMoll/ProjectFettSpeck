package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem;

import android.content.DialogInterface;

import static android.content.DialogInterface.*;


public class DialogClickListener implements OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public DialogClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }


    @Override
    public void onClick(DialogInterface dialogInterface, int i) {

        switch (i){

            case DialogInterface.BUTTON_POSITIVE:
         mApplicationLogic.onOkClicked();
        }

    }
}
