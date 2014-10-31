package com.BooYa.CarPal;

/**
 * Created by Barry.Z on 10/29/2014.
 */

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.*;

public class UserCustomAdapter extends ArrayAdapter<PendingRequest> {
    Context context;
    int layoutResourceId;
    ArrayList<PendingRequest> data = new ArrayList<PendingRequest>();
    final Animation anim ;


    public UserCustomAdapter(Context context, int layoutResourceId,
                             ArrayList<PendingRequest> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
        anim = AnimationUtils.loadAnimation(context, R.anim.fade_anim);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        UserHolder holder = null;

        if (convertView == null)
        {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, null);
            holder = new UserHolder();
            holder.btnCancel = (ImageButton) convertView.findViewById(R.id.btnCancel);
            holder.btnAccept = (ImageButton) convertView.findViewById(R.id.btnAccept);
            holder.imageviewNotification = (ImageView)convertView.findViewById(R.id.imageView);
            holder.imageviewNotification.setImageBitmap(data.get(position).getNotificationPic());
            convertView.setTag(holder);
        }
        else
        {
            holder = (UserHolder) convertView.getTag();
            holder.imageviewNotification.setImageBitmap(data.get(position).getNotificationPic());
        }
        PendingRequest pendingRequest = data.get(position);
        holder.btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Rejected the offer",
                        Toast.LENGTH_LONG).show();

                PendingRequest item = DriverPanel.userAdapter.getItem(position);
                DriverPanel.userAdapter.remove(item);

            }
        });
        holder.btnAccept.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Accepted the offer",
                        Toast.LENGTH_LONG).show();

                PendingRequest item = DriverPanel.userAdapter.getItem(position);
                DriverPanel.userAdapter.remove(item);
                DriverPanel.imageView.setVisibility(View.VISIBLE);
            }
        });
        return convertView;

    }



    static class UserHolder {
        ImageButton btnCancel;
        ImageButton btnAccept;
        ImageView imageviewNotification;
    }
}