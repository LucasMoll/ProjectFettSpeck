import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class EinstellungenActivity extends PreferenceActivity
        implements Preference.OnPreferenceChangeListener {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences);
        Preference pref = findPreference(getString(R.string.preference_Standardeinheiten_key));
        pref.setOnPreferenceChangeListener(this);

        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        String gespeichertePref = sharedPrefs.getString(pref.getKey(),"");
        onPreferenceChange(pref, gespeichertePref);
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value){

        preference.setSummary(value.toString());

        return true;
    }



}
