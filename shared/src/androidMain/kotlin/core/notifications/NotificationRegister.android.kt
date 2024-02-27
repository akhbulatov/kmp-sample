package core.notifications

import android.content.Context
import io.github.aakira.napier.Napier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import me.pushy.sdk.Pushy

actual class NotificationRegister(
    private val context: Context,
    private val appCoroutineScope: CoroutineScope
) {

    actual fun register() {
        Napier.d("Register notifications....")
        Pushy.listen(context)
        if (!Pushy.isRegistered(context)) {
            registerAsync()
        }
    }

    private fun registerAsync() {
        appCoroutineScope.launch {
            try {
                val deviceToken = Pushy.register(context)
                Napier.d("Device token: $deviceToken")
            } catch (e: Exception) {
                Napier.e("Failed for register: $e")
            }
        }
    }
}
