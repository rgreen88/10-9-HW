package com.example.rynel.myforeground;

/**
 * Created by rynel on 10/9/2017.
 */

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.provider.SyncStateContract;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

public class ForegroundService extends Service {
    private static final String LOG_TAG = "ForegroundService";
    public static boolean IS_SERVICE_RUNNING = false;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Starting if statement provides activity start for service
        if (intent.getAction().equals(SyncStateContract.Constants.ACTION.STARTFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Start Foreground Intent ");
            showNotification();
            Toast.makeText(this, "Service Started!", Toast.LENGTH_SHORT).show();

            //action for previous button clicked
        } else if (intent.getAction().equals(SyncStateContract.Constants.ACTION.PREV_ACTION)) {
            Log.i(LOG_TAG, "Clicked Previous");

            Toast.makeText(this, "Clicked Previous!", Toast.LENGTH_SHORT)
                    .show();

            //action for play clicked
        } else if (intent.getAction().equals(SyncStateContract.Constants.ACTION.PLAY_ACTION)) {
            Log.i(LOG_TAG, "Clicked Play");

            Toast.makeText(this, "Clicked Play!", Toast.LENGTH_SHORT).show();

            //next button clicked
        } else if (intent.getAction().equals(SyncStateContract.Constants.ACTION.NEXT_ACTION)) {
            Log.i(LOG_TAG, "Clicked Next");

            Toast.makeText(this, "Clicked Next!", Toast.LENGTH_SHORT).show();

            //button stop clicked
        } else if (intent.getAction().equals(
                SyncStateContract.Constants.ACTION.STOPFOREGROUND_ACTION)) {
            Log.i(LOG_TAG, "Received Stop Foreground Intent");
            stopForeground(true);
            stopSelf();
        }
        return START_STICKY;
    }

    //setting intent to display notification window for player
    private void showNotification() {
        Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction(SyncStateContract.Constants.ACTION.MAIN_ACTION);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        //retrieving activity from notiIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                notificationIntent, 0);

        //setting retrieved intent
        Intent previousIntent = new Intent(this, ForegroundService.class);
        previousIntent.setAction(SyncStateContract.Constants.ACTION.PREV_ACTION);
        PendingIntent previousIntent2 = PendingIntent.getService(this, 0,
                previousIntent, 0);

        //intent to start notification activity
        Intent playIntent = new Intent(this, ForegroundService.class);
        playIntent.setAction(SyncStateContract.Constants.ACTION.PLAY_ACTION);
        PendingIntent pplayIntent = PendingIntent.getService(this, 0,
                playIntent, 0);

        //intent to pass queuing in service
        Intent nextIntent = new Intent(this, ForegroundService.class);
        nextIntent.setAction(SyncStateContract.Constants.ACTION.NEXT_ACTION);
        PendingIntent pnextIntent = PendingIntent.getService(this, 0,
                nextIntent, 0);

        //icon on left of notifican bar player
        Bitmap icon = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);


        //building notification bar with player controls
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("Music Player")
                .setTicker("Music Player")
                .setContentText("Wake Up, Get Up, Get Out There!")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .addAction(android.R.drawable.ic_media_previous, "Previous",
                        ppreviousIntent)
                .addAction(android.R.drawable.ic_media_play, "Play",
                        pplayIntent)
                .addAction(android.R.drawable.ic_media_next, "Next",
                        pnextIntent).build();
        startForeground(SyncStateContract.Constants.NOTIFICATION_ID.FOREGROUND_SERVICE,
                notification);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(LOG_TAG, "onDestroy");
        Toast.makeText(this, "Service Terminated!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }
}