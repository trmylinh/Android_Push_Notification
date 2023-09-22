package com.example.startactivityfromnotification

import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import com.example.startactivityfromnotification.databinding.ActivityMainBinding
import java.util.Date

/*
Start activity from notification
with regular activity (with back stack)
 */


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    companion object {
        val TITLE_PUSH_NOTI = "BigText and BigPicture trong Notification"
        val CONTENT_PUSH_NOTI = "Trong Part 4 này, mình xin trình bày cách chúng ta tạo một Expandable Notification với:\n" +
                "- BigTextStyle: Hiển thị nội dung Text dài trên Notification\n" +
                "- BigPictureStyle: Hiển thị Ảnh To trên Notification."
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendNotification.setOnClickListener{
            sendPushNotification()
        }

        binding.btnGotolistproducts.setOnClickListener{
            val intent = Intent(this, ListProductActivity::class.java)
            startActivity(intent)
        }
    }

    private fun sendPushNotification() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

        // Create an Intent for the activity you want to start
        val resultIntent = Intent(this, DetailActivity::class.java)
        // Create the TaskStackBuilder
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(this).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0,
                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        }

        val notification = NotificationCompat.Builder(this@MainActivity, MyApplication.CHANNEL_ID)
            .setContentTitle(TITLE_PUSH_NOTI)
            .setContentText(CONTENT_PUSH_NOTI)
            .setSmallIcon(R.drawable.ic_notification_custom)
            .setLargeIcon(bitmap)
            .setContentIntent(resultPendingIntent)
            .setAutoCancel(true)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun getNotificationId(): Int {
        return Date().time.toInt()
    }

}