package com.josh_davey.university_3rd_year_project;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import static android.content.Context.NOTIFICATION_SERVICE;

public class Firebase_Notification_Cancel_Listener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        clearNotificationCount(context);

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.cancel(0);

    }

    public void clearNotificationCount(Context context)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("motion_detection_app",context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove("notification_counter").apply();
    }
}
