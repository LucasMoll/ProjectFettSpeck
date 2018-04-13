package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import android.database.Cursor;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.DataAdapter;

public class Foodstuff implements IEdible {

    private int _foodstuffsId;
    private String _foodstuffName;
    private double _kcalsPerUnit;
    private String _quantityAbbreviation;
    private String _quantityUnit;
    private int _quantityUnitId;

    public Foodstuff(int id, double kcalsPerUnit)
    {
        this(id, kcalsPerUnit, "");
    }

    public Foodstuff(int id, double kcalsPerUnit, String foodstuffName)
    {
        this._foodstuffsId = id;
        this._kcalsPerUnit = kcalsPerUnit;
        this._foodstuffName = foodstuffName;
    }

    public int get_foodstuffsId()
    {
        return this._foodstuffsId;
    }

    public void set_foodstuffsId(int menuId)
    {
        this._foodstuffsId = menuId;
    }

    public String get_foodstuffName()
    {
        return this._foodstuffName;
    }

    public void set_foodstuffName(String menuName)
    {
        this._foodstuffName = menuName;
    }

    public double get_kcalsPerUnit()
    {
        return this._kcalsPerUnit;
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

    public static Foodstuff retrieveById(int id, int quantityUnitID, DataAdapter dataAdapter)
    {
        Cursor cFoodstuff = dataAdapter.getData("SELECT Lebensmittel.Bezeichnung, Einheit.ID, Einheit.Bezeichnung, Einheit.Kurzbezeichnung, le.Menge FROM 'Lebensmittel'JOIN Lebensmittel_Einheit AS le, Einheit ON le.LebensmittelId = Lebensmittel.ID AND Einheit.ID = le.EinheitId WHERE Lebensmittel.ID = " + id + ";", "lgGetFoodstuff");

        if(cFoodstuff.getCount() < 2)
            return null; // Not valid Data available

        cFoodstuff.moveToFirst();

        double kcals = -1;
        double unitQuantity = 1;
        int quantityUnitId = -1;
        String quantityUnit = null;
        String quantityUnitAbbr = null;

        if(cFoodstuff.isNull(1)) // no name available -> data not valid
            return null;

        String foodstuffName = cFoodstuff.getString(0);

        while(!cFoodstuff.isAfterLast())
        {
            if (!cFoodstuff.isNull(4)) {

                // Check id of unit 1 = kcal
                if (cFoodstuff.getInt(1) == 1) // kcal
                {
                    kcals = cFoodstuff.getDouble(4);
                }
                else if(quantityUnitID == -1 || cFoodstuff.getInt(1) == quantityUnitID) {

                    unitQuantity = cFoodstuff.getDouble(4);

                    if(!cFoodstuff.isNull(1))
                        quantityUnitId = cFoodstuff.getInt(1);

                    if(!cFoodstuff.isNull(2))
                        quantityUnit = cFoodstuff.getString(2);

                    if(!cFoodstuff.isNull(3))
                        quantityUnitAbbr = cFoodstuff.getString(3);

                    // all needed Data available -> break
                    if(kcals > 0)
                        break;
                }

                cFoodstuff.moveToNext();
            }
        }

        cFoodstuff.close();

        double kcalsPerUnit = kcals / unitQuantity;

        Foodstuff foodstuff = new Foodstuff(id, kcalsPerUnit, foodstuffName);
        foodstuff.set_quantityUnit(quantityUnit);
        foodstuff.set_quantityAbbreviation(quantityUnitAbbr);
        foodstuff.set_quantityUnitId(quantityUnitId);

        return foodstuff;
    }

    @Override
    public String toString() {
        return  get_foodstuffName();
    }


    @Override
    public double get_Calories() {
        return get_kcalsPerUnit();
    }

    @Override
    public String get_Name() {
        return get_foodstuffName();
    }
}
