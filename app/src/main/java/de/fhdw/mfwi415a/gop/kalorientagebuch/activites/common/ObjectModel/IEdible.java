package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

public interface IEdible {
    public double get_Calories();
    public String get_Name();
    public String get_quantityUnit();
    public String get_quantityAbbreviation();

    public void set_quantityUnit(String unit);
    public void set_quantityAbbreviation(String abr);
}
