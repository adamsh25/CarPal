package com.BooYa.CarPal;

/**
 * Created by Barry.Z on 10/30/2014.
 */

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new MainActivity();
            case 1:
                return new MainActivity();
            case 2:
                return new MainActivity();
            case 3:
                return new MainActivity();
            case 4:
                return new MainActivity();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 5;
    }

}