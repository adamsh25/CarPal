package com.BooYa.CarPal;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.location.*;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.*;
import com.google.maps.android.clustering.ClusterManager;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupGoogleMapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Menu      mMenu;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private Map<String,Marker> _dicMarkers = new HashMap<String, Marker>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_google_maps);
        setUpMapIfNeeded();
        //InitializeActionBar();
        //InitializeDrawer();
        BL.CONTEXT = this;

        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker)
            {


                ActionBar actionBar = getActionBar();

                return true;
            }
        });

        InitializeActionBar();
        InitializeDrawer();
        setMenuItemsVisibleDefault();

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
        ab.setCustomView(R.layout.action_bar_tab_group);
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        addMembersToMap();
        addMeetingLocationToMap();
        addCurrentLocationToMap();
        focusOnMarker("meetLocation");
    }

    private void addMembersToMap()
    {
        try
        {
            ArrayList<UserInfo> members = DAL.getSta_groupInfo().get_groupMembers();
            int i = 0;
            for (UserInfo groupMember : members) {

                addMarkerToMember(i++);
            }
        }
        catch(Exception ex)
        {
            String s = ex.getMessage();
            s = "";
        }


    }


    private void focusOnMarker(String key)
    {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(_dicMarkers.get(key).getPosition(), 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14.5f), 2000, null);
    }

    private void addMarkerToMember(int i)
    {
        try
        {
            String title ="";
                UserInfo groupMember = DAL.getSta_groupInfo().get_groupMembers().get(i);
                int id = groupMember.get_imgRecourceID();
                if(i == 0)
                {
                    title = "Me";
                    id = R.drawable.house;
                    addMarkerToWork(groupMember);
                }
                else
                {
                    title= String.format("%s %s", groupMember.get_userName(), groupMember.get_userLastName());
                }
                LatLng memberLocation = null;
                memberLocation = BL.GetLatLngFromAddress(this, groupMember.get_addressHome().toString());
                Marker memberMarker = mMap.addMarker(new MarkerOptions()
                        .position(memberLocation)
                        .title(title)
                        .snippet(title)
                        .icon(BitmapDescriptorFactory
                                .fromResource(id)))
                        ;
                _dicMarkers.put(groupMember.get_number() ,memberMarker);

        }
        catch(Exception ex)
        {
            String s = ex.getMessage();
            s = "";
        }

    }

    private void addMarkerToWork(UserInfo groupMember)
    {
        LatLng memberLocation = null;
        memberLocation = BL.GetLatLngFromAddress(this, groupMember.get_addressWork().toString());
        Marker memberMarker = mMap.addMarker(new MarkerOptions()
                .position(memberLocation)
                .title("Work")
                .snippet("Work")
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.workplace)))
                ;
        _dicMarkers.put("workLocation" ,memberMarker);
    }

    private void addMeetingLocationToMap()
    {
        ArrayList<UserInfo> members = DAL.getSta_groupInfo().get_groupMembers();
        MarkerOptions mOptions =  new MarkerOptions()
                .position(BL.GetCenter(this,members))
                .title("")
                .snippet(String.format("%s", "PALS MEET SPOT"))
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.meet));
        _dicMarkers.put("meetLocation" ,mMap.addMarker(mOptions));
    }

    private void addCurrentLocationToMap()
    {
        mMap.setOnMyLocationChangeListener(new GoogleMap.OnMyLocationChangeListener() {

            @Override
            public void onMyLocationChange(Location arg0) {
                // TODO Auto-generated method stub

                MarkerOptions mOptions =  new MarkerOptions()
                        .position(new LatLng(arg0.getLatitude(),arg0.getLongitude()))
                        .title("My Location")
                        .snippet(String.format("%s", "My Location"))
                        .icon(BitmapDescriptorFactory
                                .fromResource(R.drawable.face1));
                _dicMarkers.put("myLocation", mMap.addMarker(mOptions));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        //(menu).add(Menu.NONE,555,Menu.NONE,"My location").setIcon(R.drawable.caricon);
        inflater.inflate(R.menu.menu_car_pal_group_map, menu);
        mMenu = menu;
        setMenuItemsVisibleDefault();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId())
        {
            case android.R.id.home:
                if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                } else{
                    mDrawerLayout.openDrawer(mDrawerList);
                }
                break;
            case R.id.action_home:
                String home = DAL.getSta_groupInfo().get_groupMembers().get(0).get_number();
                focusOnMarker(home);
                break;
            case R.id.action_work:
                focusOnMarker("workLocation");
                break;
            case R.id.action_meetLocation:
                focusOnMarker("meetLocation");
                break;

            case R.id.action_navigate:
                startActivity(new Intent(getBaseContext(), GroupGoogleMapsActivity.class));
                break;
            case R.id.action_call:
                startActivity(new Intent(getBaseContext(), GroupGoogleMapsActivity.class));
                break;
            case R.id.action_plusfive:
                startActivity(new Intent(getBaseContext(), GroupGoogleMapsActivity.class));
                break;
            case R.id.action_cancel:
                startActivity(new Intent(getBaseContext(), GroupGoogleMapsActivity.class));
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
        setMenuItemsVisibleDefault();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    private void setMenuItemsVisibleDefault()
    {
        if(mMenu != null) {
            //(mMenu).findItem(R.id.action_snooz).setVisible(false);
            (mMenu).findItem(R.id.action_call).setVisible(false);
            (mMenu).findItem(R.id.action_navigate).setVisible(false);


            (mMenu).findItem(R.id.action_home).setVisible(true);
            (mMenu).findItem(R.id.action_work).setVisible(true);
            (mMenu).findItem(R.id.action_meetLocation).setVisible(true);

            (mMenu).findItem(R.id.action_plusfive).setVisible(false);
            (mMenu).findItem(R.id.action_cancel).setVisible(false);
        }
    }

    private void setVisibleOnClickMe()
    {

    }

    private void setVisibleOnClickMember()
    {

    }

}
