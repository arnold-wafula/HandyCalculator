package com.lemonboy.handycalculator.ui.settings

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.lemonboy.handycalculator.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.main_settings, rootKey)

        val supportDevPref = findPreference<Preference>(getString(R.string.key_donate_coffee))
        supportDevPref?.setOnPreferenceClickListener {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            val action = SettingsFragmentDirections.actionNavSettingsToSupportSettings()
            navController.navigate(action)

            true
        }

        /**
        val accSettingsPref = findPreference<Preference>(getString(R.string.key_account_settings))

        accSettingsPref?.setOnPreferenceClickListener {
            //Toast.makeText(context, "Account Settings", Toast.LENGTH_SHORT).show()

            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController

            val action = SettingsFragmentDirections.actionNavSettingsToAccountSettings()
            navController.navigate(action)

            true
        }

        // Read preference values in fragment
        val sharedPreferences = context?.let { PreferenceManager.getDefaultSharedPreferences(it) }
        val autoReplyTime = sharedPreferences?.getString(getString(R.string.key_auto_reply_time), "")
        Log.i("SettingsFragment", "Auto Reply Time: $autoReplyTime")

        val autoDownload = sharedPreferences?.getBoolean(getString(R.string.key_auto_download), true)
        Log.i("SettingsFragment", "Auto Download: $autoDownload")

        val statusPref = findPreference<EditTextPreference>(getString(R.string.key_status))
        statusPref?.setOnPreferenceChangeListener { preference, newValue ->
            //Log.i("SettingsFragment", "New Status: $newValue")

            val newStatus = newValue as String
            if (newStatus.contains("bad")) {
                view?.let {
                    Snackbar.make(it, "Inappropriate use according to community guidelines", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show()
                }

                false
            }

            else {
                true
            }
        }*/
    }
}