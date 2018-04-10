package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.EinheitDetail;

import android.view.View;
import android.widget.Button;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {

    private Button mButton_Save;

    public Gui(View myView){

        mButton_Save = (Button) myView.findViewById(R.id.add_einheit_item_save);


    }

    public Button getmButton() {
        return mButton_Save;
    }


}
