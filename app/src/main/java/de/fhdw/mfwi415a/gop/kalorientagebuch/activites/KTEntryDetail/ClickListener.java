package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KTEntryDetail;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.DatePicker;

import java.util.Calendar;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId())
        {
            case R.id.fab_duplicate_entry:
                mApplicationLogic.duplicateEntry();
                break;
            case R.id.fab_delete_entry:
                mApplicationLogic.deleteEntry();
                break;
        }
    }
}

