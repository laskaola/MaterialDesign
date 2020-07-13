package com.example.astrocalculator;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.card.MaterialCardView;

import java.util.HashMap;
import java.util.Map;


public class SunFragment extends Fragment {

    private static final String TITLE1 = "TITLE1";
    private static final String TITLE2 = "TITLE2";
    private static final String TITLE3 = "TITLE3";
    private static final String SUNRISE_TIME = "TAG1";
    private static final String SUNRISE_AZIMUTH = "TAG2";
    private static final String SUNSET_TIME = "TAG3";
    private static final String SUNSET_AZIMUTH = "TAG4";
    private static final String MORNING_TWILIGHT = "TAG5";
    private static final String EVENING_TWILIGHT = "TAG6";
    private static final String IMAGE = "IMAGE";
    private static final String DATE_AND_TIME = "DATE_AND_TIME";

    String title;

    private TextView currentDataTime;

    private MaterialCardView sunCard;
    private TextView title1;
    private TextView title2;
    private TextView title3;

    private TextView bar1;
    private TextView bar2;
    private TextView bar3;
    private TextView bar4;
    private TextView bar5;
    private TextView bar6;
    private TextView updateTime;

    private ImageView image;

    private static Bundle args;
    private static SunFragment fragment;

    public SunFragment() {

    }

    public static SunFragment newInstance(Map<String, String> dataPackage, int imageResource) {

        if(fragment == null)
            fragment = new SunFragment();

       args = new Bundle();
       args.putInt(IMAGE, imageResource);
       unpackData(dataPackage);

       return fragment;
    }

    public static void unpackData(Map<String, String> dataPackage) {

        args.putString(DATE_AND_TIME, dataPackage.get("DATE") + " " + dataPackage.get("TIME"));
        args.putString("TIME", "last update:" + dataPackage.get("TIME"));
        args.putString(TITLE1, "sunrise");
        args.putString(TITLE2, "sunset");
        args.putString(TITLE3, "twilight");
        args.putString(SUNRISE_TIME, "time: " + dataPackage.get("SUNRISE_TIME"));
        args.putString(SUNRISE_AZIMUTH, "azimuth: " + dataPackage.get("SUNRISE_AZIMUTH"));
        args.putString(SUNSET_TIME, "time: " + dataPackage.get("SUNSET_TIME"));
        args.putString(SUNSET_AZIMUTH, "azimuth: " + dataPackage.get("SUNSET_AZIMUTH"));
        args.putString(MORNING_TWILIGHT, "morning: " + dataPackage.get("MORNING_TWILIGHT"));
        args.putString(EVENING_TWILIGHT, "evening: " + dataPackage.get("EVENING_TWILIGHT"));
        fragment.setArguments(args);

    }

    public static SunFragment getInstance() {
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View itemView = inflater.inflate(R.layout.sun_fragment, container, false);

        title1 = itemView.findViewById(R.id.title1);
        title2 = itemView.findViewById(R.id.title2);
        title3 = itemView.findViewById(R.id.title3);

        bar1 = itemView.findViewById(R.id.bar1);
        bar2 = itemView.findViewById(R.id.bar2);
        bar3 = itemView.findViewById(R.id.bar3);
        bar4 = itemView.findViewById(R.id.bar4);
        bar5 = itemView.findViewById(R.id.bar5);
        bar6 = itemView.findViewById(R.id.bar6);

        image = itemView.findViewById(R.id.Image);

        updateTime = itemView.findViewById(R.id.updateTime);


        title1.setText(fragment.getArguments().getString(TITLE1));
        title2.setText(fragment.getArguments().getString(TITLE2));
        title3.setText(fragment.getArguments().getString(TITLE3));

        bar1.setText(fragment.getArguments().getString(SUNRISE_TIME));
        bar2.setText(fragment.getArguments().getString(SUNRISE_AZIMUTH));
        bar3.setText(fragment.getArguments().getString(SUNSET_TIME));
        bar4.setText(fragment.getArguments().getString(SUNSET_AZIMUTH));
        bar5.setText(fragment.getArguments().getString(MORNING_TWILIGHT));
        bar6.setText(fragment.getArguments().getString(EVENING_TWILIGHT));

        updateTime.setText(fragment.getArguments().getString("TIME"));

        image.setImageResource(fragment.getArguments().getInt(IMAGE));

        return itemView;
    }


    public void reloadData() {


        title1.setText(fragment.getArguments().getString(TITLE1));
        title2.setText(fragment.getArguments().getString(TITLE2));
        title3.setText(fragment.getArguments().getString(TITLE3));

        bar1.setText(fragment.getArguments().getString(SUNRISE_TIME));
        bar2.setText(fragment.getArguments().getString(SUNRISE_AZIMUTH));
        bar3.setText(fragment.getArguments().getString(SUNSET_TIME));
        bar4.setText(fragment.getArguments().getString(SUNSET_AZIMUTH));
        bar5.setText(fragment.getArguments().getString(MORNING_TWILIGHT));
        bar6.setText(fragment.getArguments().getString(EVENING_TWILIGHT));
        updateTime.setText(fragment.getArguments().getString("TIME"));

    }

    public void resumeInstance() {
        fragment.onResume();
    }
}
