package core.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import di.CommonFactoryProvider
import io.github.aakira.napier.Napier

class NotificationReceiver : BroadcastReceiver() {

    private val notificationHelper: NotificationHelper by lazy {
        CommonFactoryProvider.commonFactory.notificationHelper
    }

    override fun onReceive(context: Context, intent: Intent) {
        Napier.d("Notification is received: $intent")
        val notificationParser = NotificationParser(context = context)
        val notificationMessage = notificationParser.parseNotification(intent)
        notificationMessage?.let { notificationHelper.sendNotification(it) }
    }
}
