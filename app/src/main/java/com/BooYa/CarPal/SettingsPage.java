package com.BooYa.CarPal;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;

import java.util.ArrayList;

/**
 * Created by Rony on 13/11/2014.
 */
public class SettingsPage extends Page {
    public static final String START_WORK_TIME_HOUR_DATA_KEY = "start_work_hour";
    public static final String START_WORK_TIME_MINUTE_DATA_KEY = "start_work_minute";
    public static final String END_WORK_TIME_HOUR_DATA_KEY = "end_work_hour";
    public static final String END_WORK_TIME_MINUTE_DATA_KEY = "end_work_minute";
    public static final String PREFERRED_DAYS_DATA_KEY = "preferred_days";
    public static final String UNPREFERRED_DAYS_DATA_KEY = "unpreferred_days";

    public SettingsPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return SettingsFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Start work time", SettingsFragment.GetTimeString(mData.getInt(START_WORK_TIME_HOUR_DATA_KEY), mData.getInt(START_WORK_TIME_MINUTE_DATA_KEY)), getKey(), -1));
        dest.add(new ReviewItem("End work time", SettingsFragment.GetTimeString(mData.getInt(END_WORK_TIME_HOUR_DATA_KEY), mData.getInt(END_WORK_TIME_MINUTE_DATA_KEY)), getKey(), -1));
        dest.add(new ReviewItem("Preferred days", mData.getString(PREFERRED_DAYS_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItem("Preferred days", mData.getString(UNPREFERRED_DAYS_DATA_KEY), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        //return !TextUtils.isEmpty(mData.getString(START_WORK_TIME_DATA_KEY)) && !TextUtils.isEmpty(mData.getString(END_WORK_TIME_DATA_KEY));
        return true;
    }
}
