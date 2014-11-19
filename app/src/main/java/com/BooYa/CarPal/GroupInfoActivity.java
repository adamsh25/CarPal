package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.*;


public class GroupInfoActivity extends Activity implements View.OnClickListener {

    private ArrayList<UserInfo>  _groupMembers;
    Map<String,List<View>> _dicViews;

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
        _dicViews = new HashMap<String, List<View>>();
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
    }

    private void Initiallize()
    {
        GetMembersFromDB();
        addTouchEventsToViews();
               /*CircularImageView circularImageView = (CircularImageView)findViewById(R.id.circular2);
        circularImageView.setBorderColor(getResources().getColor(R.color.abc_primary_text_material_dark));
        circularImageView.setBorderWidth(10);
        circularImageView.setSelectorColor(getResources().getColor(R.color.abc_primary_text_material_dark));
        circularImageView.setSelectorStrokeColor(getResources().getColor(R.color.abc_search_url_text));
        circularImageView.setSelectorStrokeWidth(10);
        circularImageView.addShadow();*/
    }

    private void GetMembersFromDB()
    {
        //ToDo: Supposed To Be in BL
        _groupMembers = new ArrayList<UserInfo>();
        _groupMembers.add(new UserInfo("0542501474"));
        _groupMembers.add(new UserInfo("972548018050"));
        _groupMembers.add(new UserInfo("0542501474"));
        _groupMembers.add(new UserInfo("0542501474"));
        _groupMembers.add(new UserInfo("0542501474"));

    }

    private void addViewsToDictionary(int id)
    {

        View v = this.findViewById(id);
        v.setOnClickListener(this);
        String tag = v.getTag().toString();
        if(_dicViews.containsKey(tag))
        {
            List<View> views = _dicViews.get(tag);
            views.add(v);
            _dicViews.remove(tag);
            _dicViews.put(tag, views);
        }
        else
        {
            List<View> views = new ArrayList<View>();
            views.add(v);
            _dicViews.put(tag, views);
        }
    }


    private UserInfo getUserInfoByView(View v)
    {
        UserInfo userInfo = null;
        try
        {
            String tag = ((View) v.getParent()).getTag().toString();
            int index = Integer.parseInt(tag);
            userInfo = _groupMembers.get(index--);
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
    }
}
