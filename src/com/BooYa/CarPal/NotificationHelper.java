package com.BooYa.CarPal;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by adam on 30/10/2014.
 */
public class NotificationHelper
{
    public static Notification CreateNotification(NotificationTypeEnum notificationType,
                                                  Notification.Builder notification,
                                                  CarPalNotification carPalNotification,
                                                  PendingIntent pendingIntent,
                                                  PendingIntent piYes,
                                                  PendingIntent piNo
                                                  )
    {
        Bitmap bit = BitmapFactory.decodeResource(null, R.drawable.adam);
        switch (notificationType)
        {
            case USER_CANCEL_CARPAL:


                return notification
                        .setContentTitle(carPalNotification.get_mTitle())
                        .setContentIntent(pendingIntent)
                        .setContentText(carPalNotification.get_mText())
                        .setSmallIcon(R.drawable.adam)
                        .setLargeIcon(bit)
                        .addAction (R.drawable.beeri,
                                "לא", piYes)
                        .addAction (R.drawable.rony,
                                "כן", piNo)

                        .build();

            case ASK_TO_APPROVE_TOMORROW_CARPAL:
                return notification
                        .setContentTitle(carPalNotification.get_mTitle())
                        .setContentIntent(pendingIntent)
                        .setContentText(carPalNotification.get_mText())
                        .setSmallIcon(R.drawable.adam)
                        .setLargeIcon(bit)
                        .build();

            default:
                return notification
                        .setContentTitle(carPalNotification.get_mTitle())
                        .setContentIntent(pendingIntent)
                        .setContentText(carPalNotification.get_mText())
                        .setSmallIcon(R.drawable.adam)
                        .setLargeIcon(bit)
                        .build();

        }




    }
}
