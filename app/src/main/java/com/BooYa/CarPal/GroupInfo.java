package com.BooYa.CarPal;

import android.graphics.Point;

import java.util.ArrayList;

/**
 * Created by adam on 22/11/2014.
 */
public class GroupInfo {

    private ArrayList<UserInfo> _groupMembers;
    private String _groupName;
    private String _workAddress;
    private String _pickUpLocationAddress;

    public ArrayList<UserInfo> get_groupMembers() {
        return _groupMembers;
    }

    public void set_groupMembers(ArrayList<UserInfo> _groupMembers) {
        this._groupMembers = _groupMembers;
    }

    public GroupInfo()
    {
        _groupMembers = new ArrayList<UserInfo>();
        _groupName = "nothing";
    }

    public String get_groupName() {
        return _groupName;
    }

    public void set_groupName(String _groupName) {
        this._groupName = _groupName;
    }

    public String get_workAddress() {
        return _workAddress;
    }

    public void set_workAddress(String _workAddress) {
        this._workAddress = _workAddress;
    }

    public String get_pickUpLocationAddress() {
        return _pickUpLocationAddress;
    }

    public void set_pickUpLocationAddress(String _pickUpLocationAddress) {
        this._pickUpLocationAddress = _pickUpLocationAddress;
    }
}
