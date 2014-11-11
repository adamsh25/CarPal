package com.BooYa.CarPal;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;

import java.util.ArrayList;

/**
 * Created by Rony on 11/11/2014.
 */
public class BasicInfoPage extends Page {
    public static final String FULL_NAME_DATA_KEY = "full_name";
    public static final String ORGANIZATION_DATA_KEY = "organization";
    public static final String MOTO_DATA_KEY = "moto";

    public BasicInfoPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return BasicInfoFragment.create(getKey());
    }

    @Override
    public void getReviewItems(ArrayList<ReviewItem> dest) {
        dest.add(new ReviewItem("Your name", mData.getString(FULL_NAME_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItem("Your organization", mData.getString(ORGANIZATION_DATA_KEY), getKey(), -1));
        dest.add(new ReviewItem("Your moto", mData.getString(MOTO_DATA_KEY), getKey(), -1));
    }

    @Override
    public boolean isCompleted() {
        return !TextUtils.isEmpty(mData.getString(FULL_NAME_DATA_KEY));
    }
}
