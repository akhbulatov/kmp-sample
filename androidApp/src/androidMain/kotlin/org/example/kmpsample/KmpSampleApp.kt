package org.example.kmpsample

import android.app.Application
import core.database.di.DatabaseSqlDriverFactory
import di.CommonFactory
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

class KmpSampleApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        initLogger()
    }

    private fun initLogger() {
        Napier.base(DebugAntilog())
    }

    companion object {
        private lateinit var instance: KmpSampleApp

        val commonFactory: CommonFactory by lazy {
            CommonFactory(
                sqlDriverFactory = DatabaseSqlDriverFactory(context = instance.applicationContext)
            )
        }
    }
}