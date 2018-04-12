package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by pschoger on 20.03.2018.
 */

public class KTEintrag implements Parcelable {
    private String _Bezeichnung;
   //private Map<Gericht, Map.Entry<String, Double>> _Gerichte;
   //private Map<Lebensmittel, Map.Entry<String, Double>> _Nahrungsmittel;
    private Bundle _Gerichte;
    private Bundle _Nahrungsmittel;
    private Date _Datum;

    public KTEintrag()
    {
        _Nahrungsmittel = new Bundle();
        _Gerichte = new Bundle();

        set_Datum(new Date());
    }

    public String get_Bezeichnung() {
        return _Bezeichnung;
    }

    public void set_Bezeichnung(String _Bezeichnung) {
        this._Bezeichnung = _Bezeichnung;
    }


    public Bundle get_Nahrungsmittel() {return  _Nahrungsmittel;}
    public Bundle get_Gerichte() {return  _Gerichte;}

    @Override
    public String toString() {
        return _Bezeichnung;
    }

    public Date get_Datum() {
        return _Datum;
    }

    public void set_Datum(Date _Datum) {
        this._Datum = _Datum;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this._Bezeichnung);
        dest.writeBundle(this._Gerichte);
        dest.writeBundle(this._Nahrungsmittel);
        dest.writeLong(this._Datum != null ? this._Datum.getTime() : -1);
    }

    protected KTEintrag(Parcel in) {
        this._Bezeichnung = in.readString();
        this._Gerichte = in.readBundle();
        this._Nahrungsmittel = in.readBundle();
        long tmp_Datum = in.readLong();
        this._Datum = tmp_Datum == -1 ? null : new Date(tmp_Datum);
    }

    public static final Creator<KTEintrag> CREATOR = new Creator<KTEintrag>() {
        @Override
        public KTEintrag createFromParcel(Parcel source) {
            return new KTEintrag(source);
        }

        @Override
        public KTEintrag[] newArray(int size) {
            return new KTEintrag[size];
        }
    };
}
