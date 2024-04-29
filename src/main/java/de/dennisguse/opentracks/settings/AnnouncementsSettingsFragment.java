package de.dennisguse.opentracks.settings;

import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;

import de.dennisguse.opentracks.R;

public class AnnouncementsSettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_announcements);

        findPreference(getString(R.string.settings_ski_announcements_key)).setOnPreferenceClickListener(preference -> {
            ((SettingsActivity) getActivity()).openScreen(getString(R.string.settings_ski_announcements_key));
            return true;
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        ((SettingsActivity) getActivity()).getSupportActionBar().setTitle(R.string.settings_announcements_statistics_title);
    }

    @Override
    public void onResume() {
        super.onResume();

        ListPreference voiceFrequency = findPreference(getString(R.string.voice_announcement_frequency_key));
        voiceFrequency.setEntries(PreferencesUtils.getVoiceAnnouncementFrequencyEntries());

        ListPreference voiceDistance = findPreference(getString(R.string.voice_announcement_distance_key));
        voiceDistance.setEntries(PreferencesUtils.getVoiceAnnouncementDistanceEntries());
    }
}
