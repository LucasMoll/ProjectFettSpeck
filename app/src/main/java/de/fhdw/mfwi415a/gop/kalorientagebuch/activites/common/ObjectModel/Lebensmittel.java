package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by pschoger on 20.03.2018.
 */

public class Lebensmittel implements Parcelable {
    private String _Bezeichnung;
    private Bundle _Einheiten;

    public Lebensmittel()
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

    protected Lebensmittel(Parcel in) {
        this._Bezeichnung = in.readString();
        this._Einheiten = in.readBundle();
    }

    public static final Creator<Lebensmittel> CREATOR = new Creator<Lebensmittel>() {
        @Override
        public Lebensmittel createFromParcel(Parcel source) {
            return new Lebensmittel(source);
        }

        @Override
        public Lebensmittel[] newArray(int size) {
            return new Lebensmittel[size];
        }
    };
}
