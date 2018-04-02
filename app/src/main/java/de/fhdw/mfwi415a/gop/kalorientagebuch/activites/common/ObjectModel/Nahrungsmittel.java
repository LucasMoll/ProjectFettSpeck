package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pschoger on 20.03.2018.
 */

public class Nahrungsmittel implements Parcelable {
    private String _Bezeichnung;
    private Bundle _Einheiten;

    public Nahrungsmittel()
    {
        _Einheiten = new Bundle();
    }

    public String get_Bezeichnung() {
        return _Bezeichnung;
    }

    public void set_Bezeichnung(String _Bezeichnung) {
        this._Bezeichnung = _Bezeichnung;
    }

    public Bundle get_Einheiten() {return  _Einheiten;}

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
        dest.writeBundle(this._Einheiten);
    }

    protected Nahrungsmittel(Parcel in) {
        this._Bezeichnung = in.readString();
        this._Einheiten = in.readBundle();
    }

    public static final Creator<Nahrungsmittel> CREATOR = new Creator<Nahrungsmittel>() {
        @Override
        public Nahrungsmittel createFromParcel(Parcel source) {
            return new Nahrungsmittel(source);
        }

        @Override
        public Nahrungsmittel[] newArray(int size) {
            return new Nahrungsmittel[size];
        }
    };
}
