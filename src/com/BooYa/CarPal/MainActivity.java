package com.BooYa.CarPal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends Fragment implements View.OnClickListener {

    private ListView listviewPendingRequests;
    private Button btnSettings;
    private Button btnChangeGroup;
    private ImageView imageviewThursdayDriver;
    private ImageView imageViewWednesdayDriver;
    private ImageView imageviewTuesdayDriver;
    private ImageView imageviewMondayDriver;
    private ImageView imageviewSundayDriver;
    private ArrayList<PendingRequest> pendingRequestsList;
    private View rootView;

    /**
     * Called when the activity is first created.
     */

    //@Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.main);
//        findViews();
//        getPendingRequests();
//        getWeeklyDrivers();
//
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {

        rootView = inflater.inflate(R.layout.main, container, false);
        findViews();
        getPendingRequests();
        getWeeklyDrivers();
        final Intent mServiceIntent = new Intent(this.getActivity().getBaseContext(), NotificationService.class);
        this.getActivity().startService(mServiceIntent);
    

        return rootView;
    }

    private void findViews() {
        listviewPendingRequests = (ListView) rootView.findViewById(R.id.listviewPendingRequests);
        btnSettings = (Button) rootView.findViewById(R.id.btnSettings);
        btnChangeGroup = (Button) rootView.findViewById(R.id.btnChangeGroup);
        imageviewThursdayDriver = (ImageView) rootView.findViewById(R.id.imageviewThursdayDriver);
        imageViewWednesdayDriver = (ImageView) rootView.findViewById(R.id.imageViewWednesdayDriver);
        imageviewTuesdayDriver = (ImageView) rootView.findViewById(R.id.imageviewTuesdayDriver);
        imageviewMondayDriver = (ImageView) rootView.findViewById(R.id.imageviewMondayDriver);
        imageviewSundayDriver = (ImageView) rootView.findViewById(R.id.imageviewSundayDriver);

        btnSettings.setOnClickListener(this);
        btnChangeGroup.setOnClickListener(this);
    }

    private void getWeeklyDrivers()
    {
        List<WeeklyDriver> driverList= new ArrayList<WeeklyDriver>();

        driverList.add(new WeeklyDriver("Barry","Zavodnik","1",1,0));

//        driverList = DAL.populate_weekly_drivers;
//        Collections.sort(driverList, new WeeklyDriverOrderedScheduleComparator());
//        imageviewSundayDriver.setImageBitmap(driverList.get(4).getDriverPicture());
//        imageviewMondayDriver.setImageBitmap(driverList.get(3).getDriverPicture());
//        imageviewTuesdayDriver.setImageBitmap(driverList.get(2).getDriverPicture());
//        imageViewWednesdayDriver.setImageBitmap(driverList.get(1).getDriverPicture());
//        imageviewThursdayDriver.setImageBitmap(driverList.get(0).getDriverPicture());

        imageviewSundayDriver.setImageResource(R.drawable.adam);
        imageviewMondayDriver.setImageResource(R.drawable.beeri);
        imageviewTuesdayDriver.setImageResource(R.drawable.rony);
    }

    private void getPendingRequests()
    {
        pendingRequestsList = new ArrayList<PendingRequest>();
        //pendingRequestsList = DAL.populate_weekly_drivers;


        //---------------------
        pendingRequestsList.add(new PendingRequest(1,"a"));
        pendingRequestsList.add(new PendingRequest(1,"b"));
        pendingRequestsList.add(new PendingRequest(1,"c"));
        pendingRequestsList.add(new PendingRequest(1,"d"));
        pendingRequestsList.add(new PendingRequest(1,"e"));
        pendingRequestsList.add(new PendingRequest(1,"f"));
        pendingRequestsList.add(new PendingRequest(1,"g"));

        //---------------------

        UserCustomAdapter userAdapter;
        userAdapter = new UserCustomAdapter(rootView.getContext(), R.layout.row, pendingRequestsList);
        listviewPendingRequests.setItemsCanFocus(false);
        listviewPendingRequests.setAdapter(userAdapter);
        /**
         * get on item click listener
         */
            listviewPendingRequests.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Toast.makeText(getView().getContext(),
                        "List View Clicked:" + position, Toast.LENGTH_LONG)
                        .show();
            }
        });


    }
     @Override
    public void onClick(View v)
     {
        if (v == btnSettings) {
            // Handle clicks for btnSettings
        } else if (v == btnChangeGroup) {
            // Handle clicks for btnChangeGroup
        }
    }

}
