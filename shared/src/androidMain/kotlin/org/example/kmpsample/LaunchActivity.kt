package org.example.kmpsample

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import core.notifications.NotificationRegister
import di.CommonFactory
import feature.main.MainScreen

@SuppressLint("CustomSplashScreen")
class LaunchActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val notificationRegister = NotificationRegister(
            context = this,
            appCoroutineScope = CommonFactory.appCoroutineScope
        )
        notificationRegister.register()
        setContent {
            Navigator(MainScreen)
        }
    }
}