package com.BooYa.CarPal;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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
        Intent newIntent;
         if(!yes && !no)
         {
             switch (typeEnum)
             {
                 case USER_GOT_PRESENT:
                     String URL = intent.getStringExtra("URL");
                     newIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(URL));
                     newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                     context.startActivity(newIntent);
                     break;
                 default:
                 newIntent = new Intent(context, MainDriverPanel.class);
                 newIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                 context.startActivity(newIntent);
                 break;
             }
         }
        else
         {
             switch (typeEnum)
             {

                 case ASK_TO_APPROVE_TOMORROW_CARPAL:

                     break;
                 case USER_CANCEL_CARPAL:

                     break;
                 case USER_GOT_PRESENT:

                     break;

             }
             manager.cancel(typeEnum.ordinal());
         }

    }
}
