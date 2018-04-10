package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten.Gui;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten.OnItemClickListener;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddEinheitFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.EinheitenFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.MenuDetailFragment;

public class ApplicationLogic {


    private de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Einheiten.Gui mGui;
    private Context mContext;
    private ArrayList <Integer> mIDList = new ArrayList<Integer>();


    public ApplicationLogic (Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();

    }

    private void initGui() {

        showallEinheiten();

    }

    private void showallEinheiten() {

        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        Cursor cursor = mDBHelper.getAllEinheiten();

        ArrayList<String> einheiten = new ArrayList<String>();
        cursor.moveToFirst();
        mIDList.clear();
        while (!cursor.isAfterLast())

        {
            mIDList.add(cursor.getInt(cursor.getColumnIndex("ID")));
            einheiten.add(cursor.getString(cursor.getColumnIndex("Bezeichnung"))+ " (" + cursor.getString(cursor.getColumnIndex("Kurzbezeichnung"))+")");
            cursor.moveToNext();
        }

        setmListViewText(einheiten);
        cursor.close();

    }

    private void setmListViewText(ArrayList<String> einheiten) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, einheiten );
        mGui.getmListView().setAdapter(arrayAdapter);
    }

    private void initListener() {

        mGui.getmListView().setOnItemClickListener(new OnItemClickListener(this));

    }

    public void OnListItemClicked(int i) {

        Activity activity = (Activity) mContext;

        Bundle bundle = new Bundle();
        bundle.putInt("Einheit_ID",mIDList.get(i));
        AddEinheitFragment F = new AddEinheitFragment();
        F.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, F).commit();

    }

}
