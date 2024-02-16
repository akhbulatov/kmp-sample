package core.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AppPreferences(private val dataStore: DataStore<Preferences>) {

    fun getLogin(): Flow<String?> {
        return dataStore.data
            .map { it[LOGIN] }
    }

    suspend fun saveLogin(login: String) {
        dataStore.edit { prefs ->
            prefs[LOGIN] = login
        }
    }

    companion object {
        private val LOGIN = stringPreferencesKey(name = "login")
    }
}