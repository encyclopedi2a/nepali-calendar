package com.ingrails.nepalicalendar.interfaces.views.activities;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by gokarna on 8/7/17.
 * top level class for project
 */

abstract public class CalendarViewActivity extends AppCompatActivity {
    abstract public void initializeView();

    abstract public void initializeListeners();
}
