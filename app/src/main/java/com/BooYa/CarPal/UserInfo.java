package com.BooYa.CarPal;

import java.util.Date;

/**
 * Created by adam on 19/11/2014.
 */
public class UserInfo
{
    private String _number;
    private String _userName;
    private String _userLastName;
    private String _pictureAddress;
    private String _organizationName;
    private String _moto;
    private Address _addressHome;
    private Address _addressWork;
    private Date _workStartTime;
    private Date _workEndTime;
    private int _imgRecourceID;// TODO: will be URI From Server.
    private int[] _preferredDays;
    private int[] _unPreferredDays;


    public UserInfo(String phoneNumber)
    {
        set_number(phoneNumber);
    }

    public String get_number() {
        return _number;
    }

    public UserInfo set_number(String _number) {
        this._number = _number;
        return this;
    }

    public String get_userName() {
        return _userName;
    }

    public UserInfo set_userName(String _userName) {
        this._userName = _userName;return this;
    }

    public String get_userLastName() {
        return _userLastName;
    }

    public UserInfo set_userLastName(String _userLastName) {
        this._userLastName = _userLastName;return this;
    }

    public String get_pictureAddress() {
        return _pictureAddress;
    }

    public UserInfo set_pictureAddress(String _pictureAddress) {
        this._pictureAddress = _pictureAddress;return this;
    }

    public String get_organizationName() {
        return _organizationName;
    }

    public UserInfo set_organizationName(String _organizationName) {
        this._organizationName = _organizationName;return this;
    }

    public String get_moto() {
        return _moto;
    }

    public UserInfo set_moto(String _moto) {
        this._moto = _moto;return this;
    }



    public Date get_workStartTime() {
        return _workStartTime;
    }

    public UserInfo set_workStartTime(Date _workStartTime) {
        this._workStartTime = _workStartTime;return this;
    }

    public Date get_workEndTime() {
        return _workEndTime;
    }

    public UserInfo set_workEndTime(Date _workEndTime) {
        this._workEndTime = _workEndTime;return this;
    }

    public int[] get_preferredDays() {
        return _preferredDays;
    }

    public UserInfo set_preferredDays(int[] _preferredDays) {
        this._preferredDays = _preferredDays;return this;
    }

    public Address get_addressHome() {
        return _addressHome;
    }

    public UserInfo set_addressHome(Address _addressHome) {
        this._addressHome = _addressHome;
        return this;
    }

    public Address get_addressWork() {
        return _addressWork;
    }

    public UserInfo set_addressWork(Address _addressWork) {
        this._addressWork = _addressWork;return this;
    }


    public int get_imgRecourceID() {
        return _imgRecourceID;
    }

    public UserInfo set_imgRecourceID(int _imgRecourceID) {
        this._imgRecourceID = _imgRecourceID;
        return (this);
    }
}
