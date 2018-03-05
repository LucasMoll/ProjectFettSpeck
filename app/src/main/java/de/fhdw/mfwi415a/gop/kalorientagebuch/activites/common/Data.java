package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.common;

/**
 * Created by joel on 05.03.18.
 */

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import de.fhdw.mfwi415a.gop.kalorientagebuch.activites.constants.Constants;

public class Data {

    // activity data
    private Activity mActivity;
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

        if ( savedInstanceState == null ) {
            readIntentParametersOrSetDefaultValues(mActivity.getIntent());
        }
        else {  // restore from last state
            restoreDataFromBundle(savedInstanceState);
        }
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
        // b.putLongArray(KEYLONGARRAY, mExamplelongArray);
    }

    public void restoreDataFromBundle(Bundle b) {
        mExampleIntValue = b.getInt(Constants.KEYEXAMPLEINTVALUE);
        mExampleLongValue = b.getLong(Constants.KEYEXAMPLELONGVALUE);
        // mExamplelongArray = b.getLongArray(KEYLONGARRAY);
    }

    // setter

    public void setExampleIntValue(int value) {
        mExampleIntValue = value;
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

    // other

    // ....

}

