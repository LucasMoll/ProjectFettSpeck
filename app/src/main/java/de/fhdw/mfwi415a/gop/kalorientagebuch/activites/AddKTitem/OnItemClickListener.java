package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem;

import android.view.View;
import android.widget.AdapterView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem.ApplicationLogic;

public class OnItemClickListener implements AdapterView.OnItemClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem.ApplicationLogic mApplicationLogic;

    public OnItemClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        mApplicationLogic.onListClicked(i);

    }
}
