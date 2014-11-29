package com.BooYa.CarPal;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class MyFragmentAdapter extends FragmentPagerAdapter {
    protected static final StatsItem[] CONTENT = new StatsItem[] {
            new StatsItem("My Group Ride reduced 138 vehicles off the road so far!",1,R.drawable.mainstat),
            new StatsItem("My Group Ride saved up 458 USD so far!",2,R.drawable.ic_launcher),
            new StatsItem("My Group Ride reduced 195 g/kWh of Carbon monoxide so far!",3,R.drawable.ic_launcher),
            new StatsItem("My Group Ride saved ...!",4,R.drawable.ic_launcher) };

    private int mCount = CONTENT.length;

    public MyFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return TestFragment.newInstance(CONTENT[position % CONTENT.length]);
    }

    @Override
    public int getCount() {
        return mCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return MyFragmentAdapter.CONTENT[position % CONTENT.length].getStatsText();
    }

    public void setCount(int count) {
        if (count > 0 && count <= 10) {
            mCount = count;
            notifyDataSetChanged();
        }
    }
}