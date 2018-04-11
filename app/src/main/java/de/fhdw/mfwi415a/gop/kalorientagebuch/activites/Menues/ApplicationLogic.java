package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Menues;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;

import java.util.ArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddEinheitFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddMenueFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.MenuDetailFragment;

public class ApplicationLogic {


    private Gui mGui;
    private Context mContext;
    private ArrayList<Integer> mIDList = new ArrayList<Integer>();

    public ApplicationLogic(Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {

        showallGerichte();

    }

    private void showallGerichte() {

        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        Cursor cursor = mDBHelper.getAllGerichte();

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

    private void setmListViewText(ArrayList<String> gerichte) {

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, gerichte);
        mGui.getmListView().setAdapter(arrayAdapter);
    }

    private void initListener() {

        ClickListener cl;
        cl = new ClickListener(this);

        mGui.getmListView().setOnItemClickListener(new OnItemClickListener(this));
        mGui.getmFloat().setOnClickListener(cl);


    }

    private void setSnackbar(String s) {
        mGui.setSnackbar(s);
    }

    public void OnListItemClicked(int i) {
        Activity activity = (Activity) mContext;

        Bundle bundle = new Bundle();
        bundle.putInt("Menue_ID",mIDList.get(i));
        MenuDetailFragment F = new MenuDetailFragment();
        F.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, F).commit();

    }

        private void changeFragment(Fragment f,int i){
            Activity activity = (Activity) mContext;

            Bundle bundle = new Bundle();
            bundle.putInt("Menue_ID", i);
            f.setArguments(bundle);

            FragmentManager fragmentManager = activity.getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, f).commit();
        }

        public void onPlusFabClicked() {

            changeFragment(new AddMenueFragment(), 0);

        }
    }
