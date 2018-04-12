package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.ClickListener;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.DialogClickListener;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.AddMenue.OnItemClickListener;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;

public class ApplicationLogic {


    private Gui mGui;
    private Context mContext;
    private DialogClickListener dcl;
    private View mDialogView;
    private int currentLebensmittel = 0;
    private ArrayList<Integer> mIDList = new ArrayList<Integer>();

    public ApplicationLogic(Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {
        showAllLebensmittel();
    }

    private void initListener() {

        ClickListener cl = new ClickListener(this);

        dcl = new DialogClickListener(this);
        mGui.getmLebensmittelliste().setOnItemClickListener(new OnItemClickListener(this));
    }

    private void showAllLebensmittel() {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllLebensmittel();
        mIDList.clear();
        cursor.moveToFirst();
        ArrayList<String> bezeichnungen = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            mIDList.add(cursor.getInt(cursor.getColumnIndex("ID")));
            bezeichnungen.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }
        setmListViewText(bezeichnungen);
        cursor.close();
    }


    private String getNameOfLebensmittel(int i) {

        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getAllLebensmittel();

        ArrayList<String> name = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            mIDList.add(cursor.getInt(cursor.getColumnIndex("ID")));
            name.add(cursor.getString(cursor.getColumnIndex("Bezeichnung")));
            cursor.moveToNext();
        }

        cursor.close();

        return name.get(0);

    }

    private void setmListViewText(ArrayList<String> arrayList){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arrayList );
        mGui.getmLebensmittelliste().setAdapter(arrayAdapter);
    }

    public void onListClicked(int i) {

        showDialog(mIDList.get(i));

    }

    private void showDialog(int i)
    {

        mDialogView = LayoutInflater.from(mContext).inflate(R.layout.add_menue_dialog_layout, null, false);
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(mContext);
        alertBuilder.setView(mDialogView);

        alertBuilder.setTitle(getNameOfLebensmittel(i));
        currentLebensmittel = i;

        alertBuilder.setCancelable(true).setPositiveButton("OK", dcl);
        alertBuilder.setCancelable(true).setNegativeButton("Abbrechen", dcl);

        Dialog dialog = alertBuilder.create();
        dialog.show();

    }

    public void onOkClicked() {
        TextView Menge = (TextView) mDialogView.findViewById(R.id.Portion_Menue);
        Float f = Float.parseFloat(Menge.getText().toString());
    }

}
