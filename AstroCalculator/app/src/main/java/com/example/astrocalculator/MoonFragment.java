package com.example.astrocalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.astrocalculator.AstroCalculator;

import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MoonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MoonFragment extends Fragment {


    private static final String MOONRISE_TIME = "TAG1";
    private static final String MOONSET_TIME = "TAG2";
    private static final String NEW_MOON_DATE = "TAG3";
    private static final String FULL_MOON_DATE = "TAG4";
    private static final String PHASE = "TAG5";
    private static final String MOON_AGE = "TAG6";
    private static final String IMAGE = "IMAGE";
    private static final String DATE_AND_TIME = "DATE_AND_TIME";
    private static final String TIME = "TIME";

    private TextView updateTime;

    private TextView bar1;
    private TextView bar2;
    private TextView bar3;
    private TextView bar4;
    private TextView bar5;
    private TextView bar6;

    private ImageView image;

    private static Bundle args;
    private static MoonFragment fragment;

    public MoonFragment() {
    }


    public static MoonFragment newInstance(Map<String, String> dataPackage, int imageResource) {

        if(fragment == null)
            fragment = new MoonFragment();

        args = new Bundle();
        args.putInt(IMAGE, imageResource);
        unpackData(dataPackage);

        return fragment;
    }

    public static void unpackData(Map<String, String> dataPackage) {

        args.putString(DATE_AND_TIME, dataPackage.get("DATE") + " " + dataPackage.get(TIME));
        args.putString(TIME, "last update:" + dataPackage.get(TIME));
        args.putString(MOONRISE_TIME, "moonrise time: " + dataPackage.get("MOONRISE_TIME"));
        args.putString(MOONSET_TIME, "moonset time: " + dataPackage.get("MOONSET_TIME"));
        args.putString(NEW_MOON_DATE, "new moon date: " + dataPackage.get("NEW_MOON_DATE"));
        args.putString(FULL_MOON_DATE, "full moon date: " + dataPackage.get("FULL_MOON_DATE"));
        args.putString(PHASE, "phase: " + dataPackage.get("PHASE") + "%");
        args.putString(MOON_AGE, "moon age: " + dataPackage.get("MOON_AGE"));
        fragment.setArguments(args);
    }

    public static MoonFragment getInstance() {
        return fragment;
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View itemView = inflater.inflate(R.layout.fragment_layout, container, false);


        bar1 = itemView.findViewById(R.id.bar1);
        bar2 = itemView.findViewById(R.id.bar2);
        bar3 = itemView.findViewById(R.id.bar3);
        bar4 = itemView.findViewById(R.id.bar4);
        bar5 = itemView.findViewById(R.id.bar5);
        bar6 = itemView.findViewById(R.id.bar6);

        image = itemView.findViewById(R.id.Image);

        updateTime = itemView.findViewById(R.id.updateTime);

        bar1.setText(fragment.getArguments().getString(MOONRISE_TIME));
        bar2.setText(fragment.getArguments().getString(MOONSET_TIME));
        bar3.setText(fragment.getArguments().getString(NEW_MOON_DATE));
        bar4.setText(fragment.getArguments().getString(FULL_MOON_DATE));
        bar5.setText(fragment.getArguments().getString(PHASE));
        bar6.setText(fragment.getArguments().getString(MOON_AGE));

        updateTime.setText(fragment.getArguments().getString(TIME));

        image.setImageResource(fragment.getArguments().getInt(IMAGE));

        return itemView;
    }


    public void reloadData() {


        bar1.setText(fragment.getArguments().getString(MOONRISE_TIME));
        bar2.setText(fragment.getArguments().getString(MOONSET_TIME));
        bar3.setText(fragment.getArguments().getString(NEW_MOON_DATE));
        bar4.setText(fragment.getArguments().getString(FULL_MOON_DATE));
        bar5.setText(fragment.getArguments().getString(PHASE));
        bar6.setText(fragment.getArguments().getString(MOON_AGE));

        updateTime.setText(fragment.getArguments().getString(TIME));


    }

    public void resumeInstance() {
        fragment.onResume();
    }
}
