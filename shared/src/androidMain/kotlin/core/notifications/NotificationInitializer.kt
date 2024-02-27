package core.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import io.github.aakira.napier.Napier
import org.example.kmpsample.R

class NotificationInitializer {

    fun init(context: Context) {
        setupChannels(context)
    }

    private fun setupChannels(context: Context) {
        Napier.d("Setup channels...")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val defaultChannel = NotificationChannel(
                context.getString(R.string.notification_default_channel_id),
                context.getString(R.string.notification_default_channel_name),
                NotificationManager.IMPORTANCE_HIGH
            )
            NotificationManagerCompat.from(context)
                .createNotificationChannel(defaultChannel)
        }
    }
}
