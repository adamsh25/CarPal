package com.BooYa.CarPal;

import java.util.Date;

/**
 * Created by adam on 19/11/2014.
 */
public class UserInfo
{
    private String number;
    private String _userName;
    private String _userLastName;
    private String _pictureAddress;
    private String _organizationName;
    private String _moto;
    private Address _addressHome;
    private Address _addressWork;
    private Date _workStartTime;
    private Date get_workEndTime;
    private int[] preferredDays;
    private int[] unPreferredDays;


    public UserInfo(String phoneNumber)
    {
        setNumber(phoneNumber);
    }

    public String getNumber() {
        return number;
    }

    public UserInfo setNumber(String number) {
        this.number = number;
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

    public Date getGet_workEndTime() {
        return get_workEndTime;
    }

    public UserInfo setGet_workEndTime(Date get_workEndTime) {
        this.get_workEndTime = get_workEndTime;return this;
    }

    public int[] getPreferredDays() {
        return preferredDays;
    }

    public UserInfo setPreferredDays(int[] preferredDays) {
        this.preferredDays = preferredDays;return this;
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


}
