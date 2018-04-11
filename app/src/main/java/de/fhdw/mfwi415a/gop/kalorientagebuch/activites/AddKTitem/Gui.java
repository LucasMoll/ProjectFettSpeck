package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {


    private EditText mBezeichnung;
    private ListView mMenueListe;
    private Button mSaveButton;
    private TextView mMenge;
    private View mView;



    public Gui(View myView) {


        mBezeichnung = (EditText) myView.findViewById(R.id.add_kt_item_name);
        mMenueListe = (ListView) myView.findViewById(R.id.add_kt_item_list);
        mSaveButton = (Button) myView.findViewById(R.id.add_kt_item_save);

        mView=myView;

    }

    public float getPortion(){
        return  Float.parseFloat(mMenge.getText().toString());

    }
    public EditText getmBezeichnung() {
        return mBezeichnung;
    }

    public ListView getmMenueListe() {
        return mMenueListe;
    }
    public TextView getmMenge() {
        return mMenge;
    }
    public Button getmSaveButton() {
        return mSaveButton;
    }

    public View getmView(){return mView;}

}