package com.BooYa.CarPal;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.sleepbot.datetimepicker.time.RadialPickerLayout;
import com.sleepbot.datetimepicker.time.TimePickerDialog;
import com.tech.freak.wizardpager.ui.PageFragmentCallbacks;

import java.util.Calendar;

import be.billington.calendar.recurrencepicker.LinearLayoutWithMaxWidth;
import be.billington.calendar.recurrencepicker.RecurrencePickerDialog;
import be.billington.calendar.recurrencepicker.WeekButton;

/**
 * Created by Rony on 13/11/2014.
 */
public class SettingsFragment extends Fragment {
    private static final String ARG_KEY = "key";
    public static final String TIMEPICKER_TAG = "timepicker";
    private final int DEFAULT_START_TIME_HOUR = 8;
    private final int DEFAULT_START_TIME_MINUTE = 0;
    private final int DEFAULT_END_TIME_HOUR = 18;
    private final int DEFAULT_END_TIME_MINUTE = 0;

    private PageFragmentCallbacks mCallbacks;
    private String mKey;
    private SettingsPage mPage;
    private TextView mStartWorkTimeView;
    private TextView mEndWorkTimeView;
    private LinearLayoutWithMaxWidth mPreferredDaysView;
    private LinearLayoutWithMaxWidth mUnPreferredDaysView;

    public static SettingsFragment create(String key) {
        Bundle args = new Bundle();
        args.putString(ARG_KEY, key);

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public SettingsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle args = getArguments();
        mKey = args.getString(ARG_KEY);
        mPage = (SettingsPage) mCallbacks.onGetPage(mKey);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.profile_fragment_page_settings, container, false);
        ((TextView) rootView.findViewById(android.R.id.title)).setText(mPage.getTitle());

        mStartWorkTimeView = ((TextView) rootView.findViewById(R.id.profile_start_work_time));
        mStartWorkTimeView.setText(GetTimeString(mPage.getData().getInt(SettingsPage.START_WORK_TIME_HOUR_DATA_KEY, DEFAULT_START_TIME_HOUR), mPage.getData().getInt(SettingsPage.START_WORK_TIME_MINUTE_DATA_KEY, DEFAULT_START_TIME_MINUTE)));

        mEndWorkTimeView = ((TextView) rootView.findViewById(R.id.profile_end_work_time));
        mEndWorkTimeView.setText(GetTimeString(mPage.getData().getInt(SettingsPage.END_WORK_TIME_HOUR_DATA_KEY, DEFAULT_END_TIME_HOUR), mPage.getData().getInt(SettingsPage.END_WORK_TIME_MINUTE_DATA_KEY, DEFAULT_END_TIME_MINUTE)));

        mPreferredDaysView = ((LinearLayoutWithMaxWidth) rootView.findViewById(R.id.profile_preferred_days));
        //todo: select from page data

        mUnPreferredDaysView = ((LinearLayoutWithMaxWidth) rootView.findViewById(R.id.profile_unpreferred_days));
        //todo: select from page data

        return rootView;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        if (!(activity instanceof PageFragmentCallbacks)) {
            throw new ClassCastException("Activity must implement PageFragmentCallbacks");
        }

        mCallbacks = (PageFragmentCallbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Time picker dialog code
        final Calendar calendar = Calendar.getInstance();
        final TimePickerDialog timePickerDialog_start = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
                SetTime(mStartWorkTimeView, hour, minute, SettingsPage.START_WORK_TIME_HOUR_DATA_KEY, SettingsPage.START_WORK_TIME_MINUTE_DATA_KEY);
            }
        }, 0, 0, true, true);
        mStartWorkTimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog_start.setStartTime(mPage.getData().getInt(SettingsPage.START_WORK_TIME_HOUR_DATA_KEY, DEFAULT_START_TIME_HOUR), mPage.getData().getInt(SettingsPage.START_WORK_TIME_MINUTE_DATA_KEY, DEFAULT_START_TIME_MINUTE));
                timePickerDialog_start.show(getFragmentManager(), TIMEPICKER_TAG);
            }
        });

        final TimePickerDialog timePickerDialog_end = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout radialPickerLayout, int hour, int minute) {
                SetTime(mEndWorkTimeView, hour, minute, SettingsPage.END_WORK_TIME_HOUR_DATA_KEY, SettingsPage.END_WORK_TIME_MINUTE_DATA_KEY);
            }
        }, 0, 0, true, true);
        timePickerDialog_end.setCloseOnSingleTapMinute(false);

        mEndWorkTimeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog_end.setStartTime(mPage.getData().getInt(SettingsPage.END_WORK_TIME_HOUR_DATA_KEY, DEFAULT_END_TIME_HOUR), mPage.getData().getInt(SettingsPage.END_WORK_TIME_MINUTE_DATA_KEY, DEFAULT_END_TIME_MINUTE));
                timePickerDialog_end.show(getFragmentManager(), TIMEPICKER_TAG);
            }
        });

        if (savedInstanceState != null) {
            TimePickerDialog tpd = (TimePickerDialog) getFragmentManager().findFragmentByTag(TIMEPICKER_TAG);
            if (tpd != null) {
                tpd.setOnTimeSetListener(null);
            }
        }

//        mFullNameView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
//                                          int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                mPage.getData().putString(BasicInfoPage.FULL_NAME_DATA_KEY,
//                        (editable != null) ? editable.toString() : null);
//                mPage.notifyDataChanged();
//            }
//        });
//
//        mMotoView.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1,
//                                          int i2) {
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//                mPage.getData().putString(BasicInfoPage.MOTO_DATA_KEY,
//                        (editable != null) ? editable.toString() : null);
//                mPage.notifyDataChanged();
//            }
//        });
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);

        // In a future update to the support library, this should override setUserVisibleHint
        // instead of setMenuVisibility.
//        if (mFullNameView != null) {
//            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(
//                    Context.INPUT_METHOD_SERVICE);
//            if (!menuVisible) {
//                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);
//            }
//        }
    }

    public void SetTime(TextView view, int hourOfDay, int minute, String hourKey, String minuteKey) {
        mPage.getData().putInt(hourKey, hourOfDay);
        mPage.getData().putInt(minuteKey, minute);
        mPage.notifyDataChanged();
        view.setText(GetTimeString(hourOfDay, minute));
    }

    public static String GetTimeString(int hourOfDay, int minute) {
        return String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
    }
}
