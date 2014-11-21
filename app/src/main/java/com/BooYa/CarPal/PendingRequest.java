package com.BooYa.CarPal;

import android.graphics.Bitmap;

/**
 * Created by Barry.Z on 10/29/2014.
 */
public class PendingRequest {
    private int request_id;
    private String pending_request_info;
    private Bitmap notificationPicture;


    public PendingRequest(int request_id, String pending_request_info, Bitmap notificationPicture) {
        this.setRequest_id(request_id);
        this.setPending_request_info(pending_request_info);
        this.notificationPicture = notificationPicture;
    }

    public int getRequest_id() {
        return request_id;
    }

    public void setRequest_id(int request_id) {
        this.request_id = request_id;
    }

    public Bitmap getNotificationPic() {
        return notificationPicture;
    }

    public String getPending_request_info() {
        return pending_request_info;
    }

    public void setPending_request_info(String pending_request_info) {
        this.pending_request_info = pending_request_info;
    }
}
