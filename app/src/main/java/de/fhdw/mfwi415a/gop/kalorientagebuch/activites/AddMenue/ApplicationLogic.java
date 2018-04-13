package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;


import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.MenuesFragment;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;
    private View mDialogView;
    private String Lebensmittelbezeichnung;
    private int Lebensmittelid;
    private int MenueMaxID;
    private String Einheitbezeichnung;
    private int EinheitID;
    private int LebensmittelGerichtID;
    private String Lebensmittelbez;
    private ArrayList<Integer> mIDListEinheiten = new ArrayList<Integer>();
    private ArrayList<Integer> mIDListLebensmittel = new ArrayList<Integer>();

    public ApplicationLogic(Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {
        showallEinheiten();
        showallLebensmittel();
        MenueMaxID = getMaxGerichtID()+1;

    }

    private void initListener() {

        ClickListener cl;

        cl = new ClickListener(this);
        mGui.getmAddLebensmittel().setOnClickListener(cl);
        mGui.getmSaveButton().setOnClickListener(cl);
    }


    private void populateSpinnerLebensmittel(ArrayList<String> spinnerArray)
    {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (mContext, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        mGui.populateSpinnerLebensmittel(spinnerArrayAdapter);
    }

    private void populateSpinnerEinheiten(ArrayList<String> spinnerArray)
    {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (mContext, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        mGui.populateSpinnerEinheiten(spinnerArrayAdapter);
    }

    public void onClickAdd() {

            Lebensmittelid = getLebensmittelIDbyBezeichnung();
            EinheitID = getEinheitIDbyBezeichnung();

            LebensmittelGerichtID = getMaxLebensmittelGericht()+1;
            int delFlg = 0;


            String insert1 = "INSERT INTO Lebensmittel_Gericht (ID,LebensmittelID,GerichtID,EinheitID,Menge) VALUES"
                    + " (\""+ LebensmittelGerichtID +"\",\""
                    + Lebensmittelid +"\",\""
                    + MenueMaxID +"\",\""
                    + EinheitID +"\",\""
                    + mGui.getmMenge().getText().toString() +"\")";

            Log.d("Test", insert1);

            DataAdapter mDbHelper = new DataAdapter(mContext);
            mDbHelper.createDatabase();
            mDbHelper.open();

            mDbHelper.writeData(insert1, "AddEinheit");


    }


    public void onClickSave(){
        int delFlg = 0;

        String insert1 = "INSERT INTO Gericht (ID,Bezeichnung,delFlg) VALUES" + " ("+ MenueMaxID + ", \"" + mGui.getmBezeichnung().getText().toString() +"\", \"" + delFlg + "\")";

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        mDbHelper.writeData(insert1, "AddGericht");

        changeFragment(new MenuesFragment(), 0);
    }


    private int getMaxGerichtID(){

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getMaxGericht_ID();

        ArrayList<Integer> id = new ArrayList<Integer>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            id.add(cursor.getInt(cursor.getColumnIndex("max(ID)")));
            cursor.moveToNext();
        }

        cursor.close();
        return id.get(0);
    }


    private int getMaxLebensmittelGericht(){

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getMaxLebensmittelGericht_ID();

        ArrayList<Integer> id = new ArrayList<Integer>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            id.add(cursor.getInt(cursor.getColumnIndex("max(ID)")));
            cursor.moveToNext();
        }

        cursor.close();
        return id.get(0);
    }

    private void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        FragmentManager fragmentManager = activity.getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.content_frame, f).addToBackStack("tag");
        ft.detach(f).attach(f).commitAllowingStateLoss();
    }

    private void showallEinheiten() {
        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        Cursor cursor = mDBHelper.getAllEinheiten();

        ArrayList<String> einheiten = new ArrayList<String>();
        cursor.moveToFirst();
        mIDListEinheiten.clear();
        while (!cursor.isAfterLast())

        {
            mIDListEinheiten.add(cursor.getInt(cursor.getColumnIndex("ID")));
            einheiten.add(cursor.getString(cursor.getColumnIndex("Bezeichnung"))+ " (" + cursor.getString(cursor.getColumnIndex("Kurzbezeichnung"))+")");
            cursor.moveToNext();
        }

        setmspinnerEinheiten(einheiten);
        cursor.close();


    }

    private void setmspinnerEinheiten(ArrayList<String> einheiten) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, einheiten );
        populateSpinnerEinheiten(einheiten);
    }

    private void showallLebensmittel() {
        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        Cursor cursor = mDBHelper.getAllLebensmittel();

        ArrayList<String> Lebensmittel = new ArrayList<String>();
        cursor.moveToFirst();
        mIDListLebensmittel.clear();
        while (!cursor.isAfterLast())

        {
            mIDListLebensmittel.add(cursor.getInt(cursor.getColumnIndex("ID")));
            Lebensmittel.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));

            cursor.moveToNext();
        }

        setmspinnerLebensmittel(Lebensmittel);
        cursor.close();


    }

    private void setmspinnerLebensmittel(ArrayList<String> Lebensmittel) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, Lebensmittel );
        populateSpinnerLebensmittel(Lebensmittel);
    }

    private int getLebensmittelIDbyBezeichnung(){

        Lebensmittelbezeichnung = String.valueOf(mGui.getSpinnerLebensmittel().getSelectedItem());

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getIDofLebensmittel(Lebensmittelbezeichnung);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            Lebensmittelid = cursor.getInt(cursor.getColumnIndex("ID"));
            cursor.moveToNext();
        }

        cursor.close();
        return Lebensmittelid;


    }

    private int getEinheitIDbyBezeichnung(){

        Einheitbezeichnung = String.valueOf(mGui.getSpinnerEinheiten().getSelectedItem());

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getIDofEinheit(Einheitbezeichnung);

        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {

            EinheitID = cursor.getInt(cursor.getColumnIndex("ID"));
            cursor.moveToNext();
        }

        cursor.close();
        return EinheitID;
    }

}
