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
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        UserHolder holder = null;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new UserHolder();
            holder.btnCancel = (ImageButton) row.findViewById(R.id.btnCancel);
            holder.btnAccept = (ImageButton) row.findViewById(R.id.btnAccept);
            holder.imageviewNotification = (ImageView)row.findViewById(R.id.imageView);
            row.setTag(holder);
        } else
        {
            holder = (UserHolder) row.getTag();
        }
        PendingRequest pendingRequest = data.get(position);
        holder.btnCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(final View view) {
                // TODO Auto-generated method stub
                Log.i("Edit Button Clicked", "**********");
                Toast.makeText(context, "Edit button Clicked",
                        Toast.LENGTH_LONG).show();

                anim.setAnimationListener(new Animation.AnimationListener() {

                    @Override
                    public void onAnimationStart(Animation animation) {

                        view.setHasTransientState(true);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {


                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        PendingRequest item = DriverPanel.userAdapter.getItem(0);
                        DriverPanel.userAdapter.remove(item);
                        view.setHasTransientState(false);
                    }
                });

                //RelativeLayout r = (RelativeLayout) ((ViewGroup) view.getParent()).getParent();

                view.startAnimation(anim);



            }
        });
        holder.btnAccept.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Log.i("Delete Button Clicked", "**********");
                Toast.makeText(context, "Delete button Clicked",
                        Toast.LENGTH_LONG).show();
            }
        });
        return row;

    }

    static class UserHolder {
        ImageButton btnCancel;
        ImageButton btnAccept;
        ImageView imageviewNotification;
    }
}