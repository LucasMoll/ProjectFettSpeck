package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.view.View;
import android.widget.AdapterView;

public class OnItemClickListener  implements AdapterView.OnItemClickListener {

    private ApplicationLogic mApplicationLogic;

    public OnItemClickListener(ApplicationLogic applicationLogic) {
        mApplicationLogic = applicationLogic;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

       mApplicationLogic.onListItemClicked(i);



    }
}
