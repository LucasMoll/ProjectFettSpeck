package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import android.database.Cursor;

import java.util.ArrayList;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;

public class Menu implements IEdible {

    private int _menuId;
    private String _menuName;
    private ArrayList<MenuItem> _menuItems = new ArrayList<>();

    private String _quantityAbbreviation;
    private String _quantityUnit;
    private int _quantityUnitId;

    public Menu(int menuId) { this(menuId, ""); }

    public Menu(int menuId, String menuName)
    {
        this._menuId = menuId;
        this._menuName = menuName;
    }

    public int get_menuId()
    {
        return _menuId;
    }

    public String get_menuName()
    {
        return _menuName;
    }

    public ArrayList<MenuItem> get_menuItems() {
        return _menuItems;
    }

    public double get_menuCalories()
    {
        double totalCals = 0;

        for(MenuItem mi: get_menuItems())
            totalCals += mi.get_calories();

        return totalCals;
    }

    public String get_quantityAbbreviation()
    {
        return this._quantityAbbreviation;
    }

    public void set_quantityAbbreviation(String quantityAbbreviation)
    {
        this._quantityAbbreviation = quantityAbbreviation;
    }

    public String get_quantityUnit()
    {
        return this._quantityUnit;
    }

    public void set_quantityUnit(String unit)
    {
        this._quantityUnit = unit;
    }

    public int get_quantityUnitId()
    {
        return _quantityUnitId;
    }

    public void set_quantityUnitId(int quantityUnitId)
    {
        this._quantityUnitId = quantityUnitId;
    }


    public static Menu retrieveById(int menuId, DataAdapter dataAdapter)
    {
        // Get Menu

        dataAdapter.open();

        Cursor nameCur = dataAdapter.getData("SELECT Bezeichnung FROM Gericht WHERE ID = " + menuId + ";", "lgGetMenuName");

        if(nameCur.getCount() == 0)
            return null;

        nameCur.moveToFirst();

        String menuName = nameCur.getString(0);

        nameCur.close();

        Menu m = new Menu(menuId, menuName);

        // Get MenuItems

        Cursor cItemIds = dataAdapter.getData("SELECT LebensmittelId FROM Lebensmittel_Gericht WHERE GerichtId = " + menuId + ";", "lgGetMenuItemIds");

        if(cItemIds.getCount() > 0)
        {
            cItemIds.moveToFirst();

            while (!cItemIds.isAfterLast())
            {
                if(!cItemIds.isNull(0))
                {
                    int foodstuffId = cItemIds.getInt(0);

                    MenuItem mItem = MenuItem.retrieveById(foodstuffId, menuId, dataAdapter);

                    if(mItem != null)
                        m.get_menuItems().add(mItem);
                }

                cItemIds.moveToNext();
            }
        }

        cItemIds.close();

        return m;
    }

    public static Menu retrieveByName(String menuName, DataAdapter dataAdapter)
    {
        return null;
    }

    @Override
    public double get_Calories() {
        return get_menuCalories();
    }

    @Override
    public String get_Name() {
        return get_menuName();
    }
}
