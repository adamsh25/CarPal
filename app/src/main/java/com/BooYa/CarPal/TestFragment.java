package com.BooYa.CarPal;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public final class TestFragment extends Fragment {
    private static final String KEY_CONTENT = "TestFragment:Content";

    public static TestFragment newInstance(StatsItem content) {
        TestFragment fragment = new TestFragment();


        fragment.mContent = content;

        return fragment;
    }

    private StatsItem mContent = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((savedInstanceState != null) && savedInstanceState.containsKey(KEY_CONTENT)) {
            mContent.setStatsText(savedInstanceState.getString(KEY_CONTENT));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//       TextView text = new TextView(getActivity());
//       text.setGravity(Gravity.CENTER);
//       text.setText(mContent.getStatsText());
//       text.setTextSize(20 * getResources().getDisplayMetrics().density);
//       text.setPadding(20, 20, 20, 20);

        ImageView imageView = new ImageView(getActivity());
        imageView.setImageResource(mContent.getStatsPicResourceID());

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
        layout.setGravity(Gravity.CENTER);

//        layout.addView(text);

        layout.addView(imageView);


        return layout;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_CONTENT, mContent.getStatsText());
    }
}
