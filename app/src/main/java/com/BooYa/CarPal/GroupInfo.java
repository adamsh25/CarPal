package com.BooYa.CarPal;

import java.util.ArrayList;

/**
 * Created by adam on 22/11/2014.
 */
public class GroupInfo {

    private ArrayList<UserInfo> _groupMembers;
    private String _groupName;

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
}
