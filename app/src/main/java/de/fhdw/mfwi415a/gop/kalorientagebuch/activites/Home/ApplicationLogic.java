package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.AddKTitemFragment;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.StatistikFragment;


public class ApplicationLogic {


    private int usedLimit;
    private int DailyLimit;
    private Gui mGui;
    private Context mContext;
    private ArrayList<Integer> mIndexList = new ArrayList<Integer>();

    public ApplicationLogic(Gui gui, Context context) {
        mGui = gui;
        mContext = context;
        initGui();
        initListener();
    }

    private void initGui() {
        DailyLimit = getDailyMax();
        usedLimit = getUsedLimit();
        showGerichteOfDay();
        changeBarValue();
        setLimitText();
    }

    private void initListener() {
        ClickListener cl;

         cl = new ClickListener(this);
        mGui.getmHomePlusFab().setOnClickListener(cl);
        mGui.getmListView().setOnItemClickListener(new OnItemClickListener(this));
    }

    private void changeBarValue() {
        int unusedSize = 0;

        if (usedLimit >= DailyLimit)
        {
            unusedSize = 0;
        } else {

            unusedSize = (int) DailyLimit - usedLimit;
        }

        mGui.setmUsedBarSizeAndText(usedLimit, (usedLimit + " kcal"));
        mGui.setmUnusedBarSizeAndText(unusedSize, (unusedSize + " kcal"));
    }

    private void setSnackbar(String s){
        mGui.setSnackbar(s);
    }

    public void onListItemClicked(int i){
        String s = String.valueOf(mIndexList.get(i));
        setSnackbar(s);
//TODO: Fragment wechseln und mIndexList.get(i) als Argument übergeben (Bundle: "KT_ID",mIndexList.get(i))
    }

    private void setLimitText()
    {
        String text;
        if (usedLimit<DailyLimit)
        {
            text = "Du darfst heute noch " + (DailyLimit-usedLimit) + " kcal zur dir nehmen, bevor du dein Ziel überschreitest.";
        }else if (usedLimit>DailyLimit)
        {
            text = "Du hast dein Ziel heute leider schon um "+ (usedLimit-DailyLimit) +" kcal überschritten!";
        }
        else { text = "Du hast dein Ziel erreicht!";}

        mGui.getmMotivationText().setText(text);
    }

    public void onActivityReturned(int requestCode, int resultCode, Intent data) {

    }


    public void onPlusFabClicked() {
        changeFragment(new AddKTitemFragment(), 0);

    }

    private void changeFragment(Fragment f, int i) {
        Activity activity = (Activity) mContext;

        Bundle bundle = new Bundle();
        bundle.putInt("KT_ID", i);
        f.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, f).commit();
    }

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = mdformat.format(calendar.getTime());
        //return strDate;
        return "06.04.2018";
    }

    private int getDailyMax() {
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getDailyMax();
        ArrayList<Integer> max = new ArrayList<Integer>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            max.add(cursor.getInt(cursor.getColumnIndex("Tageslimit")));
            cursor.moveToNext();
        }

        cursor.close();

        return max.get(0);
    }


    private int getUsedLimit(){
        int i=0;
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getGerichteOfDay(getCurrentDate());

        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
           i+= cursor.getInt(cursor.getColumnIndex("Summe"));
            cursor.moveToNext();
        }
        cursor.close();
        return i;

    }

    private void showGerichteOfDay(){
        DataAdapter mDbHelper = new DataAdapter(mContext);
        mDbHelper.createDatabase();
        mDbHelper.open();

        Cursor cursor = mDbHelper.getGerichteOfDay(getCurrentDate());

        ArrayList<String> gerichte = new ArrayList<String>();
        mIndexList.clear();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            gerichte.add(cursor.getString(0) +": "+ cursor.getString(1) + " (" + String.valueOf(cursor.getInt(cursor.getColumnIndex("Summe"))) +" kcal)");
            mIndexList.add(cursor.getInt(3));
            cursor.moveToNext();
        }

        setmListViewText(gerichte);
        cursor.close();

    }

    private void setmListViewText(ArrayList<String> arrayList){
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, arrayList );
        mGui.getmListView().setAdapter(arrayAdapter);
    }

}