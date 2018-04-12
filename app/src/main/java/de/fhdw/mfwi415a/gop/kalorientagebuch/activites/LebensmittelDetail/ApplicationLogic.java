package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.LebensmittelDetail;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;
    private int mLebensmittelID;

    public ApplicationLogic(Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {

        // initialize view attributes
        getLebensmittelByID(mLebensmittelID);
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
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }
        cursor.close();
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


    public void onListItemClicked(int i) {
    }

    public void onClickDelete() {
    }

    public void onClickAdd() {
    }
}