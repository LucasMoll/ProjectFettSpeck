package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten;

import android.view.View;
import android.widget.AdapterView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten.ApplicationLogic;

public class OnItemClickListener implements AdapterView.OnItemClickListener {

    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten.ApplicationLogic mApplicationLogic;


    public OnItemClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        mApplicationLogic.OnListItemClicked(position);

    }
}
