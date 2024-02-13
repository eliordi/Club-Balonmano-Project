package es.jcc.project.Settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import es.jcc.project.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}
// if dark chat fondo del fragment negre, rec_mensaje cardview yellow i letra negra