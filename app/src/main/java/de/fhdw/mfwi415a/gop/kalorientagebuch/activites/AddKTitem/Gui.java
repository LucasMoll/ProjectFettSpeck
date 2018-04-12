package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {


    private EditText mBezeichnung;
    private ListView mMenueListe;
    private Button mSaveButton;



    public Gui(View myView) {

        mBezeichnung = (EditText) myView.findViewById(R.id.add_kt_item_name);
        mMenueListe = (ListView) myView.findViewById(R.id.add_kt_item_list);
        mSaveButton = (Button) myView.findViewById(R.id.add_kt_item_save);



    }

    public ListView getmMenueListe() {
        return mMenueListe;
    }

}