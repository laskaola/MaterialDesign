package com.example.astrocalculator;
import androidx.lifecycle.MutableLiveData;
import com.astrocalculator.AstroCalculator;

import java.util.HashMap;
import java.util.Map;


public class CalculatorData {

    private static CalculatorData instance;
    private AstroCalculatorHandler astroSetup;
    private AstroCalculator calculator;
    private Map<String, String> dataPackage;


    MutableLiveData<Map<String, String>> data = new MutableLiveData<>();

    public CalculatorData() {
        astroSetup = new AstroCalculatorHandler();
        calculator = new AstroCalculator(astroSetup.getDatetime(), astroSetup.getLocation());
        dataPackage = new HashMap<>();
    }

    public static CalculatorData getInstance(){
        if(instance == null) {
            instance = new CalculatorData();
        }
        return instance;
    }

    public void changeLocation(String key, double value) {
        double number = value;

        if(key == "latitude")
            astroSetup.getSetupLocation().setLatitude(value);
        if(key == "longitude")
            astroSetup.getSetupLocation().setLongitude(value);
        astroSetup.resetLocation();
    }


    private void wrapData() {

        String[] temp;

        // sun data
        temp = calculator.getSunInfo().getSunrise().toString().split(" ");
        dataPackage.put("SUNRISE_TIME", temp[1].substring(0, 5));
        temp = calculator.getSunInfo().getSunset().toString().split(" ");
        dataPackage.put("SUNSET_TIME", temp[1].substring(0, 5));
        temp = calculator.getSunInfo().getTwilightMorning().toString().split(" ");
        dataPackage.put("MORNING_TWILIGHT", temp[1].substring(0, 5));
        temp = calculator.getSunInfo().getTwilightEvening().toString().split(" ");
        dataPackage.put("EVENING_TWILIGHT", temp[1].substring(0, 5));
        dataPackage.put("SUNRISE_AZIMUTH", Double.toString(calculator.getSunInfo().getAzimuthRise()).substring(0, Double.toString(calculator.getSunInfo().getAzimuthRise()).lastIndexOf(".") + 3));
        dataPackage.put("SUNSET_AZIMUTH", Double.toString(calculator.getSunInfo().getAzimuthSet()).substring(0, Double.toString(calculator.getSunInfo().getAzimuthSet()).lastIndexOf(".") + 3));

        // moon data
        temp = calculator.getMoonInfo().getMoonrise().toString().split(" ");
        dataPackage.put("MOONRISE_TIME", temp[1].substring(0, 5));
        temp = calculator.getMoonInfo().getMoonset().toString().split(" ");
        dataPackage.put("MOONSET_TIME", temp[1].substring(0, 5));
        temp = calculator.getMoonInfo().getNextNewMoon().toString().split(" ");
        dataPackage.put("NEW_MOON_DATE", temp[0]);
        temp = calculator.getMoonInfo().getNextFullMoon().toString().split(" ");
        dataPackage.put("FULL_MOON_DATE", temp[0]);
        dataPackage.put("PHASE", Double.toString(calculator.getMoonInfo().getIllumination()*100).substring(0, Double.toString(calculator.getMoonInfo().getIllumination()).lastIndexOf(".") + 1));
        dataPackage.put("MOON_AGE", Double.toString(calculator.getMoonInfo().getAge()).substring(0, Double.toString(calculator.getMoonInfo().getAge()).lastIndexOf(".") + 3));

        StringBuffer dateAndTime = new StringBuffer();

        dateAndTime.append(astroSetup.getDatetime().getDay());
        dateAndTime.append(".");
        dateAndTime.append(astroSetup.getDatetime().getMonth());
        dateAndTime.append(".");
        dateAndTime.append(astroSetup.getDatetime().getYear());

        dataPackage.put("DATE", dateAndTime.toString());

        dateAndTime = new StringBuffer();
        if(Integer.toString(astroSetup.getDatetime().getHour()).length() < 2)
            dateAndTime.append("0");
        dateAndTime.append(astroSetup.getDatetime().getHour());
        dateAndTime.append(":");
        if(Integer.toString(astroSetup.getDatetime().getMinute()).length() < 2)
            dateAndTime.append("0");
        dateAndTime.append(astroSetup.getDatetime().getMinute());


        dataPackage.put("TIME", dateAndTime.toString());

        dataPackage.put("LATITUDE", Double.toString(astroSetup.getLocation().getLatitude()));
        dataPackage.put("LONGITUDE", Double.toString(astroSetup.getLocation().getLongitude()));
    }



    public MutableLiveData<Map<String, String>> getCalculatorData() {
        wrapData();
        data.setValue(this.dataPackage);
        return data;
    }


    public MutableLiveData<Map<String, String>> resetData(){
        astroSetup.resetDateTime();
        calculator = new AstroCalculator(astroSetup.getDatetime(), astroSetup.getLocation());
        wrapData();
        data.setValue(this.dataPackage);
        return data;
    }


}
