package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Lebensmittel;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    //private TextView mUsedBar;
    //private TextView mUnusedBar;
    private FloatingActionButton mPlus;
    private View mView;

    Context mContext;
    Activity mActivity;
    ListAdapter mListAdapter;
    private ListView mListViewLebensmittel;
    public Gui(View myView) {
        mView = myView;
        mListViewLebensmittel = (ListView) mView.findViewById(R.id.lebensmittelListView);
        mPlus = (FloatingActionButton) myView.findViewById(R.id.lebensmittel_plus_fab);

        //mActivity =  (Activity) (mView.getContext());
        //mContext = mActivity;
    }

    public FloatingActionButton getmLebensmittelPlusFab() {
        return mPlus;
    }
    public ListView getListViewLebensmittel() {return mListViewLebensmittel;}

    public void populateListView(ArrayAdapter<String> arrayAdapter)
    {
        mListViewLebensmittel.setAdapter(arrayAdapter);
    }

    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}

