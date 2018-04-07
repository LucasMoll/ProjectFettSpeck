package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.view.View;
import android.widget.AdapterView;

public class OnItemClickListener  implements AdapterView.OnItemClickListener, View.OnClickListener {
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        int index;
        index = adapterView.getPositionForView(adapterView);

    }

    @Override
    public void onClick(View view) {

    }
}
