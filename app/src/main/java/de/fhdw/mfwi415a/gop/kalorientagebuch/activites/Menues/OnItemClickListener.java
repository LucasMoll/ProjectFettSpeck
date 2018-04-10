package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues;

import android.view.View;
import android.widget.AdapterView;

public class OnItemClickListener implements AdapterView.OnItemClickListener {

    private ApplicationLogic mApplicationLogic;

    public OnItemClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
      //  mApplicationLogic.OnListItemClicked(position);

}
}

