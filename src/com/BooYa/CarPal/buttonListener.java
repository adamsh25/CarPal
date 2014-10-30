package com.BooYa.CarPal;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by adam on 30/10/2014.
 */
public class ButtonListener extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        boolean yes = intent.getAction().contains("yes");
        boolean no = intent.getAction().contains("no");
        int notificationTypeId =  Integer.parseInt(intent.getAction().replace("_yes","").replace("_no",""));
        NotificationTypeEnum typeEnum = NotificationTypeEnum.values()[notificationTypeId];
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        switch (typeEnum)
        {
           case ASK_TO_APPROVE_TOMORROW_CARPAL:
               manager.cancel(0);
               break;
           case USER_CANCEL_CARPAL:
               manager.cancel(1);
               break;
        }

    }
}
