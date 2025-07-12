package zed.rainxch.pljuneminichallenges

import android.app.Application
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.core.content.getSystemService

class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager = getSystemService<NotificationManager>()

            val channel = NotificationChannel(
                NOTIFICATION_CHANNEL,
                "June notifications channel",
                NotificationManager.IMPORTANCE_HIGH
            )

            notificationManager?.createNotificationChannel(channel)
        }
    }

    companion object {
        const val NOTIFICATION_CHANNEL = "june_notification_channel"
    }
}