package com.example.untitled1;

import android.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.SparseArray;

import java.util.concurrent.atomic.AtomicInteger;

import static android.app.PendingIntent.FLAG_CANCEL_CURRENT;

/**
 *
 */
public class NotifyUtils {

    // --- static singletone
    private static NotifyUtils instance;

    // --- instance variable
    private Context context;
    private NotificationManager manager;
    private SparseArray<Notification> messages = new SparseArray<Notification>();
    private AtomicInteger incrementor = new AtomicInteger(1);

    private NotifyUtils(Context context) {
        this.context = context;
        this.manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    }

    public static int buildNotify(Context context) {
        ensureInstance(context);
        int id = instance.incrementor.getAndIncrement();
        Intent notificationIntent = new Intent(context, SecondActivity.class);
        notificationIntent.putExtra("num", id);
        NotificationCompat.Builder nb = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_lock_idle_alarm)
                .setAutoCancel(true)
                .setTicker("Splash message")
                .setContentText("Main Text")
                .setContentIntent(PendingIntent.getActivity(context, 0, notificationIntent, FLAG_CANCEL_CURRENT))
                .setWhen(System.currentTimeMillis())
                .setContentTitle("New message")
                .setDefaults(Notification.DEFAULT_ALL);
        Notification notification = nb.build();
        instance.manager.notify(id, notification);
        instance.messages.put(id, notification);
        return id;
    }

    private static void ensureInstance(Context context) {
        if (instance == null) {
            instance = new NotifyUtils(context);
        }
    }

}
