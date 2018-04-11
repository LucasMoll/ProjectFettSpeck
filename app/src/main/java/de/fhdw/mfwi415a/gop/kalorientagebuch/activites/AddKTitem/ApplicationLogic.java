package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddKTitem;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.HomeFragment;

public class ApplicationLogic {


    private Gui mGui;
    private Context mContext;
    private DialogClickListener dcl;
    private ArrayList<Integer> mIDList = new ArrayList<Integer>();
    private View mDialogView;
    private int currentGericht = 0;
    private int KTE_ID;
    private String mInsert_KTEEintrag_Gericht = "INSERT INTO KTEintrag_Gericht (GerichtID,KTEintragID,EinheitID, Menge) VALUES";

    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }


    private void initGui() {
        showAllGerichte();
        KTE_ID = getMaxKTEId() +1;

    }

    private void initListener() {

        ClickListener cl = new ClickListener(this);

        dcl = new DialogClickListener(this);
        mGui.getmMenueListe().setOnItemClickListener(new OnItemClickListener(this));

        mGui.getmSaveButton().setOnClickListener(cl);


    }

    private void showAllGerichte(){
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllGerichte();

        ArrayList<String> gerichte = new ArrayList<String>();
        cursor.moveToFirst();
        mIDList.clear();
        while (!cursor.isAfterLast())

        {
            mIDList.add(cursor.getInt(cursor.getColumnIndex("ID")));
            gerichte.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }

        setmListViewText(gerichte);
        cursor.close();

    }
    private void setmListViewText(ArrayList<String> arrayList){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arrayList );
        mGui.getmMenueListe().setAdapter(arrayAdapter);
    }

    private String getNameOfGericht(int i){

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getNameOfGericht(i);

        ArrayList<String> name = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            name.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }

        cursor.close();

        return name.get(0);

    }

    private int getMaxKTEId(){

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getMaxKTE_ID();

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


    public void onListClicked(int i) {

        showDialog(mIDList.get(i));


    }

    private void showDialog(int i)
    {

        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.addkt_dialog_layout, null, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        alertBuilder.setView(mDialogView);

        alertBuilder.setTitle(getNameOfGericht(i));
        currentGericht = i;

        alertBuilder.setCancelable(true).setPositiveButton("OK", dcl);
        alertBuilder.setCancelable(true).setNegativeButton("Abbrechen", dcl);

        Dialog dialog = alertBuilder.create();
        dialog.show();

    }


    public void onOkClicked() {
        TextView Menge = (TextView) mDialogView.findViewById(R.id.Portion_Menge);
        Float f = Float.parseFloat(Menge.getText().toString());
        mInsert_KTEEintrag_Gericht += " (" + currentGericht + ", " + KTE_ID + ", 7, " + f + "), ";
        Log.d("TEST", mInsert_KTEEintrag_Gericht);
    }

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
    }
    public void onSaveClicked() {

        Log.d("TEST", "onSaveClicked");

        String insert1 = "INSERT INTO KTEintrag (ID,Zeitpunkt,Bezeichnung) VALUES" + " ("+ KTE_ID+ ", \"" + getCurrentDate()+"\", \"" + mGui.getmBezeichnung().getText().toString() + "\")";
        String insert2 = mInsert_KTEEintrag_Gericht.substring(0, mInsert_KTEEintrag_Gericht.length()-2);

        Log.d("TEST", insert1);
        Log.d("TEST", insert2);

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        mDbHelper.writeData(insert1, "AddKTEintrag");
        mDbHelper.writeData(insert2, "AddKTEintrag_Gericht");

        changeFragment(new HomeFragment(), 0);


    }

    private void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        FragmentManager fragmentManager = activity.getFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction().replace(R.id.content_frame, f).addToBackStack("tag");
        ft.detach(f).attach(f).commitAllowingStateLoss();
    }
}
