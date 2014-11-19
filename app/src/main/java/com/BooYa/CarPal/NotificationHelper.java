package com.BooYa.CarPal;

import android.app.Notification;
import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Created by adam on 30/10/2014.
 */
public class NotificationHelper {
    public static Notification CreateNotification(NotificationTypeEnum notificationType,
                                                  Notification.Builder notification,
                                                  CarPalNotification carPalNotification,
                                                  PendingIntent pendingIntent,
                                                  PendingIntent piYes,
                                                  PendingIntent piNo
    ) {
        Bitmap bit = BitmapFactory.decodeResource(null, R.drawable.adam);
        switch (notificationType) {
            case USER_CANCEL_CARPAL:
                notification.addAction(R.drawable.yesbutton,
                        "", piYes)
                        .addAction(R.drawable.nobutton,
                                "", piNo);

                break;

            case ASK_TO_APPROVE_TOMORROW_CARPAL:
                notification.addAction(R.drawable.yesbutton,
                        "", piYes)
                        .addAction(R.drawable.nobutton,
                                "", piNo);
                break;

            case USER_GOT_PRESENT:
                notification.setAutoCancel(true);
                break;

            default:
                break;


        }


        return notification
                .setContentTitle(carPalNotification.get_mTitle())
                .setContentIntent(pendingIntent)
                .setStyle(new Notification.BigTextStyle()
                        .bigText(carPalNotification.get_mText()))
                .setSmallIcon(R.drawable.logo_new_black)
                .setLargeIcon(bit)
                .build();

    }
}
