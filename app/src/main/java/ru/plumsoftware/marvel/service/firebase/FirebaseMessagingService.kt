package ru.plumsoftware.marvel.service.firebase

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import ru.plumsoftware.marvel.ui.presentation.activity.presentation.MainActivity

@SuppressLint("MissingFirebaseInstanceTokenRefresh")
class FirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "ru.plumsoftware.marvel.FirebaseMessagingService"

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: ${remoteMessage.from}")

        if (remoteMessage.data.isNotEmpty()) {
            val values: MutableCollection<String> = remoteMessage.data.values
            val data: Map<String, String> = remoteMessage.data


            for ((key, value) in data) {
                Log.d(TAG, "Message data payload: $key, $value")

                if (key == "hero_page") {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    intent.putExtra("hero_id", value)
                    startActivity(intent)
                }
            }

            Log.d(TAG, "Message data payload: ${remoteMessage.data}")
            Log.d(TAG, "Message values payload: $values")

        }
    }

    override fun handleIntent(intent: Intent?) {
        super.handleIntent(intent)

        if (intent?.action == "hero_page") {
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.putExtra("hero_id", intent.getIntExtra("hero_id", 1009268))
            startActivity(mainIntent)
        }
    }
}