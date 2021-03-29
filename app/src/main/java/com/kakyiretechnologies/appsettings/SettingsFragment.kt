package com.kakyiretechnologies.appsettings

import android.os.Bundle
import android.widget.Toast
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat


private const val TAG = "SettingsFragment"

class SettingsFragment : PreferenceFragmentCompat() {

    //preference keys
    private val themeKey = "theme_pref"
    private val notificationKey = "notification"
    private val contactKey = "contact"
    private val privacyKey = "privacy"

    private var themePreference: ListPreference? = null
    private var notifications: SwitchPreferenceCompat? = null
    private var contactUs: Preference? = null
    private var privacyPolicy: Preference? = null
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        setPreferencesFromResource(R.xml.preferences, rootKey)



        initializePrefs()
        preferenceListeners()

    }

    private fun initializePrefs() {
        themePreference = findPreference(themeKey)
        notifications = findPreference(notificationKey)
        contactUs = findPreference(contactKey)
        privacyPolicy = findPreference(privacyKey)


    }

    //listen to events or changes by the user
    private fun preferenceListeners() {


        themePreference?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                when (newValue) {
                    "system" -> showMessage("Theme is set to System Default")
                    else -> showMessage("Theme is set tor $newValue")
                }
                true
            }


        notifications?.onPreferenceChangeListener =
            Preference.OnPreferenceChangeListener { preference, newValue ->
                when (newValue.toString()) {
                    "true" -> showMessage("Notification is On")
                    else -> showMessage("Notification is off")
                }
                true
            }


        contactUs?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showMessage("Contact Us")
            true
        }

        privacyPolicy?.onPreferenceClickListener = Preference.OnPreferenceClickListener {
            showMessage("Privacy Policy")
            true
        }

    }


    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}