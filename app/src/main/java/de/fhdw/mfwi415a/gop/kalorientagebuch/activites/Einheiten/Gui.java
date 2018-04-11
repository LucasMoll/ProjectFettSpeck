package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ListView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    public ListView getmListView() {
        return mListView;
    }
    public FloatingActionButton getmFloat() {
        return mFloat;
    }

    private ListView mListView;
    private View mView;
    private FloatingActionButton mFloat;

    public Gui(View myView) {

        mView = myView;
        mListView = (ListView) myView.findViewById(R.id.einheitenListView);
        mFloat = (FloatingActionButton) myView.findViewById(R.id.einheiten_plus_fab);

    }

    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }



}
