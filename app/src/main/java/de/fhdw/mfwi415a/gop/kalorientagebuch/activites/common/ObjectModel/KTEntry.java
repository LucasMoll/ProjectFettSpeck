package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import java.util.Date;

public class KTEntry
{
    private int _id;
    private String _description;
    private double _totalCalories;
    private Date _date;
    private String _ingredient;

    public KTEntry(int id)
    {
        this(id, "");
    }

    public KTEntry(int id, String description)
    {
        this._id = id;
        this._description = description;
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int id)
    {
        _id = id;
    }

    public String get_description()
    {
        return _description;
    }

    public void set_description(String description)
    {
        _description = description;
    }

    public double get_totalCalories()
    {
        return _totalCalories;
    }

    public void set_totalCalories(double totalCalories)
    {
        _totalCalories = totalCalories;
    }

    public Date get_date()
    {
        return _date;
    }

    public void set_date (Date date)
    {
        _date = date;
    }

    public String get_ingredient()
    {
        return _ingredient == null ? "" : _ingredient;
    }

    public void set_ingredient(String ingredient)
    {
        _ingredient = ingredient;
    }
}
