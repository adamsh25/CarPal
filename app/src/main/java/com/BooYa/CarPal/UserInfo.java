package com.BooYa.CarPal;

/**
 * Created by adam on 19/11/2014.
 */
public class UserInfo
{
    private String number;
    public UserInfo(String phoneNumber)
    {
        setNumber(phoneNumber);
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
