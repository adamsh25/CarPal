package com.BooYa.CarPal;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;

// TODO one custom view for five days
public class CarPalPanel extends FragmentActivity {

    private NotificationListAdapter adapter;
    private MyFragmentAdapter mAdapter;
    private ViewPager mPager;
    private PageIndicator mIndicator;
    private ListView notificationListView;
    private int mPagerPosition = 0;
    static final int ANIMATION_DURATION = 200;

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_pal_panel);

        //Start notification service.
        final Intent mServiceIntent = new Intent(this, NotificationService.class);
        this.startService(mServiceIntent);

        InitializeActionBar();
        InitializeDrawer();

        setupPagerAdapter();
        setupListViewAdapter();
        populateData();

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
                "PROFILE",
                "MY RIDE GROUP",
                "NOTIFICATIONS",
                "SHARE",
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
                        mDrawerToggle.setAnimateEnabled(true);
                        //drawerArrow.setProgress(1f);
                        mDrawerToggle.syncState();
                        startActivity(new Intent(getBaseContext(), ProfileActivity.class));
                        break;
                    case 1:
                        mDrawerToggle.setAnimateEnabled(true);
                        //drawerArrow.setProgress(0f);
                        mDrawerToggle.syncState();
                        startActivity(new Intent(getBaseContext(), GroupInfoActivity.class));
                        break;
                    case 2:
                        mDrawerToggle.setAnimateEnabled(true);
                        mDrawerToggle.syncState();
                        break;
                    case 3:
                        shareStatOnClickHandler(null);
//                        Intent share = new Intent(Intent.ACTION_SEND);
//                        share.setType("text/plain");
//                        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        share.putExtra(Intent.EXTRA_SUBJECT,
//                                getString(R.string.app_name));
//                        share.putExtra(Intent.EXTRA_TEXT, "CarPal" + "\n" +
//                                "GitHub Page :  https://github.com/IkiMuhendis/LDrawer\n" +
//                                "Sample App : https://play.google.com/store/apps/details?id=" +
//                                getPackageName());
//                        startActivity(Intent.createChooser(share,
//                                getString(R.string.app_name)));
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

    public void removeNotificationOnClickHandler(View v)
    {
        // Handle dismissed notifications
        Notification itemToRemove = (Notification)v.getTag();
        deleteCell(notificationListView.getChildAt(adapter.getPosition(itemToRemove)),v);
        Toast.makeText(CarPalPanel.this, "YOU REJECTED THE OFFER!", Toast.LENGTH_SHORT).show();
        manageEmptyListView();
    }

    public void AcceptNotificationOnClickHandler(View v)
    {
        // Handle accepted notifications
        Notification itemToRemove = (Notification)v.getTag();
        deleteCell(notificationListView.getChildAt(adapter.getPosition(itemToRemove)),v);
        Toast.makeText(CarPalPanel.this, "YOU ACCEPTED THE OFFER!", Toast.LENGTH_SHORT).show();
        manageEmptyListView();

    }

    private void manageEmptyListView()
    {
        if(notificationListView.getCount() == 1) {
            TextView textViewNoNotifications = (TextView)findViewById(R.id.textviewNoNotifications);
            textViewNoNotifications.setVisibility(View.VISIBLE);
        }
    }

    private void setupListViewAdapter()
    {
        adapter = new NotificationListAdapter(CarPalPanel.this, R.layout.notification_item, new ArrayList<Notification>());
        notificationListView = (ListView)findViewById(R.id.NoticiationList);
        notificationListView.setAdapter(adapter);
    }

    private void setupPagerAdapter()
    {
        mAdapter = new MyFragmentAdapter(getSupportFragmentManager());

        // Get pagerview object
        mPager = (ViewPager)findViewById(R.id.pager);
        mPager.setAdapter(mAdapter);

        // Get indicator object
        mIndicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);

        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                //Toast.makeText(CarPalPanel.this, "Changed to page " + position, Toast.LENGTH_SHORT).show();
                mPagerPosition = position;
            }

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public void ShowPlusMeOnClickHandler(View v)
    {

        switch (((ImageView)(((RelativeLayout)(v.getParent())).findViewById(R.id.plusme))).getVisibility()) {
            case View.VISIBLE:
            ((ImageView)(((RelativeLayout)(v.getParent())).findViewById(R.id.plusme))).setVisibility(View.INVISIBLE);
             break;
            case View.INVISIBLE:
             ((ImageView)(((RelativeLayout)(v.getParent())).findViewById(R.id.plusme))).setVisibility(View.VISIBLE);
        }
    }
    private void populateData()
    {
        adapter.insert(new Notification("WOULD YOU LIKE TO REPLACE AVI?", 1,R.drawable.notificon), 0);
        adapter.insert(new Notification("50% AT SPAGETTHIM TODAY!", 1,R.drawable.prize), 1);
        adapter.insert(new Notification("WOULD YOU LIKE TO REPLACE AVI?", 1,R.drawable.notificon), 0);
        adapter.insert(new Notification("50% AT SPAGETTHIM TODAY!", 1,R.drawable.prize), 1);
        adapter.insert(new Notification("WOULD YOU LIKE TO REPLACE AVI?", 1,R.drawable.notificon), 0);
        adapter.insert(new Notification("50% AT SPAGETTHIM TODAY!", 1,R.drawable.prize), 1);

        DayDriver dayDriverSunday = new DayDriver("ME",1,R.drawable.face1);
        DayDriver dayDriverMonday = new DayDriver("RON",2,R.drawable.face2);
        DayDriver dayDriverTuesday = new DayDriver("TAL",3,R.drawable.face3);
        DayDriver dayDriverWednesday = new DayDriver("AVI",4,R.drawable.face4);
        DayDriver dayDriverThursday = new DayDriver("LINA",5,R.drawable.face5);


        assignDriverToDay(R.id.sunday,dayDriverSunday);
        assignDriverToDay(R.id.monday,dayDriverMonday);
        assignDriverToDay(R.id.tuesday,dayDriverTuesday);
        assignDriverToDay(R.id.wednesday,dayDriverWednesday);
        assignDriverToDay(R.id.thursday,dayDriverThursday);

    }

    private void assignDriverToDay(int dayLayoutID,DayDriver dayDriver)
    {
        View day = findViewById(dayLayoutID);
        TextView driverName = (TextView)day.findViewById(R.id.textviewDriverName);
        ImageView driverPic = (ImageView)day.findViewById(R.id.imageViewDriverPic);

        driverName.setText(dayDriver.getDriverName());
        driverPic.setImageResource(dayDriver.getDriverPicResourceID());
    }

    private void deleteCell(final View v,final View buttonview) {

        Animation.AnimationListener al = new Animation.AnimationListener() {
            @Override
            public void onAnimationEnd(Animation arg0) {
                Notification itemToRemove = (Notification)buttonview.getTag();
                adapter.remove(itemToRemove);

                adapter.notifyDataSetChanged();
            }
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationStart(Animation animation) {}
        };

        collapse(v, al);
    }

    private void collapse(final View v, Animation.AnimationListener al) {
        final int initialHeight = v.getMeasuredHeight();

        Animation anim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                }
                else {
                    v.getLayoutParams().height = initialHeight - (int)(initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        if (al!=null) {
            anim.setAnimationListener(al);
        }
        anim.setDuration(ANIMATION_DURATION);
        v.startAnimation(anim);
    }

    public void shareStatOnClickHandler(View v)
    {
        String shareBody = mAdapter.getPageTitle(mPagerPosition).toString();
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "WoW! Using CarPal helps the environment!");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_car_pal_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
