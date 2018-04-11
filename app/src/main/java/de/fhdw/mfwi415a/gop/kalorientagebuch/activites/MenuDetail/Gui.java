package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail;

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
    private FloatingActionButton mPlus;
    private View mView;

    Context mContext;
    Activity mActivity;
    ListAdapter mListAdapter;
    private ListView mLwIngredients;

    public Gui(View myView) {
        mView = myView;
        mLwIngredients = (ListView) mView.findViewById(R.id.LwMenuEntries);

        //mActivity =  (Activity) (mView.getContext());
        //mContext = mActivity;
    }

    public ListView getListViewIngredients() {return mLwIngredients;}

    public void populateListView(ArrayAdapter<String> arrayAdapter)
    {
        mLwIngredients.setAdapter(arrayAdapter);
    }

    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }
}
