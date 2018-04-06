package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings.ApplicationLogic;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings.Dialog;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings.Gui;

/**
 * Created by Simone on 03.04.18.
 */

public class ProfilFragment extends android.app.Fragment {

    View myView;

    private Button button_name;
    private Button button_hoechstsatz;
    private Button neue_Einheit;
    private Button alle_Einheiten;

    private Gui mGui;
    private ApplicationLogic mApplicationLogic;


   /* @Nullable
    @Override
   public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        /*myView = inflater.inflate(R.layout.profil_layout, container, false);
        getActivity().setTitle("Profil");
        mGui = new Gui(myView);
        mApplicationLogic = new ApplicationLogic(mGui,getActivity() );
        return myView;

        button_name = (Button) findViewById(R.id.button_name);
        button_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialog();
            }
        });
        button_hoechstsatz = (Button) findViewById(R.id.button_hoechstsatz);
      //  button_neueEinheit = (Button) findViewById(R.id.button_neueEinheit);
      //  button_alleEinheiten = (Button) findViewById(R.id.button_alleEinheiten);

    }

    public void openDialog(){

        Dialog dialog = new Dialog();
        dialog.show(getSupportFragmentManager(), "Dialog");

    }
}
*/

}