package com.BooYa.CarPal;

/**
 * Created by adam on 30/10/2014.
 */
public class CarPalNotification
{
    private int _mTypeId;
    private String _mTitle;
    private String _mText;
    private String _mURL;


    private CarPalNotification()
    {

    }
    public CarPalNotification(int mTypeId)
    {
        this._mTypeId = mTypeId;
    }
    public CarPalNotification(int _mTypeId, String _notificationText)
    {
        this._mTypeId = _mTypeId;
        this.set_mText(_notificationText);
    }
    public CarPalNotification(int _mTypeId, String _notificationText, String URL)
    {
        this._mTypeId = _mTypeId;
        this.set_mText(_notificationText);
        this.set_mURL(URL);
    }


    public int get_mTypeId() {
        return _mTypeId;
    }

    public void set_mTypeId(int _mTypeId) {
        this._mTypeId = _mTypeId;
    }

    public String get_mText() {
        return _mText;
    }

    public void set_mText(String _mText) {
        this._mText = _mText;
    }

    public String get_mTitle() {
        return _mTitle;
    }

    public void set_mTitle(String _mTitle) {
        this._mTitle = _mTitle;
    }

    public String get_mURL() {
        return _mURL;
    }

    public void set_mURL(String _mURL) {
        this._mURL = _mURL;
    }
}
