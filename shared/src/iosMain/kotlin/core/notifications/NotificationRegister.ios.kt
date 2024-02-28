package core.notifications

import cocoapods.Pushy.Pushy
import io.github.aakira.napier.Napier
import platform.UIKit.UIApplication

actual class NotificationRegister {

    private val pushy = Pushy(UIApplication.sharedApplication)

    actual fun register() {
        Napier.d(tag = "NotificationRegister", message = "register called.")
        pushy.register { error, deviceToken ->
            Napier.d(
                tag = "NotificationRegister",
                message = "register:\n\terror=$error\n\ttoken=$deviceToken"
            )
        }
        setHandler()
    }

    private fun setHandler() {
        // Enable in-app notification banners (iOS 10+)
        pushy.toggleInAppBanner(value = true)

        // Handle incoming notifications
        pushy.setNotificationHandler { map, _ ->
            // Print notification payload
            Napier.d(
                tag = "NotificationRegister",
                message = "setNotificationHandler:\n\tmap=$map"
            )
        }
    }
}