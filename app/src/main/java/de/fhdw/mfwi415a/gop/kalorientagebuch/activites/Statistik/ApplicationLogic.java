package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Statistik;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KtEntryRowAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Foodstuff;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.KTEntry;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Menu;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;
    private ArrayList<Integer> mIndexListUnusedEInheiten;
    private ArrayList<Integer> mIndexListUsedEInheiten;

    private final DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL);

    public ApplicationLogic(Gui gui, Context context) {
        mGui = gui;
        mContext = context;

        initGui();
        initListener();
    }

    private void initGui() {

        TextView lblDate = mGui.getmLblDate();

        Calendar calendar = Calendar.getInstance();

        lblDate.setText(dateFormat.format(calendar.getTime()));

        LoadInitialAverageData();
        LoadData(Calendar.getInstance());
    }

    private void initListener() {
        ClickListener cl = new ClickListener(this);

        mGui.getmLblDate().setOnClickListener(cl);

    }

    public Context getContext()
    {
        return mContext;
    }

    public void onDateChanged(int year, int month, int day)
    {
        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);

        mGui.getmLblDate().setText(dateFormat.format(calendar.getTime()));
        LoadData(calendar);
    }

    private void LoadInitialAverageData()
    {
        Calendar calToday = Calendar.getInstance();

        Calendar calBefore7 = Calendar.getInstance();
        calBefore7.add(Calendar.DAY_OF_MONTH, -7);

        Calendar calBefore14 = Calendar.getInstance();
        calBefore14.add(Calendar.DAY_OF_MONTH, -14);

        Calendar calBefore30 = Calendar.getInstance();
        calBefore30.add(Calendar.DAY_OF_MONTH, -30);

        DataAdapter da = new DataAdapter(mContext);

        double total7 = 0, total14 = 0, total30 = 0, totalToday = 0;
        int mCount7 = 0, mCount14 = 0, mCount30 = 0, mCountToday = 0;
        int dCount7 = 0, dCount14 = 0, dCount30 = 0, dCountToday = 0;

        ArrayList<Date> dates = new ArrayList<>();

        DateFormat iso8601Format = new SimpleDateFormat("yyyy-MM-dd");

        da.open();

        // Get Menus of last 30 Days

        Cursor cMenuLast30d = da.getData("SELECT kg.GerichtID, kg.EinheitID, kg.Menge, Zeitpunkt FROM KTEintrag JOIN KTEintrag_Gericht AS kg ON kg.KTEintragID = KTEintrag.ID WHERE Zeitpunkt >= DATE('now', '-30 days') AND Zeitpunkt <= DATE('now');", "lgGetMenusLast30d");

        if(cMenuLast30d.getCount() > 0)
        {
            cMenuLast30d.moveToFirst();

            while (!cMenuLast30d.isAfterLast())
            {
                if(!cMenuLast30d.isNull(0) && !cMenuLast30d.isNull(3) && !cMenuLast30d.isNull(2))
                {
                    String dateString = cMenuLast30d.getString(3);

                    double quantity = cMenuLast30d.getDouble(2);

                    try {
                        Date date = iso8601Format.parse(dateString);

                        Menu menu = Menu.retrieveById(cMenuLast30d.getInt(0), da);

                        if(menu != null)
                        {
                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calToday.getTime()))) == 0) {
                                //today
                                totalToday += menu.get_menuCalories() * quantity;
                                mCountToday++;

                                if(!dates.contains(date))
                                    dCountToday++;
                            }

                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calBefore7.getTime()))) >= 0) {
                                // last 7 days
                                total7 += menu.get_menuCalories() * quantity;
                                mCount7++;

                                if(!dates.contains(date))
                                    dCount7++;
                            }

                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calBefore14.getTime()))) >= 0) {
                                // last 14 days
                                total14 += menu.get_menuCalories() * quantity;
                                mCount14++;

                                if(!dates.contains(date))
                                    dCount14++;
                            }

                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calBefore30.getTime()))) >= 0) {
                                // last 30 days
                                total30 += menu.get_menuCalories() * quantity;
                                mCount30++;

                                if(!dates.contains(date))
                                    dCount30++;
                            }
                        }

                        if(!dates.contains(date))
                            dates.add(date);
                    }
                    catch (ParseException ex)
                    {
                        cMenuLast30d.moveToNext();
                        continue;
                    }
                }

                cMenuLast30d.moveToNext();
            }
        }

        cMenuLast30d.close();

        // Get Foodstuff of last 30 days

        Cursor cFoodstuffLast30 = da.getData("SELECT kl.LebensmittelID, kl.EinheitID, kl.Menge, Zeitpunkt FROM KTEintrag JOIN KTEintrag_Lebensmittel AS kl ON kl.KTEintragID = KTEintrag.ID WHERE Zeitpunkt >= DATE('now', '-30 days') AND Zeitpunkt <= DATE('now');", "lgGetMenusLast30d");

        if(cFoodstuffLast30.getCount() > 0)
        {
            cFoodstuffLast30.moveToFirst();

            while (!cFoodstuffLast30.isAfterLast())
            {
                if(!cFoodstuffLast30.isNull(0) && !cFoodstuffLast30.isNull(1) && !cFoodstuffLast30.isNull(3) && !cFoodstuffLast30.isNull(2))
                {
                    String dateString = cFoodstuffLast30.getString(3);

                    double quantity = cFoodstuffLast30.getDouble(2);

                    try {
                        Date date = iso8601Format.parse(dateString);

                        Foodstuff foodstuff = Foodstuff.retrieveById(cFoodstuffLast30.getInt(0), cFoodstuffLast30.getInt(1), da);

                        if(foodstuff != null)
                        {
                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calToday.getTime()))) == 0) {
                                //today
                                totalToday += foodstuff.get_kcalsPerUnit() * quantity;
                                mCountToday++;

                                if(!dates.contains(date))
                                    dCountToday++;
                            }

                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calBefore7.getTime()))) >= 0) {
                                // last 7 days
                                total7 += foodstuff.get_kcalsPerUnit() * quantity;
                                mCount7++;

                                if(!dates.contains(date))
                                    dCount7++;
                            }

                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calBefore14.getTime()))) >= 0) {
                                // last 14 days
                                total14 += foodstuff.get_kcalsPerUnit() * quantity;
                                mCount14++;

                                if(!dates.contains(date))
                                    dCount14++;
                            }

                            if (date.compareTo(iso8601Format.parse(iso8601Format.format(calBefore30.getTime()))) >= 0) {
                                // last 30 days
                                total30 += foodstuff.get_kcalsPerUnit() * quantity;
                                mCount30++;

                                if(!dates.contains(date))
                                    dCount30++;
                            }
                        }

                        if(!dates.contains(date))
                            dates.add(date);
                    }
                    catch (ParseException ex)
                    {
                        cFoodstuffLast30.moveToNext();
                        continue;
                    }
                }

                cFoodstuffLast30.moveToNext();
            }
        }

        cFoodstuffLast30.close();

        da.close();

        // TODO: maybe write values to members of class

        //mGui.getmLblCaloriesToday().setText(String.format(Locale.ROOT, "%.2f", (mCountToday == 0 ? 0 : totalToday / mCountToday)) + " kcals");
        mGui.getmLblCaloriesToday().setText(String.format(Locale.ROOT, "%.2f", totalToday) + " kcals");

        // avg per menu

        //mGui.getmLblCaloriesAvgSeven().setText(String.format(Locale.ROOT, "%.2f", (mCount7 == 0 ? 0 : total7 / mCount7)) + " kcals");
        //mGui.getmLblCaloriesAvgFourteen().setText(String.format(Locale.ROOT, "%.2f", (mCount14 == 0 ? 0 : total14 / mCount14)) + " kcals");
        //mGui.getmLblCaloriesAvgThirty().setText(String.format(Locale.ROOT, "%.2f", (mCount30 == 0 ? 0 : total30 / mCount30)) + " kcals");

        // avg per day
        mGui.getmLblCaloriesAvgSeven().setText(String.format(Locale.ROOT, "%.2f", (dCount7 == 0 ? 0 : total7 / dCount7)) + " kcals");
        mGui.getmLblCaloriesAvgFourteen().setText(String.format(Locale.ROOT, "%.2f", (dCount14 == 0 ? 0 : total14 / dCount14)) + " kcals");
        mGui.getmLblCaloriesAvgThirty().setText(String.format(Locale.ROOT, "%.2f", (dCount30 == 0 ? 0 : total30 / dCount30)) + " kcals");

    }

    private void LoadData(Calendar calendar)
    {
        ArrayList<KTEntry> KtEntries = new ArrayList<>();

        DataAdapter da = new DataAdapter(mContext);

        da.open();

        // Get KTentries for current Day

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dt = simpleDateFormat.format(calendar.getTime());

        String sqlCommand = "SELECT Id, Bezeichnung, Zeitpunkt FROM KTEintrag WHERE Zeitpunkt = '" + dt + "';";

        Cursor cKTEntries = da.getData(sqlCommand, "lgGetKTEntriesFromDate");

        if(cKTEntries.getCount() > 0)
        {
            cKTEntries.moveToFirst();

            while(!cKTEntries.isAfterLast())
            {
                if(!cKTEntries.isNull(0))
                {
                    KTEntry entry = new KTEntry(cKTEntries.getInt(0));

                    if (!cKTEntries.isNull(1))
                    {
                        entry.set_description(cKTEntries.getString(1));
                    }

                    // Get Menus
                    Cursor cMenus = da.getData(String.format(Locale.US, "SELECT GerichtID, Menge FROM KTEintrag_Gericht WHERE KTEintragID = %d", entry.get_id()), "lgGetGerichtOfKtEntry");

                    if(cMenus.getCount() > 0)
                    {
                        cMenus.moveToFirst();

                        while(!cMenus.isAfterLast()) {
                            if (!cMenus.isNull(0)) {
                                Menu menu = Menu.retrieveById(cMenus.getInt(0), da);

                                if (!cMenus.isNull(1) && menu != null) {
                                    entry.set_totalCalories(entry.get_totalCalories() + menu.get_menuCalories() * cMenus.getDouble(1));
                                    entry.set_ingredient(menu.get_menuName());
                                }
                            }

                            cMenus.moveToNext();
                        }
                    }

                    cMenus.close();

                    // Get foodstuff
                    Cursor cFoodstuff = da.getData(String.format(Locale.US, "SELECT LebensmittelId, Menge, EinheitId FROM KTEintrag_Lebensmittel WHERE KTEintragID = %d", entry.get_id()), "lgGetFoodstuffOfKtEntry");

                    if(cFoodstuff.getCount() > 0)
                    {
                        cFoodstuff.moveToFirst();

                        while(!cFoodstuff.isAfterLast()) {

                            if (!cFoodstuff.isNull(0) && !cFoodstuff.isNull(2)) {
                                Foodstuff foodstuff = Foodstuff.retrieveById(cFoodstuff.getInt(0), cFoodstuff.getInt(2), da);

                                if (!cFoodstuff.isNull(1) && foodstuff != null) {
                                    entry.set_totalCalories(entry.get_totalCalories() + foodstuff.get_kcalsPerUnit() * cFoodstuff.getDouble(1)); // TODO: rework with unit used in KTentry
                                    entry.set_ingredient(foodstuff.get_foodstuffName());
                                }
                            }

                            cFoodstuff.moveToNext();
                        }
                    }

                    cFoodstuff.close();

                    KtEntries.add(entry);

                }

                cKTEntries.moveToNext();
            }
        }

        da.close();

        KtEntryRowAdapter entryAdapter = new KtEntryRowAdapter(mContext, R.layout.ktentry_row, KtEntries);

        mGui.getListViewEntries().setAdapter(entryAdapter);
    }
}