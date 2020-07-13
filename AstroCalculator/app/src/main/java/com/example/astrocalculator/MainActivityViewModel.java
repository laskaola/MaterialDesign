package com.example.astrocalculator;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Map;

public class MainActivityViewModel extends ViewModel {

    private MutableLiveData<Map<String, String>> calculatorDataViewModel;
    private CalculatorData calculatorData;


    public void init() {
        if(calculatorDataViewModel != null) {
            return;
        }
        calculatorData = CalculatorData.getInstance();
        calculatorDataViewModel = calculatorData.getCalculatorData();
    }

    public void reloadData() {
        calculatorDataViewModel = calculatorData.resetData();
    }

    public LiveData<Map<String, String>> getCalculatorDataViewModel() {
        return calculatorDataViewModel;
    }




}
