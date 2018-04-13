package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KTEntryDetail;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KtEntryDetailRowAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.KtEntryRowAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Edible;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Foodstuff;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.IEdible;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.KTEntry;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Menu;


public class ApplicationLogic {

    private Gui mGui;
    private Context mContext;
    private ArrayList<Integer> mIndexListUnusedEInheiten;
    private ArrayList<Integer> mIndexListUsedEInheiten;

    KTEntry mCurrentEntry;

    private int mEntryId;

    private String mEntryName;

    public ApplicationLogic(Gui gui, Context context, int entryId) {
        mGui = gui;
        mContext = context;

        mEntryId = entryId;

        initGui();
        initListener();
    }

    private void initGui() {
        LoadData(mEntryId);
    }

    private void initListener() {
        ClickListener cl = new ClickListener(this);

        mGui.getmLblKtEntryTotalCalories().setOnClickListener(cl);

        mGui.getFabDuplicate().setOnClickListener(cl);
        mGui.getFabDelete().setOnClickListener(cl);

    }

    public Context getContext() {
        return mContext;
    }

    public int getEntryId() {
        return mEntryId;
    }

    public String getEntryName() {
        return mCurrentEntry.get_description();
    }

    public void duplicateEntry()
    {
        DataAdapter da = new DataAdapter(mContext);

        da.open();

        da.writeData(String.format("INSERT INTO KTEintrag VALUES (null, DATE('now'), '%s')", mCurrentEntry.get_description()), "lgInsertKTEntry");

        Cursor cIdNew = da.getData("SELECT MAX(ID) FROM KTEintrag;", "lgGetMaxKTId");

        if(cIdNew.getCount() > 0)
        {
            int newEntryId = cIdNew.getInt(0);

            // duplicate MenuEntries

            da.writeData(String.format("INSERT INTO KTEintrag_Gericht (ID, GerichtId, KTEintragID, EinheitId, Menge) SELECT null, GerichtId, %d, EinheitId, Menge FROM KTEintrag_Gericht WHERE KTEintragId = %d;", newEntryId, mCurrentEntry.get_id()), "lgCopyMenuData");

            // duplicate foodstuffEntries

            da.writeData(String.format("INSERT INTO KTEintrag_Lebensmittel (ID, LebensmittelId, KTEintragID, EinheitId, Menge) SELECT null, LebensmittelId, %d, EinheitId, Menge FROM KTEintrag_Lebensmittel WHERE KTEintragId = %d;", newEntryId, mCurrentEntry.get_id()), "lgCopyFoodstuffData");

        }

        da.close();

        Toast.makeText(mContext, "Eintrag " +  mCurrentEntry.get_description() + " wurde dupliziert", Toast.LENGTH_LONG).show();
    }

    public void deleteEntry()
    {
        DataAdapter da = new DataAdapter(mContext);

        da.open();

        da.writeData("DELETE FROM KTEintrag WHERE ID = " + mCurrentEntry.get_id(), "lgDeleteKTEntry");

        da.close();

        Toast.makeText(mContext, "Eintrag " +  mCurrentEntry.get_description() + " wurde gelöscht", Toast.LENGTH_LONG).show();

        ((Activity)mContext).onBackPressed();
    }

    public void LoadData(int entryId) {

        ArrayList<Edible> edibles = new ArrayList<>();

        DataAdapter da = new DataAdapter(mContext);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        da.open();

        // Get KTentries for currentId

        String sqlCommand = "SELECT Id, Bezeichnung, Zeitpunkt FROM KTEintrag WHERE ID = " + entryId + ";";

        Cursor cKTEntry = da.getData(sqlCommand, "lgGetKTEntryById");

        if (cKTEntry.getCount() > 0) {
            cKTEntry.moveToFirst();

            if (!cKTEntry.isNull(0)) {
                KTEntry entry = new KTEntry(cKTEntry.getInt(0));

                if (!cKTEntry.isNull(1)) {
                    entry.set_description(cKTEntry.getString(1));
                }

                if (!cKTEntry.isNull(2)) {
                    try {
                        Date date = simpleDateFormat.parse(cKTEntry.getString(2));
                        entry.set_date(date);
                    } catch (ParseException ex) {

                    }
                }

                mCurrentEntry = entry;

                // Get Menus
                Cursor cMenus = da.getData(String.format(Locale.US, "SELECT GerichtID, Menge, Einheit.Bezeichnung, Einheit.Kurzbezeichnung FROM KTEintrag_Gericht JOIN Einheit ON Einheit.Id = KTEintrag_Gericht.EinheitId WHERE KTEintragID = %d", entry.get_id()), "lgGetGerichtOfKtEntry");

                if (cMenus.getCount() > 0) {
                    cMenus.moveToFirst();

                    while (!cMenus.isAfterLast()) {
                        if (!cMenus.isNull(0))
                        {
                            Menu menu = Menu.retrieveById(cMenus.getInt(0), da);

                            if (!cMenus.isNull(1) && menu != null) {

                                if(!cMenus.isNull(2))
                                    menu.set_quantityUnit(cMenus.getString(2));

                                if(!cMenus.isNull(3))
                                    menu.set_quantityAbbreviation(cMenus.getString(3));

                                Edible edible = new Edible(menu, cMenus.getDouble(1));

                                edibles.add(edible);
                            }
                        }

                        cMenus.moveToNext();
                    }
                }

                cMenus.close();

                // Get foodstuff
                Cursor cFoodstuff = da.getData(String.format(Locale.US, "SELECT LebensmittelId, Menge, EinheitId FROM KTEintrag_Lebensmittel WHERE KTEintragID = %d", entry.get_id()), "lgGetFoodstuffOfKtEntry");

                if (cFoodstuff.getCount() > 0) {
                    cFoodstuff.moveToFirst();

                    while (!cFoodstuff.isAfterLast()) {

                        if (!cFoodstuff.isNull(0) && !cFoodstuff.isNull(2)) {
                            Foodstuff foodstuff = Foodstuff.retrieveById(cFoodstuff.getInt(0), cFoodstuff.getInt(2), da);

                            if (!cFoodstuff.isNull(1) && foodstuff != null)
                            {
                                Edible edible = new Edible(foodstuff, cFoodstuff.getDouble(1));
                                edibles.add(edible);
                            }
                        }

                        cFoodstuff.moveToNext();
                    }
                }

                cFoodstuff.close();
            }
        }

        da.close();

        KtEntryDetailRowAdapter entryAdapter = new KtEntryDetailRowAdapter(mContext, R.layout.kt_entry_detail_row, edibles);

        double totalCalories = 0;

        for(Edible e : edibles)
            totalCalories += e.getQuantity() * e.getEdible().get_Calories();

        mGui.getmLblKtEntryTotalCalories().setText(String.format("%.2f kcals", totalCalories));

        mGui.getListViewEntries().setAdapter(entryAdapter);
    }
}