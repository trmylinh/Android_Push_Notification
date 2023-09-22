package com.example.notificationtutorial

import android.Manifest
import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RemoteViews
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificationtutorial.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.Date

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
            sendNotification()
        }

        binding.btnSendNotification2.setOnClickListener{
            sendNotification2()
        }

        binding.btnSendNotificationCustom.setOnClickListener{
            sendCustomNotification()
        }
    }


    // gui notification tu local
    private fun sendNotification() {
        val bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)

        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
            .setContentTitle(TITLE_PUSH_NOTI)
            .setContentText(CONTENT_PUSH_NOTI)
            .setStyle(NotificationCompat.BigTextStyle().bigText(CONTENT_PUSH_NOTI))
            .setSmallIcon(R.drawable.ic_notification_custom)
            .setLargeIcon(bitmap)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun sendNotification2() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_push_noti)

        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
            .setContentTitle("Title push notification 2")
            .setContentText("Message test")
            .setSmallIcon(R.drawable.ic_notification_custom)
            .setLargeIcon(bitmap)
            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap).bigLargeIcon(null))
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)
    }

    private fun sendCustomNotification() {
        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.img_push_noti)
        val sdf = SimpleDateFormat("HH:mm");
        val strDate = sdf.format(Date())

        // Get the layouts to use in the custom notification
        val notificationLayout = RemoteViews(packageName, R.layout.layout_custom_notification)
        notificationLayout.setTextViewText(R.id.noti_title, "Title custom notification")
        notificationLayout.setTextViewText(R.id.noti_info, "Message custom notification")
        notificationLayout.setTextViewText(R.id.noti_time, strDate)


        val notificationLayoutExpanded = RemoteViews(packageName, R.layout.layout_custom_expand_notification)
        notificationLayoutExpanded.setTextViewText(R.id.noti_title_expand, "Title custom notification")
        notificationLayoutExpanded.setTextViewText(R.id.noti_info_expand, "Message custom notification")
        notificationLayoutExpanded.setImageViewBitmap(R.id.img_custom_expand, bitmap)
        //notificationLayoutExpanded.setImageViewResource(R.id.img_custom_expand, R.drawable.img_push_noti)


        val notification = NotificationCompat.Builder(this, MyApplication.CHANNEL_ID_2)
            .setSmallIcon(R.drawable.ic_notification_custom)
            .setCustomContentView(notificationLayout)
            .setCustomBigContentView(notificationLayoutExpanded)
            .build()

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(getNotificationId(), notification)


    }

    private fun getNotificationId(): Int {
        return Date().time.toInt()
    }

}