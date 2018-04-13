package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class Gui {
        private EditText mBezeichnung;
        private Button mSaveButton;
        private Button mAddLebensmittel;
        private View mView;
        private Spinner mspinnerLebensmittel;
        private Spinner mspinnerEinheiten;
        private EditText mMenge;



        public Gui(View myView) {
                mView = myView;
                mBezeichnung = (EditText) myView.findViewById(R.id.add_menue_Name);
                mSaveButton = (Button) myView.findViewById(R.id.add_menue_save);
                mAddLebensmittel = (Button) myView.findViewById(R.id.add_lebensmittel);
                mMenge = (EditText) myView.findViewById(R.id.add_menue_lebensmittelanzahl);
                mspinnerEinheiten = (Spinner) myView.findViewById(R.id.add_menue_einheiten);
                mspinnerLebensmittel = (Spinner) myView.findViewById(R.id.add_menue_lebensmittel);


        }

        public View getmView () { return  mView; }

        public EditText getmBezeichnung() { return mBezeichnung; }

        public Button getmSaveButton() { return  mSaveButton; }

        public Button getmAddLebensmittel() { return mAddLebensmittel; }

        public Spinner getSpinnerLebensmittel() {return mspinnerLebensmittel; }

        public Spinner getSpinnerEinheiten() { return mspinnerEinheiten; }

        public EditText getmMenge() { return mMenge; }

        public void populateSpinnerEinheiten(ArrayAdapter<String> spinnerArrayAdapter)
        {
                mspinnerEinheiten.setAdapter(spinnerArrayAdapter);
        }
        public void populateSpinnerLebensmittel(ArrayAdapter<String> spinnerArrayAdapter)
        {
                mspinnerLebensmittel.setAdapter(spinnerArrayAdapter);
        }
        public void setSnackbar(String text) {
                Snackbar.make(mView, text, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
        }


}

