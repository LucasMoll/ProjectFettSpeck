package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.view.View;
import android.widget.Button;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private Button mButton_alleEinheiten;
    private Button mButton_name;

    public Gui(View myView){

        mButton_alleEinheiten = (Button) myView.findViewById(R.id.button_alleEinheiten);
        mButton_name = (Button) myView.findViewById(R.id.button_name);

    }

    public Button getmButton() {
        return mButton_alleEinheiten;
    }

    public Button getmButton_name() { return mButton_name; }

}