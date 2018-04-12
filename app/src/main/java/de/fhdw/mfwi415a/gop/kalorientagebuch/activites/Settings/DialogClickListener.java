package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings.ApplicationLogic;

public class DialogClickListener implements DialogInterface.OnClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings.ApplicationLogic mApplicationLogic;

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