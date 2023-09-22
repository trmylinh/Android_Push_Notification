package com.example.startactivityfromnotification

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore.Audio
import androidx.core.app.NotificationManagerCompat

class MyApplication : Application() {
    companion object {
        val CHANNEL_ID = "CHANNEL_1"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is not in the Support Library.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // sound notification
            val uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
//            val sound = Uri.parse("android.resource://"+packageName+"/"+R.raw.sound_noti)
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .build()


            // Create the NotificationChannel.
            //config channel 1
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            mChannel.setSound(uri, audioAttributes)


            // Register the channel with the system. You can't change the importance
            // or other notification behaviors after this.
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)

        }
    }


}