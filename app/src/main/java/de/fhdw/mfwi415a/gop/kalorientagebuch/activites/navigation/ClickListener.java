package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation;

/**
 * Created by joel on 05.03.18.
 */


import android.view.View;
import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class ClickListener implements View.OnClickListener{

    private ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {
        switch ( view.getId() ) {
            case R.id.idExampleButton:
                mApplicationLogic.onExampleButtonClicked();
                break;
            case R.id.idExampleTextview:
                mApplicationLogic.onExampleTextViewClicked();
                break;
        }
    }

}