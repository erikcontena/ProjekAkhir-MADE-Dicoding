package com.example.projekakhir;

import android.content.Intent;
import android.os.Bundle;

import android.provider.Settings;
import android.widget.CompoundButton;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreference;

import com.example.projekakhir.Reminder.AppPreference;
import com.example.projekakhir.Reminder.ReminderToday;



public class SettingsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        SwitchPreference switchToday;
        SwitchPreference switchRelease;
        Preference language;
        private ReminderToday reminderToday;
        private AppPreference appPreference;



        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);


            reminderToday  = new ReminderToday();

            switchToday = findPreference("today");
            switchRelease = findPreference("release");
            language = findPreference("language");

            appPreference = new AppPreference(getActivity().getApplicationContext());

            boolean isDailyReminderActivated = appPreference.getAppDailyReminder();
            boolean isReleaseTodayReminderActivated = appPreference.getAppReleaseTodayReminder();


            if (isDailyReminderActivated) {
                switchToday.setChecked(true);
            }

            if (isReleaseTodayReminderActivated){

                switchRelease.setChecked(true);
            }

            switchRelease.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    return false;
                }
            });



        }



    }

}