package com.example.materialdesign;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;
import java.util.Map;


public class FragmentAdapter extends FragmentStateAdapter {

    private Bundle arg;
    private List<Map<String, String>> data;
    private Map<Integer, String> tags;

    public FragmentAdapter(@NonNull FragmentActivity activity, List<Map<String, String>> data) {
        super(activity);
        this.data = data;
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 0:
                return HomeFragment.newInstance(data);
            case 1:
                return ProfileFragment.newInstance();
            default:
                return null;
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }



}
