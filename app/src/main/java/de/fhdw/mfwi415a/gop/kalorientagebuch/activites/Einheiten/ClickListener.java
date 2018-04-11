package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten;

import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten.ApplicationLogic;

public class ClickListener implements View.OnClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten.ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.einheiten_plus_fab:
                mApplicationLogic.onPlusFabClicked();
        }


    }
}
