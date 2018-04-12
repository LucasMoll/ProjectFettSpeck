package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddLebensmittel;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {


    private EditText mBezeichnung;
    private Button mSaveButton;
    private EditText mKilokalorien;


    public Gui(View myView) {

        mBezeichnung = (EditText) myView.findViewById(R.id.add_lebensmittel_name);
        mSaveButton = (Button) myView.findViewById(R.id.add_lebensmittel_save);
        mKilokalorien = (EditText) myView.findViewById(R.id.add_lebensmittel_kilokalorien);
    }
    public String getKilokalorien(){return  mKilokalorien.getText().toString();}

    public String getBezeichnung()
    {
        return mBezeichnung.getText().toString();
    }

    public Button getSaveButton () {return  mSaveButton;}
}