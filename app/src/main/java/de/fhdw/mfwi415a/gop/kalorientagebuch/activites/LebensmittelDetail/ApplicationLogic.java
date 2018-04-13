package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.zip.DeflaterOutputStream;

import javax.xml.transform.dom.DOMLocator;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;
    private int mLebensmittelID;
    private ArrayList<Integer> mIndexListUnusedEInheiten;
    private ArrayList<Integer> mIndexListUsedEInheiten;
    private String mLebensmittel;

    public ApplicationLogic(Gui gui, Context context, int lebensmittelID) {
        mGui = gui;
        mContext = context;
        mLebensmittelID = lebensmittelID;
        initGui();
        initListener();
    }

    private void initGui() {
        mIndexListUnusedEInheiten = new ArrayList<>();
        mIndexListUsedEInheiten = new ArrayList<>();

        // initialize view attributes
        getLebensmittelByID(mLebensmittelID);
        getUnusedEinheiten(mLebensmittelID);
    }

    private void initListener() {
        ClickListener cl;

        cl = new ClickListener(this);
        mGui.getAddButton().setOnClickListener(cl);
        mGui.getDeleteButton().setOnClickListener(cl);
        mGui.getlistViewEinheiten().setOnItemLongClickListener(new OnItemLongClickListener(this));
        mGui.getSaveButton().setOnClickListener(cl);
        //mGui.getmLebensmittelPlusFab().setOnClickListener(cl);
    }

    public void setLebensmittelID(int lebensmittelID) {
        mLebensmittelID = lebensmittelID;
    }

    private void getLebensmittelByID(int id) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getEinheitenOfLebensmittelByLebensmittelId(id);

        cursor.moveToFirst();

        ArrayList<String> bezeichnungen = new ArrayList<String>();
        ArrayList<String> kurzbezeichnung = new ArrayList<String>();
        ArrayList<Double> menge = new ArrayList<Double>();

        if (!cursor.isAfterLast()) {
            mLebensmittel = (cursor.getString(cursor.getColumnIndex("Lebensmittelbezeichnung")));
            while (!cursor.isAfterLast()) {

                //part-of-longversion
//                bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Einheitenbezeichnung")));
//                kurzbezeichnung.add(cursor.getString(cursor.getColumnIndex("Kurzbezeichnung")));
//                menge.add(cursor.getDouble(cursor.getColumnIndex("Menge")));
                //quick-version
                String entry = cursor.getString(cursor.getColumnIndex("Einheitenbezeichnung")) + " - " + cursor.getDouble(cursor.getColumnIndex("Menge")) + cursor.getString(cursor.getColumnIndex("Kurzbezeichnung"));
                bezeichnungen.add(entry);
                mIndexListUsedEInheiten.add(cursor.getInt(cursor.getColumnIndex("ID")));
                cursor.moveToNext();
            }
            cursor.close();
        } else {
            cursor = mDbHelper.getLebensmittelByID(id);
            cursor.moveToFirst();
            mLebensmittel = cursor.getString(cursor.getColumnIndex("Bezeichnung"));
        }

        mGui.getBezeichnung().setText(mLebensmittel);
//einheiten
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, bezeichnungen);
        mGui.populateEinheitenListView(arrayAdapter);

    }

    private void getArrayAdapterAllLebensmittel() {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllLebensmittel();

        cursor.moveToFirst();
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }
        cursor.close();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, bezeichnungen);
        mGui.populateEinheitenListView(arrayAdapter);
    }

    private void getUnusedEinheiten(int lebensmittelID) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getUnusedEinheitenFromLebensmittelId(lebensmittelID);

        cursor.moveToFirst();
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            mIndexListUnusedEInheiten.add(cursor.getInt(cursor.getColumnIndex("ID")));
            cursor.moveToNext();
        }
        cursor.close();

        populateSpinner(bezeichnungen);
    }

    private void populateSpinner(ArrayList<String> spinnerArray) {
        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                (mContext, android.R.layout.simple_spinner_item, spinnerArray); //selected item will look like a spinner set from XML
        spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                .simple_spinner_dropdown_item);
        mGui.populateSpinner(spinnerArrayAdapter);
    }

    private void deleteLebensmittel(int mLebensmittelID) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.setDeleteFlagLebensmittel(mLebensmittelID);
        mDbHelper.close();
    }

    private void deleteLebensmittel_Einheit(int lebensmittelID, int einheitID) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.deleteLebensmittel_Einheiten(lebensmittelID, einheitID);
        mDbHelper.close();

        initGui();
    }

    public void onListItemLongClicked(int i) {
        //get lebensmittelID from corresponding arraylist
        int id = mIndexListUsedEInheiten.get(i);

        int newid = editLebensmittel(mLebensmittelID);
        getLebensmittelByID(newid);
        deleteLebensmittel_Einheit(mLebensmittelID, id);

    }

    public void onClickDelete() {
        deleteLebensmittel(mLebensmittelID);
        ((Activity) mContext).onBackPressed();
    }

    public void onClickAdd() {
        try {
            getLebensmittelByID(editLebensmittel(mLebensmittelID));

            addEinheitToLebensmittel(mLebensmittel, mGui.getEinheitenSpinner().getSelectedItem().toString(), mGui.getmMenge().getText().toString());
        } catch (Exception e) {
            String x = mGui.getmMenge().getText().toString();
            if (x.equals("")) {
                mGui.setSnackbar("Es fehlt die Angabe der Menge");
            } else {
                mGui.setSnackbar("Du hast keine weiteren Einheiten angelegt.");
            }
        }
    }

    private void addEinheitToLebensmittel(String lebensmittel, String eineit, String menge) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.writeEinheitToLebensmittel(lebensmittel, eineit, Double.parseDouble(menge));
        mDbHelper.close();

        initGui();
    }

    public void onClickSave() {
        getLebensmittelByID(editLebensmittel(mLebensmittelID));

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.updateLebensmittelBezeichnung(mLebensmittel, mGui.getBezeichnung().getText().toString());
        mDbHelper.close();


        mGui.setSnackbar("Neuer Name gespeichert.");
    }

    private int editLebensmittel(int lebensmittelID) {

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
//make(JUST) copy of lebensmittel
        mDbHelper.writeCopyOfLebensmittel(lebensmittelID);
//set deleteflag of old lebensmittel
        mDbHelper.setDeleteFlagLebensmittel(lebensmittelID);
//get id of new lebensmittel
        int newId = getLastLebensmittelID();
//get einheiten of old lebensmittel
        Cursor cursor = mDbHelper.getLebensmittel_EinheitByLebensmittelID(lebensmittelID);
        cursor.moveToFirst();

        double menge;
        int einheitID;
        while (!cursor.isAfterLast()) {
            menge = (cursor.getDouble(cursor.getColumnIndex("Menge")));
            einheitID = (cursor.getInt(cursor.getColumnIndex("EinheitID")));

            mDbHelper.writeEinheitToLebensmittel(newId, einheitID, menge);

            cursor.moveToNext();
        }
        cursor.close();
        return newId;
    }

    private int getLastLebensmittelID()
    {
        int id;
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getLastLebensmittel();

        cursor.moveToFirst();
        id = cursor.getInt(cursor.getColumnIndex("ID"));
        cursor.close();
        mDbHelper.close();
        return id;
    }
}