package core.notifications

import android.content.Context
import android.content.Intent
import io.github.aakira.napier.Napier
import org.example.kmpsample.R

class NotificationParser(private val context: Context) {

    fun parseNotification(intent: Intent): NotificationMessage? {
        val title = intent.getStringExtra("title")
        val message = intent.getStringExtra("message")
        if (message.isNullOrBlank()) {
            Napier.d("No message! Return...")
            return null
        }

        return NotificationMessage(
            title = title ?: context.getString(R.string.app_name),
            text = message
        )
    }
}
