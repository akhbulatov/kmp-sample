package di

import core.database.di.DatabaseSqlDriverFactory
import core.location.LocationClient
import core.preferences.di.IosDataStoreFactory

actual object CommonFactoryProvider {

    private lateinit var commonFactorySingleton: CommonFactory

    actual val commonFactory: CommonFactory
        get() =
            if (::commonFactorySingleton.isInitialized) {
                commonFactorySingleton
            } else {
                CommonFactory(
                    sqlDriverFactory = DatabaseSqlDriverFactory(),
                    dataStore = IosDataStoreFactory.provideDataStore(),
                    locationClient = LocationClient()
                )
            }.also { commonFactorySingleton = it }
}