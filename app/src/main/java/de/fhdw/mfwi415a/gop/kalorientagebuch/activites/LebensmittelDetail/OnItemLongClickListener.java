package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail;

import android.view.View;
import android.widget.AdapterView;

public class OnItemLongClickListener implements AdapterView.OnItemLongClickListener {

    private ApplicationLogic mApplicationLogic;

    public OnItemLongClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
        mApplicationLogic.onListItemLongClicked(i);
        return true;
    }
}
