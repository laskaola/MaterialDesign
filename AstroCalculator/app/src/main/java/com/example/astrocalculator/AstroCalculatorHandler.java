package com.example.astrocalculator;
import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class AstroCalculatorHandler {

    private AstroDateTime dateTime;
    private AstroCalculatorHandler.Time setupTime;

    private AstroCalculator.Location location;
    private AstroCalculatorHandler.Location setupLocation;

    public AstroCalculatorHandler() {

        setupTime = new AstroCalculatorHandler.Time();
        setupLocation = new AstroCalculatorHandler.Location();

        dateTime = new AstroDateTime(
                setupTime.getYear(),
                setupTime.getMonth(),
                setupTime.getDay(),
                setupTime.getHour(),
                setupTime.getMinute(),
                setupTime.getSecond(),
                setupTime.getTimezoneOffset(),
                setupTime.isDaylightSaving()
        );
        location = new AstroCalculator.Location(setupLocation.getLatitude(), setupLocation.getLongitude());
    }

    public AstroDateTime getDatetime() {
        return dateTime;
    }

    public AstroCalculator.Location getLocation() {
        return location;
    }


    public AstroCalculatorHandler.Location getSetupLocation() {
        return setupLocation;
    }


    public void resetLocation() {
        location = new AstroCalculator.Location(setupLocation.getLatitude(), setupLocation.getLongitude());
    }

    public void resetDateTime(){
        setupTime.setTime();
        dateTime = new AstroDateTime(
                setupTime.getYear(),
                setupTime.getMonth(),
                setupTime.getDay(),
                setupTime.getHour(),
                setupTime.getMinute(),
                setupTime.getSecond(),
                setupTime.getTimezoneOffset(),
                setupTime.isDaylightSaving()
        );
    }


    class Location {

        private double latitude;
        private double longitude;


        public Location() {
            latitude = 51.773134;
            longitude = 19.448587;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }


    }

    static class Time {

        private int year;
        private int month;
        private int day;
        private int hour;
        private int minute;
        private int second;
        private int timezoneOffset;
        private boolean daylightSaving;

        public Time() {

           setTime();
        }

        public int getYear() {
            return year;
        }

        public int getMonth() {
            return month;
        }

        public int getDay() {
            return day;
        }

        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }

        public int getSecond() {
            return second;
        }

        public int getTimezoneOffset() {
            return timezoneOffset;
        }

        public boolean isDaylightSaving() {
            return daylightSaving;
        }

        public void setTime() {

            year = Calendar.getInstance().get(Calendar.YEAR);
            month = Calendar.getInstance().get(Calendar.MONTH);
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
            hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
            minute = Calendar.getInstance().get(Calendar.MINUTE);
            second = Calendar.getInstance().get(Calendar.SECOND);
            timezoneOffset = (Calendar.getInstance().get(Calendar.ZONE_OFFSET) + Calendar.getInstance().get(Calendar.DST_OFFSET)) / (60 * 1000);
            daylightSaving = true;

        }

    }
}


