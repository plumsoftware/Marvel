package ru.plumsoftware.marvel.service.firebase

import android.annotation.SuppressLint
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.plumsoftware.marvel.R
import ru.plumsoftware.marvel.ui.presentation.activity.presentation.MainActivity

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        if (remoteMessage.data.isNotEmpty()) {

            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("hero_id", remoteMessage.data["hero_id"]?.toInt())

            val pendingIntent = PendingIntent.getActivity(
                this,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val defaultSoundUri =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

            val notificationBuilder = NotificationCompat.Builder(
                this,
                "ru.plumsoftware.marvel.FirebaseMessagingService"
            )
                .setSmallIcon(R.drawable.notification_icon)
                .setContentTitle(remoteMessage.notification?.title)
                .setContentText(if (remoteMessage.notification?.body != null) remoteMessage.notification?.body else "")
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

            val notificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.notify(0, notificationBuilder.build())
        }
    }
}