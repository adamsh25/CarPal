package com.BooYa.CarPal;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.pkmmte.view.CircularImageView;

import java.util.*;


public class GroupInfoActivity extends Activity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;


    private Map<String,Map<String,View>> _dicViews;
    private GroupInfo _groupInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);
        Initiallize();

        InitializeActionBar();
        InitializeDrawer();
    }

    private void InitializeDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);

        drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
                drawerArrow, R.string.drawer_open,
                R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();


        String[] values = new String[]{
                "HOME",
                "PROFILE",
                "MY RIDE GROUP",
                "NOTIFICATIONS",
                "ABOUT"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, android.R.id.text1, values);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                switch (position) {
                    case 0:
                        finish();
                        mDrawerToggle.setAnimateEnabled(true);
                        //drawerArrow.setProgress(1f);
                        mDrawerToggle.syncState();
                        startActivity(new Intent(getBaseContext(), CarPalPanel.class));

                        break;
                    case 1:
                        mDrawerToggle.setAnimateEnabled(true);
                        //drawerArrow.setProgress(1f);
                        mDrawerToggle.syncState();
                        startActivity(new Intent(getBaseContext(), ProfileActivity.class));
                        break;
                    case 2:
                        mDrawerToggle.setAnimateEnabled(true);
                        //drawerArrow.setProgress(0f);
                        mDrawerToggle.syncState();
                        startActivity(new Intent(getBaseContext(), GroupInfoActivity.class));
                        break;
                    case 3:
                        mDrawerToggle.setAnimateEnabled(true);
                        mDrawerToggle.syncState();
                        break;
                    case 4:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://facebook.com/AppCarPal"));
                        startActivity(browserIntent);
                        break;
                }

            }
        });
    }

    private void InitializeActionBar() {
        ActionBar ab = getActionBar();
        ab.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        ab.setCustomView(R.layout.action_bar_tab);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
        ab.setTitle("MAROON 5");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_car_pal_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
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
                String.format("%s %s", _groupInfo.get_groupMembers().get(1).get_userName(), _groupInfo.get_groupMembers().get(1).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_2))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(1).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(1).get_addressHome().get_streetNumberAddress()));
        ((TextView)findViewById(R.id.groupinfo_name_3)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(2).get_userName(), _groupInfo.get_groupMembers().get(2).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_3))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(2).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(2).get_addressHome().get_streetNumberAddress()));

        ((TextView)findViewById(R.id.groupinfo_name_4)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(3).get_userName(), _groupInfo.get_groupMembers().get(3).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_4))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(3).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(3).get_addressHome().get_streetNumberAddress()));

        ((TextView)findViewById(R.id.groupinfo_name_5)).setText(
                String.format("%s %s", _groupInfo.get_groupMembers().get(4).get_userName(), _groupInfo.get_groupMembers().get(4).get_userLastName()));
        ((TextView)findViewById(R.id.groupinfo_address_5))
                .setText(String.format("%s %d", _groupInfo.get_groupMembers().get(4).get_addressHome().get_streetNameAddress(), _groupInfo.get_groupMembers().get(4).get_addressHome().get_streetNumberAddress()));



        ((CircularImageView)findViewById(R.id.groupinfo_circularImage1))
        .setImageResource(R.drawable.face1);
        ((CircularImageView)findViewById(R.id.groupinfo_circularImage2))
        .setImageResource(R.drawable.face2);
        ((CircularImageView)findViewById(R.id.groupinfo_circularImage3))
        .setImageResource(R.drawable.face3);
        ((CircularImageView)findViewById(R.id.groupinfo_circularImage4))
        .setImageResource(R.drawable.face4);
        ((CircularImageView)findViewById(R.id.groupinfo_circularImage5))
        .setImageResource(R.drawable.face5);


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

        for(View wab : _dicViews.get("whatsAppButton").values())
        {
            ((ImageView)(wab)).setVisibility(View.INVISIBLE);
            ((View) wab.getParent()).setBackgroundResource(R.drawable.bottomline);
        }
        for(View wab : _dicViews.get("phoneButton").values())
        {
            ((ImageView)(wab)).setVisibility(View.INVISIBLE);
        }



        if(wh.getVisibility() == View.VISIBLE) {
            if(key.compareTo("1") != 0) {
                wh.setVisibility(View.INVISIBLE);
                ph.setVisibility(View.INVISIBLE);
            }
            ((View) v.getParent()).setBackgroundResource(R.drawable.bottomline);
            //((View) v.getParent()).setBackgroundColor(Dra);
        }
        else {
            if(key.compareTo("1") != 0) {
                wh.setVisibility(View.VISIBLE);
                ph.setVisibility(View.VISIBLE);
            }
            ((View) v.getParent()).setBackgroundResource(R.drawable.member);
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

    private void changeMapPic(View v)
    {
        int key = Integer.parseInt(((View) v.getParent()).getTag().toString());
        ImageView imageMap = (ImageView)findViewById(R.id.mapImage);
        switch (key) {
            case 1:
                imageMap.setImageResource(R.drawable.map1);
                break;
            case 2:
                imageMap.setImageResource(R.drawable.map2);
                break;
            case 3:
                imageMap.setImageResource(R.drawable.map3);
                break;
            case 4:
                imageMap.setImageResource(R.drawable.map4);
                break;
            case 5:
                imageMap.setImageResource(R.drawable.map5);
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
            changeMapPic(v);
        }
        else if(s.equals("editableText"))
        {
            editTextInANewWindow(v);
        }
    }



    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
}
