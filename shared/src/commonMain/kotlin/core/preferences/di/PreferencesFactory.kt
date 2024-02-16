package core.preferences.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import core.preferences.AppPreferences

class PreferencesFactory(private val dataStore: DataStore<Preferences>) {

    val appPreferences: AppPreferences by lazy {
        AppPreferences(dataStore = dataStore)
    }
}