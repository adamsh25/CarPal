package com.BooYa.CarPal;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.viewpagerindicator.CirclePageIndicator;
import com.viewpagerindicator.PageIndicator;

import java.util.ArrayList;

// TODO shadow for pics
// TODO one custom view for five days
// TODO real divider
// TODO make button animated

public class CarPalPanel extends FragmentActivity {

    private NotificationListAdapter adapter;
    private MyFragmentAdapter mAdapter;
    private ViewPager mPager;
    private PageIndicator mIndicator;
    private ListView notificationListView;
    private int mPagerPosition = 0;
    static final int ANIMATION_DURATION = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_pal_panel);

        setupPagerAdapter();
        setupListViewAdapter();
        populateData();

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

    private void populateData()
    {
        adapter.insert(new Notification("WOULD YOU LIKE TO COVER DANIEL SUNDAY?", 1,R.drawable.notificon), 0);
        adapter.insert(new Notification("50% AT SPAGETTHIM TODAY!", 1,R.drawable.prize), 1);
        adapter.insert(new Notification("WOULD YOU LIKE TO COVER DANIEL SUNDAY?", 1,R.drawable.notificon), 0);
        adapter.insert(new Notification("50% AT SPAGETTHIM TODAY!", 1,R.drawable.prize), 1);
        adapter.insert(new Notification("WOULD YOU LIKE TO COVER DANIEL SUNDAY?", 1,R.drawable.notificon), 0);
        adapter.insert(new Notification("50% AT SPAGETTHIM TODAY!", 1,R.drawable.prize), 1);

        DayDriver dayDriverSunday = new DayDriver("DAN",1,R.drawable.nodriver);
        DayDriver dayDriverMonday = new DayDriver("TAL",2,R.drawable.nodriver);
        DayDriver dayDriverTuesday = new DayDriver("JOHN",3,R.drawable.nodriver);
        DayDriver dayDriverWednesday = new DayDriver("PAT",4,R.drawable.nodriver);
        DayDriver dayDriverThursday = new DayDriver("ME",5,R.drawable.beeri);


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
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "WOW!  Using CarPal helps the environment!");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
    }

}
