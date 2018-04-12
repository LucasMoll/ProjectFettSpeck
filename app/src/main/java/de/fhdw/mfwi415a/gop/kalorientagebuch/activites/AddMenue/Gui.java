package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {
        private EditText mBezeichnung;
        private ListView mLebensmittelliste;
        private Button mSaveButton;
        private View mView;



        public Gui(View myView) {
                mView = myView;
                mLebensmittelliste = (ListView) myView.findViewById(R.id.add_Lebensmittel);
                mBezeichnung = (EditText) myView.findViewById(R.id.add_menue_Name);
//                mSaveButton = (Button) myView.findViewById(R.id.add_Lebensmittel);

        }

        public ListView getmLebensmittelliste() {
            return mLebensmittelliste;
        }

        public EditText getmBezeichnung() { return mBezeichnung; }

        public Button getmSaveButton() { return  mSaveButton; }

}

