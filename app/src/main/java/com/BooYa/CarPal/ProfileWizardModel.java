package com.BooYa.CarPal;

import android.content.Context;

import com.tech.freak.wizardpager.model.AbstractWizardModel;
import com.tech.freak.wizardpager.model.BranchPage;
import com.tech.freak.wizardpager.model.ImagePage;
import com.tech.freak.wizardpager.model.MultipleFixedChoicePage;
import com.tech.freak.wizardpager.model.NumberPage;
import com.tech.freak.wizardpager.model.PageList;
import com.tech.freak.wizardpager.model.SingleFixedChoicePage;
import com.tech.freak.wizardpager.model.TextPage;

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
                new ImagePage(this, "Choose your look"),
                new BasicInfoPage(this, "Info")/*,
                new AddressPage(this, "Address"),
                new SettingsPage(this, "Settings")*/);
    }
}

