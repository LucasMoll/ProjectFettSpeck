package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Home;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.widget.ArrayAdapter;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.navigation.fragments.StatistikFragment;


public class ApplicationLogic {


    private int usedLimit;
    private int DailyLimit;
    private Gui mGui;
    private Context mContext;

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
    }

    private void changeBarValue() {


        double usedSize = (double) usedLimit / DailyLimit * 360;
        mGui.setmUsedBarSizeAndText(usedSize, (usedLimit + " kcal"));
        mGui.setmUnusedBarSizeAndText(360 - usedSize, (DailyLimit - usedLimit) + " kcal");
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
        //mGui.setSnackbar(getCurrentDate());
        mGui.setSnackbar(String.valueOf(getDailyMax()));
        //changeFragment();

    }

    private void changeFragment() {
        Activity activity = (Activity) mContext;
        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, new StatistikFragment()).commit();
    }

    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat mdformat = new SimpleDateFormat("dd.MM.yyyy");
        String strDate = mdformat.format(calendar.getTime());
        return strDate;
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
           i+= cursor.getInt(cursor.getColumnIndex("SUM"));
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
        cursor.moveToFirst();
        while (!cursor.isAfterLast())

        {
            gerichte.add(cursor.getString(cursor.getColumnIndex("KT_Bezeichnung")) +": "+ cursor.getString(cursor.getColumnIndex("GerichtName")) + " (" + String.valueOf(cursor.getInt(cursor.getColumnIndex("SUM")) +" kcal)"));
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