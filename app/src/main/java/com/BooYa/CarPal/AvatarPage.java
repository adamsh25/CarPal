package com.BooYa.CarPal;

import android.support.v4.app.Fragment;
import android.text.TextUtils;

import com.tech.freak.wizardpager.model.ModelCallbacks;
import com.tech.freak.wizardpager.model.Page;
import com.tech.freak.wizardpager.model.ReviewItem;
import com.tech.freak.wizardpager.model.TextPage;

import java.util.ArrayList;

/**
 * Created by Rony on 11/11/2014.
 */
public class AvatarPage extends TextPage {

    public AvatarPage(ModelCallbacks callbacks, String title) {
        super(callbacks, title);
    }

    @Override
    public Fragment createFragment() {
        return AvatarFragment.create(getKey());
    }

    public AvatarPage setValue(String value) {
        mData.putString(SIMPLE_DATA_KEY, value);
        return this;
    }
}
