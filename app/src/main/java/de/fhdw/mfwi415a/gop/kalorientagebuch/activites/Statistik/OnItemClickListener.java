package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Statistik;

import android.view.View;
import android.widget.AdapterView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Edible;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.KTEntry;

public class OnItemClickListener implements AdapterView.OnItemClickListener {

    private ApplicationLogic mApplicationLogic;

    public OnItemClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

       mApplicationLogic.onListItemClicked(((KTEntry)adapterView.getItemAtPosition(i)).get_id());
    }
}
