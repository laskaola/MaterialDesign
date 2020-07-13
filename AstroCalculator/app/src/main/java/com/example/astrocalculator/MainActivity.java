package com.example.astrocalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import androidx.viewpager2.widget.ViewPager2;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.TextView;


import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Calendar;
import java.util.Map;

public class MainActivity extends AppCompatActivity {


    private FragmentAdapter adapter;
    private ViewPager2 viewPager;
    private TabLayout tab;
    private MainActivityViewModel viewModel;
    private BroadcastReceiver updateReceiver;
    private boolean bool = true;
    private TextView time;
    private TextView latitude;
    private TextView longitude;
    int counter = 0;
    int updatePeriod;




    @NonNull
    @Override
    public FragmentManager getSupportFragmentManager() {
        return super.getSupportFragmentManager();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tab = findViewById(R.id.tabs);

        time = findViewById(R.id.currentDataAndTime);
        latitude = findViewById(R.id.cLatitude);
        longitude = findViewById(R.id.cLongitude);

        time.setText(setTime());

        viewPager = findViewById(R.id.viewPager);

        viewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        viewModel.init();

        latitude.setText("latitude: " + viewModel.getCalculatorDataViewModel().getValue().get("LATITUDE"));
        longitude.setText("longitude: " + viewModel.getCalculatorDataViewModel().getValue().get("LONGITUDE"));

        viewModel.getCalculatorDataViewModel().observe(this, new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> info) {
                adapter.notifyDataSetChanged();
            }
        });
        initViewPager();

        TabLayoutMediator.TabConfigurationStrategy tabConfig = new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {

                switch (position) {
                    case 0:
                        tab.setText("sun");
                        break;
                    case 1:
                        tab.setText("moon");
                        break;
                    case 2:
                        tab.setText("menu");
                        break;
                }
            }
        };

        TabLayoutMediator tabMediator = new TabLayoutMediator(tab, viewPager, tabConfig);
        tabMediator.attach();

        startUpdating();

    }



    private void initViewPager(){
        adapter = new FragmentAdapter(this, viewModel.getCalculatorDataViewModel().getValue(), getSupportFragmentManager());
        viewPager.setAdapter(adapter);
    }

    private void reloadViewPager(){

        adapter.setData(viewModel.getCalculatorDataViewModel().getValue());
        adapter.reloadFragments();
    }

    public void reloadViewModel() {
        viewModel.reloadData();
        reloadViewPager();
    }

    public void startUpdating() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        counter = 0;
        updatePeriod = 1;
        updateReceiver = new BroadcastReceiver(){

            @Override
            public void onReceive(Context context, Intent intent) {
                counter++;
                time.setText(setTime());

                if(counter == updatePeriod) {
                    reloadViewModel();
                    counter = 0;
                }
            }
        };

        registerReceiver(updateReceiver, intentFilter);
    }

    public String setTime() {

        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int minute = Calendar.getInstance().get(Calendar.MINUTE);
        StringBuffer sHour = new StringBuffer();
        StringBuffer sMinute = new StringBuffer();


        if(Integer.toString(hour).length() < 2)
            sHour.append("0");
        sHour.append(hour);

        if(Integer.toString(minute).length() < 2)
            sMinute.append("0");
        sMinute.append(minute);

        return sHour.toString() + ":" + sMinute.toString();
    }

    public void resetCounter() {
        counter = 0;
    }

    public void setUpdatePeriod(int setup) {
        updatePeriod = setup;
    }

    public void updateLocation() {
        latitude.setText("latitude: " + viewModel.getCalculatorDataViewModel().getValue().get("LATITUDE"));
        longitude.setText("longitude: " + viewModel.getCalculatorDataViewModel().getValue().get("LONGITUDE"));
    }

}
