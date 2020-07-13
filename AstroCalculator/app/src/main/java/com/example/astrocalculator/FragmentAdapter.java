package com.example.astrocalculator;


import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.HashMap;
import java.util.Map;


public class FragmentAdapter extends FragmentStateAdapter {

    private Bundle arg;
    private Map<String, String> data;
    private FragmentManager fragmentManager;
    private Map<Integer, String> tags;

    public FragmentAdapter(@NonNull FragmentActivity activity, Map<String, String> data, FragmentManager manager) {
        super(activity);
        this.data = data;
        fragmentManager = manager;
        tags = new HashMap<>();

        tags.put(0, "SUN");
        tags.put(1, "MOON");
        tags.put(2, "MENU");
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 0:
                return SunFragment.newInstance(data, R.drawable.sun_image);
            case 1:
                return MoonFragment.newInstance(data, R.drawable.moon_image);
            case 2:
                return Menu.newInstance();
            default:
                return null;
        }
    }

    public void putIntoBundle() {
        arg = new Bundle();

        if(SunFragment.getInstance() != null)
            fragmentManager.putFragment(arg, "SUN", SunFragment.getInstance());
        fragmentManager.putFragment(arg,"MOON", MoonFragment.getInstance());
        fragmentManager.putFragment(arg, "MENU", Menu.getInstance());
    }


    @Override
    public int getItemCount() {
        return 3;
    }


    public String getTag(int position) {
       return tags.get(position);
    }

    public Fragment getFragment(int position) {
        return fragmentManager.findFragmentByTag(getTag(position));
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void reloadFragments() {

        if(SunFragment.getInstance() != null) {
            SunFragment.unpackData(data);
            SunFragment.getInstance().reloadData();
        }

        if(MoonFragment.getInstance() != null) {
            MoonFragment.unpackData(data);
            MoonFragment.getInstance().reloadData();
        }

    }

}
