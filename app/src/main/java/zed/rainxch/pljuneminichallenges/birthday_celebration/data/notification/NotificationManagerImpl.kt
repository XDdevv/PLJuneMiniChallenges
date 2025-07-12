package zed.rainxch.pljuneminichallenges.birthday_celebration.data.notification

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import zed.rainxch.pljuneminichallenges.Application
import zed.rainxch.pljuneminichallenges.R
import zed.rainxch.pljuneminichallenges.birthday_celebration.domain.notification.NotificationManager

class NotificationManagerImpl(
    private val context: Context,
) : NotificationManager {

    private val notificationManager = context.getSystemService<android.app.NotificationManager>()

    override fun showNotification(title: String) {
        val notification = NotificationCompat.Builder(context, Application.NOTIFICATION_CHANNEL)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(title)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager?.notify(1, notification)
    }
}