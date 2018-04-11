package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues;

import android.view.View;
import android.widget.AdapterView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues.ApplicationLogic;

public class OnItemClickListener implements AdapterView.OnItemClickListener {

    private ApplicationLogic mApplicationLogic;

    public OnItemClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        mApplicationLogic.OnListItemClicked(i);


    }

}