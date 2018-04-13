package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

public class Edible {

    IEdible mEdible;
    double mQuantity;

    public Edible(IEdible edible, double quantity) {

        mEdible = edible;
        mQuantity = quantity;
    }

    public double getQuantity() {
        return mQuantity;
    }

    public IEdible getEdible() {
        return mEdible;
    }
}
