package com.BooYa.CarPal;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
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
import java.util.List;

public class GroupGoogleMapsActivity extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;


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
                Toast.makeText(getBaseContext(),"blll",Toast.LENGTH_LONG);
                return true;
            }
        });

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
    }

    private void addMembersToMap()
    {
        try
        {
            ArrayList<UserInfo> members = DAL.getSta_groupInfo().get_groupMembers();
            for (UserInfo groupMember : members) {

                LatLng memberLocation = null;
                memberLocation = BL.GetLatLngFromAddress(this, groupMember.get_addressHome().toString());
                Marker memberMarker = mMap.addMarker(new MarkerOptions()
                        .position(memberLocation)
                        .title("")
                        .snippet(String.format("%s %s", groupMember.get_userName(), groupMember.get_userLastName()))
                        .icon(BitmapDescriptorFactory
                                .fromResource(groupMember.get_imgRecourceID())))
                        ;
                //Move the camera instantly to hamburg with a zoom of 15.
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(memberLocation, 15));

                // Zoom in, animating the camera.
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14.5f), 2000, null);
            }
        }
        catch(Exception ex)
        {
            String s = ex.getMessage();
            s = "";
        }


    }

    private void addMeetingLocationToMap()
    {
        ArrayList<UserInfo> members = DAL.getSta_groupInfo().get_groupMembers();
        MarkerOptions mOptions =  new MarkerOptions()
                .position(BL.GetCenter(this,members))
                .title("")
                .snippet(String.format("%s", "PALS MEET SPOT"))
                .icon(BitmapDescriptorFactory
                        .fromResource(R.drawable.caricon));
        mMap.addMarker(mOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mOptions.getPosition(), 15));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14.5f), 2000, null);
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
