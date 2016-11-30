package com.josh_davey.university_3rd_year_project;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class Firebase_Notification_Service extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Update notification count - how many notifcations have been received without being cleared.
        Integer count = getNotificationCount();
        setNotificationCount(count+1);

        //Send notification.
        sendNotification();
    }

    public Integer getNotificationCount()
    {
        SharedPreferences sharedPreferences = getSharedPreferences("motion_detection_app",MODE_PRIVATE);
        return sharedPreferences.getInt("notification_counter",0);
    }

    public void setNotificationCount(Integer value)
    {
        SharedPreferences sharedPreferences = getSharedPreferences("motion_detection_app",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("notification_counter", value).apply();
    }

    private void sendNotification() {
        Intent viewIntent = new Intent(this, MainActivity.class);
        viewIntent.putExtra("remove_notification",true);
        viewIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent view = PendingIntent.getActivity(this, 0, viewIntent, PendingIntent.FLAG_ONE_SHOT);


        Intent clearIntent = new Intent(this, Firebase_Notification_Cancel_Listener.class);
        PendingIntent clear = PendingIntent.getBroadcast(this.getApplicationContext(), 0, clearIntent, 0);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Motion Detector App")
                .setContentText("Motion has been detected "+ getNotificationCount() + " time(s).")
                .setDefaults(Notification.DEFAULT_ALL)
                .setAutoCancel(true)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .addAction(R.drawable.ic_close_black_24dp, "Dismiss", clear)
                .setDeleteIntent(clear)
                .addAction(R.drawable.ic_check_black_24dp, "View", view);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0, notificationBuilder.build());
    }
}