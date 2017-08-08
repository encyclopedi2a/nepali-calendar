package com.ingrails.nepalicalendar.interfaces.views.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by gokarna on 3/31/17.
 * two default method or all the fragment that should be override by all the class
 */

abstract public class CalendarViewFragment extends Fragment {

    abstract protected void initiliseView(View view);

    abstract protected void initialiseListener();

}
