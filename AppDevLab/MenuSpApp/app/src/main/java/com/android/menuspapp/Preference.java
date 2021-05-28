package com.android.menuspapp;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.preference.PreferenceActivity;



public class Preference extends PreferenceActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.prefs);

    }
}
