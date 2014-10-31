package com.BooYa.CarPal;

/**
 * Created by adam on 30/10/2014.
 */
public enum NotificationTypeEnum
{
    ASK_TO_APPROVE_TOMORROW_CARPAL(" נקבע לך לנהוג מחר, האם תוכל? ", 0),
    USER_CANCEL_CARPAL(" ביטל את הנסיעה למחר, האם תהיה מעוניין להחליפו? ", 1),
    USER_GOT_PRESENT("הקבוצה שלך נותנת לסביבה, ולכן הסביבה מחזירה לכם.", 2)
    ;

    private String stringValue;
    private int intValue;

    private NotificationTypeEnum(String toString, int value)
    {
        stringValue = toString;
        intValue = value;
    }

    @Override
    public String toString()
    {
        return stringValue;
    }
}
