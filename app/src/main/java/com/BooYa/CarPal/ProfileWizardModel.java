package com.BooYa.CarPal;

import android.content.Context;
import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.PageList;

/**
 * Created by Rony on 11/11/2014.
 */
public class ProfileWizardModel extends AbstractWizardModel {
    public ProfileWizardModel(Context context) {
        super(context);
    }

    @Override
    protected PageList onNewRootPageList() {
        return new PageList(
                new AvatarPage(this, "Choose your look"),
                new BasicInfoPage(this, "Info"),
                new SettingsPage(this, "Settings"));
    }
}

