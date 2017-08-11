package com.ingrails.nepalicalendar.interfaces.models;

/**
 * Created by gokarna on 8/11/17.
 */

public class CalendarModel {
    private String date;
    private String month;
    private String name;
    private String day;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
