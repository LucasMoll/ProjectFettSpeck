package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues;

import android.view.View;
import android.widget.ListView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    public ListView getmListView() {
        return mListView;
    }

    private ListView mListView;

    public Gui(View myView) {

        mListView = (ListView) myView.findViewById(R.id.menuesListView);

    }

}