package com.BooYa.CarPal;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import java.util.Comparator;

/**
 * Created by Barry.Z on 10/29/2014.
 */
public class WeeklyDriver

{
    private int driver_id;
    private String first_name;
    private String last_name;
    private String phone_number;
    private int day_index;


    public WeeklyDriver(String first_name, String last_name, String phone_number, int driver_id,int day_index)
    {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.driver_id = driver_id;
        this.day_index = day_index;
    }

    public String getFirst_name() {
        return first_name;
    }

    public int getDay_Index() {
        return day_index;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public static Bitmap getDriverPicture(int driver_id)
    {
        //return DAL.getDriverPicture(driver_id);
        return null;
    }

    public Bitmap getDriverPicture()
    {
        //return DAL.getDriverPicture(this.driver_id)
        return null;
    }

}
class WeeklyDriverOrderedScheduleComparator implements Comparator<WeeklyDriver> {
    public int compare(WeeklyDriver weeklyDriver1, WeeklyDriver weeklyDriver2)
    {
        return Integer.compare(weeklyDriver1.getDay_Index(), weeklyDriver2.getDay_Index());
    }
}
