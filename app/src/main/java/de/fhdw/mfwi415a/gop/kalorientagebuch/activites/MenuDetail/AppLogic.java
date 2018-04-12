package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuDetail;

import android.content.Context;

import de.fhdw.mfwi415a.gop.kalorientagebuch.R;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.MenuRowAdapter;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.*;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Menu;


public class AppLogic {

    private Gui mGui;
    private Context mContext;
    private int _currentMenuID;

    private Menu _menu;

    public AppLogic (Gui gui, Context context, int menuID) {
        mGui = gui;
        mContext = context;

        _currentMenuID = menuID;
        initGui();
    }

    public String get_menuName()
    {
        return _menu.get_menuName();
    }

    private void initGui() {

        showAllIngredients();
    }

    private void showAllIngredients() {

        DataAdapter mDBHelper = new DataAdapter(mContext);
        mDBHelper.createDatabase();
        mDBHelper.open();

        _menu = Menu.retrieveById(_currentMenuID, mDBHelper);

        /*Cursor cMenu = mDBHelper.getData("SELECT Bezeichnung FROM Gericht WHERE ID = " + _currentMenuID + ";", null);


        cMenu.moveToFirst();

        _menuName = cMenu.getString(0);

        cMenu.close();


        Cursor cursor = mDBHelper.getData("SELECT l.ID, l.Bezeichnung, lg.Menge, e.Bezeichnung AS Mengeneinheit, e.Kurzbezeichnung, leCal.Menge AS kcal FROM Lebensmittel_Gericht AS lg, Lebensmittel_Einheit leCal JOIN Lebensmittel AS l, Einheit AS e ON l.ID = lg.LebensmittelID AND e.ID = lg.EinheitID WHERE leCal.EinheitID = 1 AND leCal.LebensmittelID = lg.LebensmittelID AND lg.GerichtID = " + _currentMenuID + ";", "");

        cursor.moveToFirst();

        int foodstuffsIdIndex = cursor.getColumnIndex("l.ID");
        int foodstuffsNameIndex = cursor.getColumnIndex("l.Bezeichnung");
        int quantityIndex = cursor.getColumnIndex("lg.Menge");
        int quantityUnitIndex = cursor.getColumnIndex("Mengeneinheit");
        int quantityAbbreviation = cursor.getColumnIndex("Kurzbezeichnung");
        int kcalIndex = cursor.getColumnIndex("kcal");

        while (!cursor.isAfterLast())
        {
            MenuItem item = new MenuItem();

            item.set_foodstuffsId(cursor.getInt(foodstuffsIdIndex));
            item.set_foodstuffName(cursor.getString(foodstuffsNameIndex));
            item.set_quantity(cursor.getDouble(quantityIndex));
            item.set_quantityUnit(cursor.getString(quantityUnitIndex));
            item.set_quantityAbbreviation(cursor.getString(quantityAbbreviation));
            item.set_calories(cursor.getDouble(kcalIndex));

            _ingredients.add(item);
            cursor.moveToNext();
        }
        cursor.close();*/

        mDBHelper.close();

        if(_menu != null)
        {
            MenuRowAdapter menuItemArrayAdapter = new MenuRowAdapter(mContext, R.layout.ingredient_row, _menu.get_menuItems());
            mGui.getListViewIngredients().setAdapter(menuItemArrayAdapter);
            mGui.getLblMenuTotalCalories().setText(String.format("%.2f", _menu.get_menuCalories()) + " kcal");
        }
    }


    /*public void OnListItemClicked(int i) {

        Activity activity = (Activity) mContext;

        Bundle bundle = new Bundle();
        bundle.putInt("Menue_ID",mIDList.get(i));
        MenuDetailFragment F = new MenuDetailFragment();
        F.setArguments(bundle);

        FragmentManager fragmentManager = activity.getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, F).commit();

    }*/
}
