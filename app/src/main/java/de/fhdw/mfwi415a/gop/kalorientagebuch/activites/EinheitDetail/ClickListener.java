package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail;

import android.content.Context;
import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail.ApplicationLogic;

public class ClickListener implements View.OnClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail.ApplicationLogic mApplicationLogic;

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

        }



    }
}
