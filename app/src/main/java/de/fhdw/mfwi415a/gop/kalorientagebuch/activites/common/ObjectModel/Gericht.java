package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by pschoger on 20.03.2018.
 */

public class Gericht implements Parcelable {
    private String _Bezeichnung;
//    private Map<Nahrungsmittel, Map.Entry<String, Double>> _Nahrungsmittel;
    private Bundle _Nahrungsmittel;

    public Gericht()
    {
        _Nahrungsmittel = new Bundle();
    }

    public String get_Bezeichnung() {
        return _Bezeichnung;
    }

    public void set_Bezeichnung(String _Bezeichnung) {
        this._Bezeichnung = _Bezeichnung;
    }

    public Bundle get_Nahrungsmittel() {return  _Nahrungsmittel;}

    public void set_Nahrunsmittel(Bundle ingredients) { this._Nahrungsmittel = ingredients; }

    @Override
    public String toString() {
        return _Bezeichnung;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._Bezeichnung);
        dest.writeBundle(this._Nahrungsmittel);
    }

    protected Gericht(Parcel in) {
        this._Bezeichnung = in.readString();
        this._Nahrungsmittel = in.readBundle();
    }

    public static final Creator<Gericht> CREATOR = new Creator<Gericht>() {
        @Override
        public Gericht createFromParcel(Parcel source) {
            return new Gericht(source);
        }

        @Override
        public Gericht[] newArray(int size) {
            return new Gericht[size];
        }
    };
}
