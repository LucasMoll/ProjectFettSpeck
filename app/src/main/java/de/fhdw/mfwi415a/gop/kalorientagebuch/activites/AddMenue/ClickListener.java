package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;

import android.view.View;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.ApplicationLogic;

public class ClickListener implements View.OnClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.ApplicationLogic mApplicationLogic;

    public ClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            //case R.id.home_plus_fab:
        }



}
}
