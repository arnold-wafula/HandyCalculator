package com.lemonboy.handycalculator.ui.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class AccountSettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        TODO("Not yet implemented")
    }

    /**
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

    // Define all preference objects with attributes
    val publicInfoPref = context?.let { MultiSelectListPreference(it) }
    publicInfoPref?.entries = resources.getStringArray(R.array.entries_public_info)
    publicInfoPref?.entryValues = resources.getStringArray(R.array.values_public_info)
    publicInfoPref?.key = resources.getString(R.string.key_public_info)
    publicInfoPref?.title = resources.getString(R.string.title_public_info)
    publicInfoPref?.isIconSpaceReserved = false

    val logOutPref = context?.let { Preference(it) }
    logOutPref?.key = resources.getString(R.string.key_log_out)
    logOutPref?.title = resources.getString(R.string.title_log_out)
    logOutPref?.isIconSpaceReserved = false

    val deleteAccPref = context?.let { Preference(it) }
    deleteAccPref?.key = resources.getString(R.string.key_delete_account)
    deleteAccPref?.title = resources.getString(R.string.title_delete_account)
    deleteAccPref?.icon = ResourcesCompat.getDrawable(resources, android.R.drawable.ic_menu_delete, null)
    deleteAccPref?.summary = resources.getString(R.string.summary_delete_account)

    val privacyCategory = context?.let { PreferenceCategory(it) }
    privacyCategory?.title = resources.getString(R.string.title_privacy)
    privacyCategory?.isIconSpaceReserved = false

    val logOutCategory = context?.let { PreferenceCategory(it) }
    logOutCategory?.title = resources.getString(R.string.title_log_out)
    logOutCategory?.isIconSpaceReserved = false

    val prefScreen = context?.let { preferenceManager.createPreferenceScreen(it) }

    // Add all pref objects in hierarchy
    if (privacyCategory != null) {
    prefScreen?.addPreference(privacyCategory)
    }

    if (logOutCategory != null) {
    prefScreen?.addPreference(logOutCategory)
    }

    if (publicInfoPref != null) {
    privacyCategory?.addPreference(publicInfoPref)
    }

    if (logOutPref != null) {
    logOutCategory?.addPreference(logOutPref)
    }

    if (deleteAccPref != null) {
    logOutCategory?.addPreference(deleteAccPref)
    }

    // Set the pref screen
    preferenceScreen = prefScreen
    }*/
}