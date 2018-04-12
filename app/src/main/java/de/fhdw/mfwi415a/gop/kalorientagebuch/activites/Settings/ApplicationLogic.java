package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddEinheitFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.ProfilFragment;


public class ApplicationLogic {


    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings.Gui mGui;
    private Context mContext;
    private DialogClickListener dcl;
    private View mDialogView;
    private String Benutzername;
    private int benutzerid;




    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {


    }


    private void initListener() {

        ClickListener cl;
        cl = new ClickListener(this);
        dcl = new DialogClickListener(this);
        mGui.getmButton_name().setOnClickListener(cl);
        mGui.getmButton().setOnClickListener(cl);
        mGui.getmButton_neueEinheit().setOnClickListener(cl);
        mGui.getmButton_email().setOnClickListener(cl);
        mGui.getmButton_hoechstsatz().setOnClickListener(cl);

    }

    public void OnNameClicked() {

        showDialog();

    }

    public void OnEmailClicked() {

        showEmailDialog();

    }

    public void OnKcalClicked() {

        showKcalDialog();
    }

    private void showKcalDialog(){

        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.kcal_dialog_layout, null, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        alertBuilder.setView(mDialogView);

        alertBuilder.setTitle("Tagesthöchsatz in kcal");

        alertBuilder.setCancelable(true).setPositiveButton("OK", dcl);
        alertBuilder.setCancelable(true).setNegativeButton("Abbrechen", dcl);

        Dialog dialog = alertBuilder.create();
        dialog.show();

    }

    private void showEmailDialog(){

        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.email_dialog_layout, null, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        alertBuilder.setView(mDialogView);

        alertBuilder.setTitle("Trage deine E-Mail-Adresse ein");

        alertBuilder.setCancelable(true).setPositiveButton("OK", dcl);
        alertBuilder.setCancelable(true).setNegativeButton("Abbrechen", dcl);

        Dialog dialog = alertBuilder.create();
        dialog.show();

    }

    private void showDialog()
    {

        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.name_dialog_layout, null, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        alertBuilder.setView(mDialogView);

        alertBuilder.setTitle("Trage deinen Namen ein");

        alertBuilder.setCancelable(true).setPositiveButton("OK", dcl);
        alertBuilder.setCancelable(true).setNegativeButton("Abbrechen", dcl);

        Dialog dialog = alertBuilder.create();
        dialog.show();

    }

    public void onOkClicked() {

        TextView mName;
        TextView mEMAIL;
        mName = (TextView) mDialogView.findViewById(R.id.text_name_input);
        mEMAIL = (TextView) mDialogView.findViewById(R.id.text_email_input);
        save();

    }


    private void save(){
        benutzerid = 1;
        Log.d("test","on ok clicked");
        Log.d("test",mGui.getmNameInput().getText().toString());
        String insert1 = "UPDATE Einstellungen SET Benutzername=\""+ mGui.getmNameInput().getText().toString()+ "\"WHERE ID =\"" + benutzerid+"\"";
        Log.d("Test", insert1);
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        mDbHelper.writeData(insert1, "AddName");

        changeFragment(new ProfilFragment(), 0);

    }


    public void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        FragmentManager fragmentManager = activity.getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.content_frame, f);
        ft.detach(f).attach(f).commitAllowingStateLoss();
    }

    public void alleEinheitenClicked(){

        changeFragment(new EinheitenFragment(),0);

    }

    public void neueEinheitHinzufuegenClicked(){

        changeFragment(new AddEinheitFragment(), 0);

    }
}
