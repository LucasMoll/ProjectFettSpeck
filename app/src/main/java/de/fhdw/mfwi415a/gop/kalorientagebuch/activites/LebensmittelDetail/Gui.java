package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    //private TextView mUsedBar;
    //private TextView mUnusedBar;
    private FloatingActionButton mPlus;
    private View mView;

    Context mContext;
    Activity mActivity;
    ListAdapter mListAdapter;



    private ListView mlistViewEinheiten;
    private Button mAddButton;
    private Button mDeleteButton;
    private Spinner mEinheitenSpinner;
    private EditText mBezeichnung;
    private EditText mMenge;
    private Button mSaveButton;

    public Gui(View myView) {
        mView = myView;

        mlistViewEinheiten = (ListView) myView.findViewById(R.id.lebensmitteldetail_usedeinheiten);
        mAddButton = (Button) myView.findViewById(R.id.lebensmitteldetail_add);
        mDeleteButton = (Button) myView.findViewById(R.id.lebensmitteldetail_delete);
        mEinheitenSpinner = (Spinner) myView.findViewById(R.id.lebensmitteldetail_einheiten);
        mBezeichnung = (EditText) myView.findViewById(R.id.lebensmitteldetail_bezeichnung);
        mMenge = (EditText) myView.findViewById(R.id.lebensmitteldetail_addmenge);
        mSaveButton = (Button) myView.findViewById(R.id.lebensmitteldetail_save);

        //listViewLebensmittel = (ListView) mView.findViewById(R.id.lebensmittelListView);
        //mPlus = (FloatingActionButton) myView.findViewById(R.id.lebensmittel_plus_fab);

        //mActivity =  (Activity) (mView.getContext());
        //mContext = mActivity;
    }

    public ListView getlistViewEinheiten() {
        return mlistViewEinheiten;
    }

    public Button getAddButton() {
        return mAddButton;
    }

    public Button getDeleteButton() {
        return mDeleteButton;
    }

    public Spinner getEinheitenSpinner() {
        return mEinheitenSpinner;
    }

    public EditText getBezeichnung() {
        return mBezeichnung;
    }

    public EditText getmMenge() {
        return mMenge;
    }

    public FloatingActionButton getmLebensmittelPlusFab() {
        return mPlus;
    }

    public void populateEinheitenListView(ArrayAdapter<String> arrayAdapter)
    {
        mlistViewEinheiten.setAdapter(arrayAdapter);
    }

    public void setSnackbar(String text) {
        Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    public void populateSpinner(ArrayAdapter<String> spinnerArrayAdapter)
    {
        mEinheitenSpinner.setAdapter(spinnerArrayAdapter);
    }

    public Button getSaveButton() {
        return mSaveButton;
    }
}

