package de.fhdw.mfwi415a.gop.kalorientagebuch.activites.Settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import de.fhdw.mfwi415a.gop.kalorientagebuch.R;

public class EinstellungenActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        Preference pref = findPreference(getString(R.string.preference_Standardeinheiten_key));
        pref.setOnPreferenceChangeListener(this);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String gespeichertePref = sharedPrefs.getString(pref.getKey(),"");
        onPreferenceChange(pref, gespeichertePref);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value){

        preference.setSummary(value.toString());

        return true;
    }



}
