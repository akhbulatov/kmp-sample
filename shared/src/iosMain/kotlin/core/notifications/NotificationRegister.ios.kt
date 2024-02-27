package core.notifications

import cocoapods.Pushy.Pushy
import io.github.aakira.napier.Napier
import platform.UIKit.UIApplication

actual class NotificationRegister {

    actual fun register() {
        val pushy = Pushy(UIApplication.sharedApplication())
        if (!pushy.isRegistered()) {
            pushy.register { error, deviceToken ->
                Napier.d(
                    tag = "NotificationRegister",
                    message = "register callback:\n\terror=$error\n\ttoken=$deviceToken"
                )
            }
        }
    }
}