package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddEinheit;

import android.content.Context;
import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class ClickListener implements View.OnClickListener {

    private ApplicationLogic mApplicationLogic;
    private Context mContext;


    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {

            case R.id.add_einheit_item_save:
                mApplicationLogic.onSaveClicked();
                break;
        }



    }
}
