package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

import javax.xml.transform.dom.DOMLocator;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;
    private int mLebensmittelID;

    public ApplicationLogic(Gui gui, Context context, int lebensmittelID) {
        mGui = gui;
        mContext = context;
        mLebensmittelID = lebensmittelID;
        initGui();
        initListener();
    }

    private void initGui() {

        // initialize view attributes
        getLebensmittelByID(mLebensmittelID);
        getAllEinheiten();
    }

    private void initListener() {
        ClickListener cl;

        cl = new ClickListener(this);
        mGui.getAddButton().setOnClickListener(cl);
        mGui.getDeleteButton().setOnClickListener(cl);
        mGui.getlistViewEinheiten().setOnItemClickListener(new OnItemClickListener(this));
        //mGui.getmLebensmittelPlusFab().setOnClickListener(cl);
    }

    public void setLebensmittelID(int lebensmittelID) {
        mLebensmittelID = lebensmittelID;
    }

    private void getLebensmittelByID(int id)
    {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getEinheitenOfLebensmittelByLebensmittelId(id);

        cursor.moveToFirst();

        String lebensmittel;
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        ArrayList<String> kurzbezeichnung = new ArrayList<String>();
        ArrayList<Double> menge = new ArrayList<Double>();

        lebensmittel =(cursor.getString(cursor.getColumnIndex("Lebensmittelbezeichnung")));
        while (!cursor.isAfterLast()) {

            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Einheitenbezeichnung")));
            kurzbezeichnung.add(cursor.getString(cursor.getColumnIndex("Kurzbezeichnung")));
            menge.add(cursor.getDouble(cursor.getColumnIndex("Menge")));

            cursor.moveToNext();
        }
        cursor.close();

        mGui.getBezeichnung().setText(lebensmittel);

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

    private void getAllEinheiten()
    {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllEinheiten();

        cursor.moveToFirst();
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
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


    public void onListItemClicked(int i) {
    }

    public void onClickDelete() {
    }

    public void onClickAdd() {
    }
}