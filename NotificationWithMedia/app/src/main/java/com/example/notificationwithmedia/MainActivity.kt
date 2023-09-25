package com.example.notificationwithmedia

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.session.MediaSession
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.app.NotificationCompat
import com.example.notificationwithmedia.databinding.ActivityMainBinding
import java.util.Date

private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnSendNotification.setOnClickListener{
            sendNotificationMedia()
        }
    }

    private fun sendNotificationMedia() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_push_noti)
        val mediaSession = MediaSessionCompat(this, "mediaSession")
        

        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_small_music)
            .setSubText("MyLinh")
            .setContentTitle("Title song")
            .setContentText("Singer")
            .setLargeIcon(bitmap)
        // Add media control buttons that invoke intents in your media service
            .addAction(R.drawable.ic_skip_previous, "Previous", null) //#0
            .addAction(R.drawable.ic_pause, "Pause", null)            //#1
            .addAction(R.drawable.ic_skip_next, "Next", null)          //#2
            // Apply the media style template.
            .setStyle(androidx.media.app.NotificationCompat.MediaStyle()
                .setShowActionsInCompactView(1 /* #1: pause button \*/)
//                .setMediaSession(mediaSession.sessionToken)
            )
            .build()


        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun getNotificationId(): Int {
        return Date().time.toInt()
    }
}