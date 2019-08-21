package com.gmail.hmazud.submissionmovie2.settings;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.gmail.hmazud.submissionmovie2.R;
import com.gmail.hmazud.submissionmovie2.service.AlarmApps;
import com.gmail.hmazud.submissionmovie2.service.SchedulerTask;

import butterknife.BindString;
import butterknife.ButterKnife;

public class SettingsService extends AppCompatActivity {

    private AlarmApps alarmReceiver = new AlarmApps();
    private SchedulerTask schedulerTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, new MyPreferenceFragment())
                .commit();

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @SuppressLint("ValidFragment")
    public class MyPreferenceFragment extends PreferenceFragment
            implements Preference.OnPreferenceChangeListener,
            Preference.OnPreferenceClickListener {

        @BindString(R.string.dailyRemind)
        String reminder_daily;

        @BindString(R.string.upcomingRemind)
        String reminder_upcoming;

        @BindString(R.string.changeLang)
        String setting_locale;

        @Override
        public void onCreate(final Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.pref_alarm);

            ButterKnife.bind(this, getActivity());

            findPreference(reminder_daily).setOnPreferenceChangeListener(this);
            findPreference(reminder_upcoming).setOnPreferenceChangeListener(this);
            findPreference(setting_locale).setOnPreferenceClickListener(this);

            schedulerTask = new SchedulerTask(getActivity());
        }

        @Override
        public boolean onPreferenceChange(Preference preference, Object o) {
            String key = preference.getKey();
            boolean isOn = (boolean) o;

            if (key.equals(reminder_daily)) {
                if (isOn) {
                    alarmReceiver.repeatingAlarm(getActivity());
                } else {
                    alarmReceiver.cancelAlarm(getActivity());
                }

                Toast.makeText(SettingsService.this, "Daily Reminder" + " " + (isOn ? "Activated" : "UnActivated"), Toast.LENGTH_SHORT).show();
                return true;
            }

            if (key.equals(reminder_upcoming)) {
                if (isOn) {
                    schedulerTask.createPeriodicTask();
                } else schedulerTask.cancelPeriodicTask();

                Toast.makeText(SettingsService.this, "Upcoming Reminder" + " " + (isOn ? "Activated" : "UnActivated"), Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;
        }

        @Override
        public boolean onPreferenceClick(Preference preference) {
            String key = preference.getKey();

            if (key.equals(setting_locale)) {
                Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
                startActivity(intent);
                return true;
            }

            return false;
        }
    }
}