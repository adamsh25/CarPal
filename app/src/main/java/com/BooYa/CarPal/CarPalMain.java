package com.BooYa.CarPal;

       import android.app.ActionBar;
        import android.app.Activity;
        import android.content.Intent;
        import android.content.res.Configuration;
       import android.graphics.Color;
       import android.net.Uri;
        import android.os.Bundle;
        import android.support.v4.widget.DrawerLayout;
        import android.view.MenuItem;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.ListView;

        import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
        import com.ikimuhendis.ldrawer.DrawerArrowDrawable;


public class CarPalMain extends Activity {

    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_pal_main);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);
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
                        mDrawerToggle.setAnimateEnabled(false);
                        drawerArrow.setProgress(1f);
                        break;
                    case 1:
                        mDrawerToggle.setAnimateEnabled(false);
                        drawerArrow.setProgress(0f);
                        break;
                    case 2:
                        mDrawerToggle.setAnimateEnabled(true);
                        mDrawerToggle.syncState();
                        break;
                    case 3:
                        Intent share = new Intent(Intent.ACTION_SEND);
                        share.setType("text/plain");
                        share.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        share.putExtra(Intent.EXTRA_SUBJECT,
                                getString(R.string.app_name));
                        share.putExtra(Intent.EXTRA_TEXT, "CarPal" + "\n" +
                                "GitHub Page :  https://github.com/IkiMuhendis/LDrawer\n" +
                                "Sample App : https://play.google.com/store/apps/details?id=" +
                                getPackageName());
                        startActivity(Intent.createChooser(share,
                                getString(R.string.app_name)));
                        break;
                    case 4:
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/IkiMuhendis/LDrawer"));
                        startActivity(browserIntent);
                        break;
                }

            }
        });
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
