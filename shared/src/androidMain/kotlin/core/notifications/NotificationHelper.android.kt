package core.notifications

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import io.github.aakira.napier.Napier
import org.example.kmpsample.LaunchActivity
import org.example.kmpsample.R

actual class NotificationHelper(private val context: Context) {

    actual fun sendNotification(message: NotificationMessage) {
        Napier.d("Message for notification: $message")
        val intent = Intent(context, LaunchActivity::class.java).apply {
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
        val flags = PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_ONE_SHOT
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, flags)

        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder =
            NotificationCompat.Builder(
                context,
                context.getString(R.string.notification_default_channel_id)
            )
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(message.title)
                .setContentText(message.text)
                .setContentIntent(pendingIntent)
                .setSound(defaultSoundUri)
                .setAutoCancel(true)

        val notificationManager: NotificationManager? = context.getSystemService()
        notificationManager?.notify(0, notificationBuilder.build())
    }
}
