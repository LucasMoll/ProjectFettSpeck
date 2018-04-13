package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Statistik;

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
            case R.id.lblDate:

                Calendar calendar = Calendar.getInstance();

                DatePickerDialog dialog = new DatePickerDialog(mApplicationLogic.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        mApplicationLogic.onDateChanged(year, month, day);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));

                dialog.show();
        }
    }
}

