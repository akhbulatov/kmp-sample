package org.example.kmpsample

import android.app.Application
import core.database.di.DatabaseSqlDriverFactory
import core.location.LocationClient
import core.notifications.NotificationHelper
import core.notifications.NotificationInitializer
import core.preferences.di.AndroidDataStoreFactory
import di.CommonFactory
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class KmpSampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLogger()
        initNotifications()
    }

    private fun initLogger() {
        Napier.base(DebugAntilog())
    }

    private fun initNotifications() {
        val initializer = NotificationInitializer()
        initializer.init(context = instance.applicationContext)
    }

    companion object {
        private lateinit var instance: KmpSampleApp

        val commonFactory: CommonFactory by lazy {
            CommonFactory(
                sqlDriverFactory = DatabaseSqlDriverFactory(context = instance.applicationContext),
                dataStore = AndroidDataStoreFactory.provideDataStore(context = instance.applicationContext),
                locationClient = LocationClient(context = instance.applicationContext),
                notificationHelper = NotificationHelper(context = instance.applicationContext)
            )
        }
    }
}