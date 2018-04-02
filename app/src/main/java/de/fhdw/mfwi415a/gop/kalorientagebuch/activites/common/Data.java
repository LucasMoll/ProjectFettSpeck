package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common;

/**
 * Created by Joel Hohmann on 05.03.18.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Gericht;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.KTEintrag;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common.ObjectModel.Nahrungsmittel;
import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.constants.Constants;

public class Data {

    // activity data
    private Activity mActivity;
    private ArrayList<KTEintrag> mKTEintragArrayList;
    private ArrayList<Gericht> mGerichtArrayList;
    private ArrayList<Nahrungsmittel> mNahrungsmittelArrayList;
    private ArrayList<String> mEinheitArrayList;
    // currents
    private KTEintrag mCurrentKTEintrag;
    private Gericht mCurrentGericht;
    private Nahrungsmittel mCurrentNahrungsmittel;

    private String mCurrentEinheit;

    private int mExampleIntValue;
    private long mExampleLongValue;
    // ...

    // default values
    private final int DEFAULTEXAMPLEINTVALUE = 0;
    private final long DEFAULTEXAMPLELONGVALUE = 0L;

    // other activity specific constants
    // ...

    public Data (Bundle savedInstanceState, Activity activity) {
        mActivity = activity;

        //initialize ArrayLists
        initArrayLists();

        if (savedInstanceState == null) {
            readIntentParametersOrSetDefaultValues(mActivity.getIntent());
        } else {  // restore from last state
            restoreDataFromBundle(savedInstanceState);
        }
    }

    private void initArrayLists()
    {
        mKTEintragArrayList = new ArrayList<>();
        mGerichtArrayList = new ArrayList<>();
        mNahrungsmittelArrayList = new ArrayList<>();
        mEinheitArrayList = new ArrayList<>();
    }

    public void readIntentParametersOrSetDefaultValues(Intent intent) {
        Bundle b = intent.getBundleExtra(Constants.KEYDATABUNDLE);
        if ( b == null ) {
            setDefaultValues();
        }
        else {
            restoreDataFromBundle(b);
        }
    }

    private void setDefaultValues() {
        mExampleIntValue = DEFAULTEXAMPLEINTVALUE;
        mExampleLongValue = DEFAULTEXAMPLELONGVALUE;
    }

    public void saveDataInBundle(Bundle b) {
        b.putInt(Constants.KEYEXAMPLEINTVALUE, mExampleIntValue);
        b.putLong(Constants.KEYEXAMPLELONGVALUE, mExampleLongValue);
        b.putStringArrayList(Constants.KEYEINHEITEN, mEinheitArrayList);
        b.putParcelableArrayList(Constants.KEYNAHRUNGSMITTEL,mNahrungsmittelArrayList);
        b.putParcelableArrayList(Constants.KEYGERICHT,mGerichtArrayList);
        b.putParcelableArrayList(Constants.KEYKTEINTRAG,mKTEintragArrayList);
        b.putString(Constants.KEYCURRENTEINHEIT,mCurrentEinheit);
        b.putParcelable(Constants.KEYCURRENTNAHRUNGSMITTEL, mCurrentNahrungsmittel);
        b.putParcelable(Constants.KEYCURRENTGERICHT, mCurrentGericht);
        b.putParcelable(Constants.KEYCURRENTKTEINTRAG, mCurrentKTEintrag);
        // b.putLongArray(KEYLONGARRAY, mExamplelongArray);
    }

    public void restoreDataFromBundle(Bundle b) {
        mExampleIntValue = b.getInt(Constants.KEYEXAMPLEINTVALUE);
        mExampleLongValue = b.getLong(Constants.KEYEXAMPLELONGVALUE);
        mEinheitArrayList = b.getStringArrayList(Constants.KEYEINHEITEN);
        mNahrungsmittelArrayList = b.getParcelableArrayList(Constants.KEYNAHRUNGSMITTEL);
        mGerichtArrayList = b.getParcelableArrayList(Constants.KEYGERICHT);
        mKTEintragArrayList = b.getParcelableArrayList(Constants.KEYKTEINTRAG);
        mCurrentEinheit = b.getString(Constants.KEYCURRENTEINHEIT);
        mCurrentNahrungsmittel = b.getParcelable(Constants.KEYCURRENTNAHRUNGSMITTEL);
        mCurrentGericht = b.getParcelable(Constants.KEYCURRENTGERICHT);
        mCurrentKTEintrag = b.getParcelable(Constants.KEYCURRENTKTEINTRAG);
        // mExamplelongArray = b.getLongArray(KEYLONGARRAY);
    }

    // setter

    public void setExampleIntValue(int value) {
        mExampleIntValue = value;
    }

    public void setmKTEintragArrayList(ArrayList<KTEintrag> mKTEintragArrayList) {
        this.mKTEintragArrayList = mKTEintragArrayList;
    }

    public void setmGerichtArrayList(ArrayList<Gericht> mGerichtArrayList) {
        this.mGerichtArrayList = mGerichtArrayList;
    }

    public void setmNahrungsmittelArrayList(ArrayList<Nahrungsmittel> mNahrungsmittelArrayList) {
        this.mNahrungsmittelArrayList = mNahrungsmittelArrayList;
    }

    public void setmEinheitArrayList(ArrayList<String> mEinheitArrayList) {
        this.mEinheitArrayList = mEinheitArrayList;
    }

    public void setmCurrentKTEintrag(KTEintrag mCurrentKTEintrag) {
        this.mCurrentKTEintrag = mCurrentKTEintrag;
    }

    public void setmCurrentGericht(Gericht mCurrentGericht) {
        this.mCurrentGericht = mCurrentGericht;
    }

    public void setmCurrentNahrungsmittel(Nahrungsmittel mCurrentNahrungsmittel) {
        this.mCurrentNahrungsmittel = mCurrentNahrungsmittel;
    }

    public void setmCurrentEinheit(String mCurrentEinheit) {
        this.mCurrentEinheit = mCurrentEinheit;
    }

    public void setmExampleIntValue(int mExampleIntValue) {
        this.mExampleIntValue = mExampleIntValue;
    }

    public void setmExampleLongValue(long mExampleLongValue) {
        this.mExampleLongValue = mExampleLongValue;
    }

    // getter

    public Activity getActivity () {
        return mActivity;
    }

    public int getExampleIntValue() {
        return mExampleIntValue;
    }

    public long getExampleLongValue() {
        return mExampleLongValue;
    }

    public ArrayList<KTEintrag> getmKTEintragArrayList() {
        return mKTEintragArrayList;
    }

    public ArrayList<Gericht> getmGerichtArrayList() {
        return mGerichtArrayList;
    }

    public ArrayList<Nahrungsmittel> getmNahrungsmittelArrayList() {
        return mNahrungsmittelArrayList;
    }

    public ArrayList<String> getmEinheitArrayList() {
        return mEinheitArrayList;
    }

    public KTEintrag getmCurrentKTEintrag() {
        return mCurrentKTEintrag;
    }

    public Gericht getmCurrentGericht() {
        return mCurrentGericht;
    }

    public Nahrungsmittel getmCurrentNahrungsmittel() {
        return mCurrentNahrungsmittel;
    }

    public String getmCurrentEinheit() {
        return mCurrentEinheit;
    }

    public int getmExampleIntValue() {
        return mExampleIntValue;
    }

    public long getmExampleLongValue() {
        return mExampleLongValue;
    }
// other

    // ....

}

