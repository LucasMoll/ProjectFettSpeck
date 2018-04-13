package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import android.database.Cursor;
import android.view.Menu;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;

public class MenuItem {

    private Foodstuff _foodstuff;
    private double _quantity;

    public MenuItem(Foodstuff foodstuff)
    {
        this(foodstuff, 0.0);
    }

    public MenuItem(Foodstuff foodstuff, double quantity)
    {
        this._foodstuff = foodstuff;
        this._quantity = quantity;
    }

    public Foodstuff get_foodstuff() {
        return _foodstuff;
    }

    public void set_foodstuff(Foodstuff foodstuff)
    {
        if(_foodstuff != null)
            _foodstuff = foodstuff;
    }

    public double get_calories()
    {
        return this.get_foodstuff().get_kcalsPerUnit() * this._quantity;
    }

    public double get_quantity()
    {
        return this._quantity;
    }

    public void set_quantity(double amount)
    {
        this._quantity = amount;
    }

    public static MenuItem retrieveById(int foodstuffId, int menuId, DataAdapter dataAdapter)
    {
        Cursor cQuant = dataAdapter.getData("SELECT Menge, EinheitId FROM Lebensmittel_Gericht WHERE LebensmittelId = " + foodstuffId + " AND GerichtId = " + menuId + ";", "lgGetMenuItemQuantity");

        if(cQuant.getCount() == 0)
            return null;

        cQuant.moveToFirst();

        if(cQuant.isNull(0))
            return null;

        double quantity = cQuant.getDouble(0);

        int quantityUnitId = -1;

        if(!cQuant.isNull(1))
        {
            quantityUnitId = cQuant.getInt(1);
        }

        cQuant.close();

        Foodstuff foodstuff = Foodstuff.retrieveById(foodstuffId, quantityUnitId, dataAdapter);

        MenuItem item = new MenuItem(foodstuff, quantity);

        return item;
    }


    @Override
    public String toString() {
        return get_quantity() + " " + get_foodstuff().get_quantityUnit() + " of " + get_foodstuff().get_foodstuffName() + " have " + get_calories() + " kcal.";
    }
}
