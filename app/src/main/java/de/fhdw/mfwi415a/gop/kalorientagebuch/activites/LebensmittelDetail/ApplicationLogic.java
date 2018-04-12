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

                bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Einheitenbezeichnung")));
                kurzbezeichnung.add(cursor.getString(cursor.getColumnIndex("Kurzbezeichnung")));
                menge.add(cursor.getDouble(cursor.getColumnIndex("Menge")));
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

    private void getUnusedEinheiten(int lebensmittelID)
    {
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

    private void populateSpinner(ArrayList<String> spinnerArray)
    {
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
        deleteLebensmittel_Einheit(mLebensmittelID, id);
    }

    public void onClickDelete() {
        deleteLebensmittel(mLebensmittelID);
        ((Activity) mContext).onBackPressed();
    }

    public void onClickAdd() {
        addEinheitToLebensmittel(mLebensmittel, mGui.getEinheitenSpinner().getSelectedItem().toString(), mGui.getmMenge().getText().toString());
    }

    private void addEinheitToLebensmittel(String lebensmittel, String eineit, String menge) {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();
        mDbHelper.writeEinheitToLebensmittel(lebensmittel, eineit, Double.parseDouble(menge));
        mDbHelper.close();

        initGui();
    }
}