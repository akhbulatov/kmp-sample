package core.preferences.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

object AndroidDataStoreFactory {

    fun provideDataStore(context: Context): DataStore<Preferences> {
        return DataStoreFactory.createDataStore(
            producePath = { context.filesDir.resolve(DataStoreFactory.DATASTORE_FILE_NAME).absolutePath }
        )
    }
}