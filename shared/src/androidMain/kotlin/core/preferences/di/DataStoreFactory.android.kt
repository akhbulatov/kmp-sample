package core.preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

object AndroidDataStoreFactory {

    fun provideDataStore(context: Context): DataStore<Preferences> {
        return createDataStore(
            producePath = { context.filesDir.resolve(DATASTORE_FILE_NAME).absolutePath }
        )
    }
}