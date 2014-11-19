package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by Barry.Z on 10/30/2014.
 */
public class DriverPanel extends Activity implements View.OnClickListener {

    public static RelativeLayout relativeLayout;
    public static ImageView imageView;
    public static ListView listviewPendingRequests;
    public static UserCustomAdapter userAdapter;
    private ImageView imageviewTabs;
    private Button btnPrivateProfile;
    private Button btnGroupProfile;
    private Button btnMain;
    private Button notifications;
    private Button btnAchievements;
    private ArrayList<PendingRequest> pendingRequestsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.driver_panel);
        getActionBar().hide();
        final Intent mServiceIntent = new Intent(this, NotificationService.class);
        this.startService(mServiceIntent);
        findViews();

        getPendingRequests();
    }

    private void getPendingRequests() {

        pendingRequestsList = new ArrayList<PendingRequest>();
        //pendingRequestsList = DAL.populate_weekly_drivers;
        //---------------------
        pendingRequestsList.add(new PendingRequest(1, "a", BitmapFactory.decodeResource(getResources(), R.drawable.rony_last)));
        pendingRequestsList.add(new PendingRequest(1, "b", BitmapFactory.decodeResource(getResources(), R.drawable.alert2_last)));
        pendingRequestsList.add(new PendingRequest(1, "c", BitmapFactory.decodeResource(getResources(), R.drawable.alert3_last)));

        //---------------------
        userAdapter = new UserCustomAdapter(this, R.layout.row, pendingRequestsList);
        listviewPendingRequests.setItemsCanFocus(false);
        listviewPendingRequests.setAdapter(userAdapter);

        final Animation anim = AnimationUtils.loadAnimation(this, R.anim.fade_anim);

//        listviewPendingRequests.setOnItemClickListener(new AdapterView.OnItemClickListener()
//        {
//
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, final int position,
//                                    long id)
//            {
//
//
//                PendingRequest item = userAdapter.getItem(position);
//                userAdapter.remove(item);
//            }
//        });


    }


    private void findViews() {
        imageView = (ImageView) findViewById(R.id.imageView);
        imageviewTabs = (ImageView) findViewById(R.id.imageview_tabs);
        btnPrivateProfile = (Button) findViewById(R.id.btn_privateProfile);
        btnGroupProfile = (Button) findViewById(R.id.btn_groupProfile);
        btnMain = (Button) findViewById(R.id.btn_main);
        notifications = (Button) findViewById(R.id.notifications);
        btnAchievements = (Button) findViewById(R.id.btnAchievements);
        listviewPendingRequests = (ListView) findViewById(R.id.listView);
        relativeLayout = (RelativeLayout) findViewById(R.id.main_relative);

        btnPrivateProfile.setOnClickListener(this);
        btnGroupProfile.setOnClickListener(this);
        btnMain.setOnClickListener(this);
        notifications.setOnClickListener(this);
        btnAchievements.setOnClickListener(this);
    }

    /**
     * Handle button click events<br />
     * <br />
     * Auto-created on 2014-10-31 00:45:51 by Android Layout Finder
     * (http://www.buzzingandroid.com/tools/android-layout-finder)
     */
    @Override
    public void onClick(View v) {
        if (v == btnPrivateProfile) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
            // Handle clicks for btnPrivateProfile
        } else if (v == btnGroupProfile) {
            // Handle clicks for btnGroupProfile
        } else if (v == btnMain) {
            Intent intent = new Intent(this, DriverPanel.class);
            startActivity(intent);
        } else if (v == notifications) {
            // Handle clicks for notifications
            Intent intent = new Intent(this, BroadCastActivity.class);
            startActivity(intent);
        } else if (v == btnAchievements) {
            // Handle clicks for btnAchievements
        }
    }


}