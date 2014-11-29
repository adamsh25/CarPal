package com.BooYa.CarPal;

import java.io.Serializable;

public class Notification implements Serializable {
	private String notificationName;
	private int notificationID;
    private int notificationIconResourceId;

	public Notification(String notificationName, int notificationID,int notificationIconResourceId) {
		this.setNotificationName(notificationName);
		this.setNotificationId(notificationID);
        this.setNotificationIconResourceId(notificationIconResourceId);
	}

	public String getNotificationName() {
		return notificationName;
	}

	public void setNotificationName(String notificationName) {
		this.notificationName = notificationName;
	}

	public double getNotificationId() {
		return notificationID;
	}

	public void setNotificationId(int notificationID) {
		this.notificationID = notificationID;
	}

    public int getNotificationIconResourceId() {
        return notificationIconResourceId;
    }

    public void setNotificationIconResourceId(int notificationIconResourceId) {
        this.notificationIconResourceId = notificationIconResourceId;
    }
}