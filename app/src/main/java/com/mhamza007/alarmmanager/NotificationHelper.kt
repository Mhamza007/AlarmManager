package com.mhamza007.alarmmanager

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.ContextWrapper
import android.os.Build
import androidx.core.app.NotificationCompat

class NotificationHelper(base: Context) : ContextWrapper(base) {

    companion object {
        const val Channel1ID = "channel1ID"
        const val Channel1Name = "Channel 1"

        const val Channel2ID = "channel2ID"
        const val Channel2Name = "Channel 2"
    }

    private var manager : NotificationManager? = null

    init {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannel()
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createChannel() {
        val channel1 =
            NotificationChannel(Channel1ID, Channel1Name, NotificationManager.IMPORTANCE_DEFAULT)
        channel1.enableLights(true)
        channel1.enableVibration(true)
        channel1.lightColor = R.color.colorPrimary
        channel1.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        getManager()?.createNotificationChannel(channel1)

        val channel2 =
            NotificationChannel(Channel2ID, Channel2Name, NotificationManager.IMPORTANCE_HIGH)
        channel2.enableLights(true)
        channel2.enableVibration(true)
        channel2.lightColor = R.color.colorAccent
        channel2.lockscreenVisibility = Notification.VISIBILITY_PRIVATE
        getManager()?.createNotificationChannel(channel2)
    }

    fun getManager() : NotificationManager? {
        if (manager == null) {
            manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }

        return manager
    }

    fun getChannelNotification() : NotificationCompat.Builder {
        return NotificationCompat.Builder(applicationContext, Channel2ID)
            .setContentTitle("Alarm Manager")
            .setContentText("Ringing Alarm")
            .setSmallIcon(R.drawable.ic_one)
    }

//    fun getChannel1Notification(title : String, message : String) : NotificationCompat.Builder {
//        return NotificationCompat.Builder(applicationContext, Channel1ID)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setSmallIcon(R.drawable.ic_one)
//    }
//
//    fun getChannel2Notification(title : String, message : String) : NotificationCompat.Builder {
//        return NotificationCompat.Builder(applicationContext, Channel2ID)
//            .setContentTitle(title)
//            .setContentText(message)
//            .setSmallIcon(R.drawable.ic_two)
//    }
}