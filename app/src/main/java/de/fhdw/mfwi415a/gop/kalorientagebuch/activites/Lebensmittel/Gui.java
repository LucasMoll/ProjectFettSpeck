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
    ListView listViewLebensmittel;
    public Gui(View myView) {
        mView = myView;
        listViewLebensmittel = (ListView) mView.findViewById(R.id.lebensmittelListView);
        //mActivity =  (Activity) (mView.getContext());
        //mContext = mActivity;
    }


    public void populateListView(ArrayAdapter<String> arrayAdapter)
    {
        listViewLebensmittel.setAdapter(arrayAdapter);
    }
}

