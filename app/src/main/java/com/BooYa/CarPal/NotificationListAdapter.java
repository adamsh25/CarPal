package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class NotificationListAdapter extends ArrayAdapter<Notification> {

	protected static final String LOG_TAG = NotificationListAdapter.class.getSimpleName();
	
	private List<Notification> items;
	private int layoutResourceId;
	private Context context;

	public NotificationListAdapter(Context context, int layoutResourceId, List<Notification> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		NotificationHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new NotificationHolder();
		holder.notification = items.get(position);
		holder.dismissNotifButton = (ImageButton)row.findViewById(R.id.dismiss_notif);
		holder.dismissNotifButton.setTag(holder.notification);

        holder.acceptNotifButton = (ImageButton)row.findViewById(R.id.accept_notif);
        holder.acceptNotifButton.setTag(holder.notification);

		holder.notification_text = (TextView)row.findViewById(R.id.notification_text);
        holder.notification_icon = (ImageView)row.findViewById(R.id.notificationIcon);

        row.setTag(holder);

		setupItem(holder);
		return row;
	}

	private void setupItem(NotificationHolder holder) {
		holder.notification_text.setText(holder.notification.getNotificationName());
        holder.notification_icon.setImageResource(holder.notification.getNotificationIconResourceId());
    }

	public static class NotificationHolder {
		Notification notification;
		TextView notification_text;
        ImageView notification_icon;
		ImageButton dismissNotifButton;
        ImageButton acceptNotifButton;

    }
	
}
