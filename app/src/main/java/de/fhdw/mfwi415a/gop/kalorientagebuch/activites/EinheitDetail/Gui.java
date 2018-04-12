package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private Button mButton_Save;
    private Button mButton_Delete;
    private EditText mBezeichnung;
    private EditText mKurzBezeichnung;
    private TextView mBezeichnungText;
    private TextView mKurzText;

    public Gui(View myView){

        mButton_Save = (Button) myView.findViewById(R.id.add_einheit_item_save);
        mButton_Delete = (Button) myView.findViewById(R.id.add_einheit_item_delete);
        mBezeichnung = (EditText) myView.findViewById(R.id.add_einheit_name);
        mKurzBezeichnung = (EditText) myView.findViewById(R.id.add_einheit_kurz);
        mBezeichnungText = (TextView) myView.findViewById(R.id.text_name_bez);
        mKurzText = (TextView) myView.findViewById(R.id.text_einheit_kurz);


    }

    public Button getmButton_Save() {
        return mButton_Save;
    }
    public Button getmButton_Delete() { return mButton_Delete; }
    public EditText getmBezeichnung() { return mBezeichnung; }
    public EditText getmKurzBezeichnung() { return mKurzBezeichnung; }
    public TextView getmKurzText() { return mKurzText; }
    public TextView getmBezeichnungText() { return mBezeichnungText; }
}
