package com.BooYa.CarPal;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by adam on 30/10/2014.
 */
public class NotificationService extends Service {

    private static final String TAG = "class: NotificationService \n";
    private static final String USER_NAME = "אדם";
    private static final String OTHER_USER_NAME = "רוני";
    private static final String PRESENT = " אחוזת החוף מעניקה לך 50 אחוז הנחה על חנייה. לחץ לקבלת הטבה. ";
    private static final String PRESENT_URL = "http://www.ahuzot.co.il/Parking/";
    private static Service _service;
    private static boolean isCancel = false;

    public static List<CarPalNotification> GetNotificationsFromServer() {
        final List<CarPalNotification> notificationsObjects = new ArrayList<CarPalNotification>();
        Calendar c = Calendar.getInstance();
        int seconds = c.get(Calendar.SECOND);
        notificationsObjects.add(new CarPalNotification(NotificationTypeEnum.ASK_TO_APPROVE_TOMORROW_CARPAL.ordinal(), USER_NAME + NotificationTypeEnum.ASK_TO_APPROVE_TOMORROW_CARPAL.toString()));
        notificationsObjects.add(new CarPalNotification(NotificationTypeEnum.USER_CANCEL_CARPAL.ordinal(), OTHER_USER_NAME + NotificationTypeEnum.USER_CANCEL_CARPAL));
        notificationsObjects.add(new CarPalNotification(NotificationTypeEnum.USER_GOT_PRESENT.ordinal(), NotificationTypeEnum.USER_GOT_PRESENT.toString() + PRESENT, PRESENT_URL));
        return notificationsObjects;
    }

    private static void StartNotify() {
        List<CarPalNotification> notificationsObjects = GetNotificationsFromServer();
        for (int i = 0; i < notificationsObjects.size(); i++) {
            CarPalNotification notificationDataObject = (CarPalNotification) notificationsObjects.get(i);
            NotificationTypeEnum typeEnum = NotificationTypeEnum.values()[notificationDataObject.get_mTypeId()];
            Intent notificationIntent = new Intent(_service, ButtonListener.class);
            notificationIntent.putExtra("URL", PRESENT_URL);

            notificationIntent.setAction(notificationDataObject.get_mTypeId() + "");
            PendingIntent pendingIntent = PendingIntent.getBroadcast(_service, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            Notification.Builder notificationBuilder = new Notification.Builder(_service.getApplicationContext());


            Intent yesIntent = new Intent(_service, ButtonListener.class);
            yesIntent.setAction(notificationDataObject.get_mTypeId() + "_yes");
            PendingIntent piYes = PendingIntent.getBroadcast(_service, 0, yesIntent, 0);

            Intent noIntent = new Intent(_service, ButtonListener.class);
            noIntent.setAction(notificationDataObject.get_mTypeId() + "_no");
            PendingIntent piNo = PendingIntent.getBroadcast(_service, 0, noIntent, 0);


            Notification notification = NotificationHelper
                    .CreateNotification(typeEnum, notificationBuilder, notificationDataObject, pendingIntent, piYes, piNo);


            Calendar c = Calendar.getInstance();
            int seconds = c.get(Calendar.SECOND);
            NotificationManager manager = (NotificationManager) _service.getSystemService(Context.NOTIFICATION_SERVICE);
            manager.notify(1, notification);
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        _service = this;
        Log.d(TAG, "onCreate");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStart");
        (new DoBackgroundTask()).execute();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
    }

    private class DoBackgroundTask extends AsyncTask<String, Integer, List<String>> {
        @Override
        protected List<String> doInBackground(String... params) {
            while (!(isCancel)) {

                StartNotify();

            }

            return null;
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }
    }


}
