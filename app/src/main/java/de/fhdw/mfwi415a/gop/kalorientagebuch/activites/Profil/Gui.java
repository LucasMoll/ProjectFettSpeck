package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Profil;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private Button mButton_Save;
    private EditText mName;
    private EditText mEmail;
    private EditText mkcal;

    public Gui(View myView){

        mButton_Save = (Button) myView.findViewById(R.id.profil_save);
        mName = (EditText) myView.findViewById(R.id.change_name);
        mEmail = (EditText) myView.findViewById(R.id.change_email);
        mkcal = (EditText) myView.findViewById(R.id.change_kcal);



    }

    public Button getmButton_Save() {
        return mButton_Save;
    }
    public EditText getmName() { return mName; }
    public EditText getmEmail() { return mEmail; }
    public EditText getmkcal() { return mkcal; }
}
