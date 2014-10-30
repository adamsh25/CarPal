package com.BooYa.CarPal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.*;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.LogRecord;

/**
 * Created by adam on 30/10/2014.
 */
public class NotificationService extends Service
{

    private static final String TAG = "class: NotificationService \n";
    private static Service _service;

    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public void onCreate()
    {
        _service = this;
        Toast.makeText(this, "Congrats! MyService Created", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        Log.d(TAG, "onStart");
        (new DoBackgroundTask()).execute();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        Toast.makeText(this, "MyService Stopped", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy");
    }



    public static List<String> GetNotificationsFromServer()
    {
        final List<String> notificationsObjects = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        notificationsObjects.add("Adam " + " Can you drive tomorrow?" + seconds);

        return notificationsObjects;
    }


    private static void StartNotify()
    {
        List<String> notificationsObjects = GetNotificationsFromServer();
        for(int i = 0;i < notificationsObjects.size();i++)
        {
            Object notificationObject = notificationsObjects.get(i);
            Bitmap bit = BitmapFactory.decodeResource(null, R.drawable.adam);
            Intent notificationIntent = new Intent(_service, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(_service, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
            Notification notification = new Notification.Builder(_service.getApplicationContext())
                    .setContentTitle(notificationObject.toString())
                    .setContentIntent(pendingIntent)
                    .setContentText(notificationObject.toString())
                    .setSmallIcon(R.drawable.adam)
                    .setLargeIcon(bit)
                    .build();

            NotificationManager manager = (NotificationManager) _service.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(i, notification);
            try
            {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }
}


    private class DoBackgroundTask extends AsyncTask<String,Integer,List<String>>
    {
        @Override
        protected List<String> doInBackground(String... params)
        {
            while(!this.isCancelled())
            {

                StartNotify();
                try
                {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return null;
        }
    }



}
