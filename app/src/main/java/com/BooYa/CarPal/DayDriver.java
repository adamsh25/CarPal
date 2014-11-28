package com.BooYa.CarPal;

import java.io.Serializable;

/**
 * Created by Barry.Z on 11/21/2014.
 */
public class DayDriver implements Serializable{

    private String driverName;
    private int DriverID;
    private int DriverPicResourceID;


    public DayDriver(String driverName, int driverID, int driverPicResourceID) {
        this.driverName = driverName;
        DriverID = driverID;
        DriverPicResourceID = driverPicResourceID;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public int getDriverID() {
        return DriverID;
    }

    public void setDriverID(int driverID) {
        DriverID = driverID;
    }

    public int getDriverPicResourceID() {
        return DriverPicResourceID;
    }

    public void setDriverPicResourceID(int driverPicResourceID) {
        DriverPicResourceID = driverPicResourceID;
    }

}