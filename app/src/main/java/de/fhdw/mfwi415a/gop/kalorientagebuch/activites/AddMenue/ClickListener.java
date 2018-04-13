package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;

import android.content.Context;
import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.ApplicationLogic;

public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }


    private Context mContext;


    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.add_menue_save:
                mApplicationLogic.onClickSave();
                break;

            case R.id.add_lebensmittel:
                mApplicationLogic.onClickAdd();
                break;

        }



    }
}