package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.*;


public class GroupInfoActivity extends Activity implements View.OnClickListener {


    private Map<String,Map<String,View>> _dicViews;
    private GroupInfo _groupInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        Initiallize();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.group_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void addTouchEventsToViews()
    {
        _dicViews = new HashMap<String, Map<String,View>>();
        addViewsToDictionary(R.id.groupinfo_whatsAppButton1);
        addViewsToDictionary(R.id.groupinfo_whatsAppButton2);
        addViewsToDictionary(R.id.groupinfo_whatsAppButton3);
        addViewsToDictionary(R.id.groupinfo_whatsAppButton4);
        addViewsToDictionary(R.id.groupinfo_whatsAppButton5);
        addViewsToDictionary(R.id.groupinfo_phoneButton1);
        addViewsToDictionary(R.id.groupinfo_phoneButton2);
        addViewsToDictionary(R.id.groupinfo_phoneButton3);
        addViewsToDictionary(R.id.groupinfo_phoneButton4);
        addViewsToDictionary(R.id.groupinfo_phoneButton5);
        addViewsToDictionary(R.id.groupinfo_circularImage1);
        addViewsToDictionary(R.id.groupinfo_circularImage2);
        addViewsToDictionary(R.id.groupinfo_circularImage3);
        addViewsToDictionary(R.id.groupinfo_circularImage4);
        addViewsToDictionary(R.id.groupinfo_circularImage5);
        addViewsToDictionary(R.id.txtGroupName);
    }

    private void Initiallize()
    {// Very Ugly Code - Will Be Generic Soon Sorry.
        GetMembersFromDB();
        addTouchEventsToViews();

        ((TextView)findViewById(R.id.txtGroupName)).setText(_groupInfo.get_groupName());


        ((TextView)findViewById(R.id.groupinfo_name_1)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(0).get_userName(), _groupInfo.get_groupMembers().get(0).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_1))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(0).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(0).get_addressHome().get_streetNumberAddress()));

        ((TextView)findViewById(R.id.groupinfo_name_2)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(1).get_userName(), _groupInfo.get_groupMembers().get(0).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_2))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(1).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(0).get_addressHome().get_streetNumberAddress()));
        ((TextView)findViewById(R.id.groupinfo_name_3)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(2).get_userName(), _groupInfo.get_groupMembers().get(0).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_3))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(2).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(0).get_addressHome().get_streetNumberAddress()));

        ((TextView)findViewById(R.id.groupinfo_name_4)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(3).get_userName(), _groupInfo.get_groupMembers().get(0).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_4))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(3).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(0).get_addressHome().get_streetNumberAddress()));

        ((TextView)findViewById(R.id.groupinfo_name_5)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(4).get_userName(), _groupInfo.get_groupMembers().get(0).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_5))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(4).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(0).get_addressHome().get_streetNumberAddress()));


    }

    private void GetMembersFromDB()
    {
        //ToDo: Supposed To Be in BL
        if(_groupInfo == null) {
            DAL.fillFakeData();
            _groupInfo = DAL.sta_groupInfo;
        }


    }

    private void addViewsToDictionary(int id)
    {

        View v = this.findViewById(id);
        v.setOnClickListener(this);
        String tag = v.getTag().toString();
        String key = "";
        if(v.getParent() instanceof TableRow) {
            key = ((View) v.getParent()).getTag().toString();
        }
        else
        {
            key = Integer.toString(id);
        }
        if(_dicViews.containsKey(tag))
        {
            Map<String,View> views = _dicViews.get(tag);
            views.put(key, v);
            _dicViews.remove(tag);
            _dicViews.put(tag, views);
        }
        else
        {
            Map<String,View> views = new HashMap<String,View>();

            views.put(key, v);
            _dicViews.put(tag, views);
        }
    }


    private UserInfo getUserInfoByView(View v)
    {
        UserInfo userInfo = null;
        try
        {
            String tag = ((View) v.getParent()).getTag().toString();
            int index = Integer.parseInt(tag)-1;
            userInfo = _groupInfo.get_groupMembers().get(index);
        }
        catch (Exception ex)
        {
            return new UserInfo("000-000000");
        }
        return userInfo;

    }

    private void openWhatsappContact(UserInfo user)
    {
        Uri uri = Uri.parse("smsto:" + user.getNumber());
        Intent i = new Intent(Intent.ACTION_SENDTO, uri);
        i.setPackage("com.whatsapp");
        startActivity(Intent.createChooser(i, ""));
    }

    private void callContact(UserInfo user)
    {
        Uri uri = Uri.parse("tel:"+user.getNumber());
        Intent i = new Intent(Intent.ACTION_CALL, uri);
        startActivity(i);
    }

    private void showContactInfoButtons(View v)
    {
        String key = ((View) v.getParent()).getTag().toString();
        String tag = v.getTag().toString();
        ImageView wh = ((ImageView)_dicViews.get("whatsAppButton").get(key));
        ImageView ph =((ImageView)_dicViews.get("phoneButton").get(key));
        if(wh.getVisibility() == View.VISIBLE) {
            wh.setVisibility(View.INVISIBLE);
            ph.setVisibility(View.INVISIBLE);
        }
        else {
            wh.setVisibility(View.VISIBLE);
            ph.setVisibility(View.VISIBLE);
        }
    }
    private void editTextInANewWindow(View v)
    {
        Intent i = new Intent(this, EditTextActivity.class);
        i.putExtra("prevText", ((TextView)v).getText());
        startActivityForResult(i, 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case 1: //edit group name text
                if(resultCode == RESULT_OK){
                    String result = data.getStringExtra("changedText");
                    ((TextView)findViewById(R.id.txtGroupName)).setText(result);
                    _groupInfo.set_groupName(result);
                }

            break;

        }
    }

    @Override
    public void onClick(View v)
    {
        String s = "";
        try {
            s = v.getTag().toString();
        }catch(Exception ex){};

        if (s.equals("whatsAppButton"))
        {
            openWhatsappContact(getUserInfoByView(v));
        }
        else if(s.equals("phoneButton"))
        {
            callContact(getUserInfoByView(v));
        }
        else if(s.equals("circularImage"))
        {
            showContactInfoButtons(v);
        }
        else if(s.equals("editableText"))
        {
            editTextInANewWindow(v);
        }
    }
}
