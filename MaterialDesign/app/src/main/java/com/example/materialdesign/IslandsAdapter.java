package com.example.materialdesign;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.List;
import java.util.Map;


public class IslandsAdapter extends FragmentStateAdapter {


    private List<Map<String, String>> data;

    public IslandsAdapter(@NonNull FragmentActivity activity) {
        super(activity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {


        switch (position) {
            case 0:
                return ArubaFragment.newInstance();
            case 1:
                return JamaicaFragment.newInstance();
            default:
                return null;
        }
    }


    @Override
    public int getItemCount() {
        return 2;
    }



}
