package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

public class MenuItem {

    private int _foodstuffsId;
    private String _foodstuffName;
    private double _kcals;
    private double _quantity;
    private String _quantityAbbreviation;
    private String _quantityUnit;

    public MenuItem()
    {

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

    public double get_calories()
    {
        return this._kcals;
    }

    public void set_calories(double calories)
    {
        this._kcals = calories;
    }

    public double get_quantity()
    {
        return this._quantity;
    }

    public void set_quantity(double amount)
    {
        this._quantity = amount;
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

    @Override
    public String toString() {
        return get_quantity() + " " + get_quantityUnit() + " of " + get_foodstuffName() + " have " + get_calories() + " kcal.";
    }
}
