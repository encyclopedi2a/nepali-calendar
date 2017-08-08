package com.ingrails.nepalicalendar.interfaces.views.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingrails.nepalicalendar.R;
import com.ingrails.nepalicalendar.interfaces.converter.Converter;
import com.ingrails.nepalicalendar.interfaces.views.fragments.CalendarFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends CalendarViewActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private Converter converter;
    private TextView monthName;
    private ImageView previousMonth, nextMonth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeView();
        initializeListeners();
        setUpViewPager();
    }

    @Override
    public void initializeView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        monthName = (TextView) findViewById(R.id.month_name);
        previousMonth = (ImageView) findViewById(R.id.previous_month);
        nextMonth = (ImageView) findViewById(R.id.next_month);
    }

    @Override
    public void initializeListeners() {
        viewPager.addOnPageChangeListener(this);
        previousMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
    }

    private void setUpViewPager() {
        converter = new Converter(this);
        monthName.setText(converter.getTitle(0));
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        for (int i = 0; i < converter.getCalendarSize(); i++) {
            Bundle bundle = new Bundle();
            bundle.putInt("no_of_days", converter.getNumberOfDaysInSpecificMonth(i));
            bundle.putInt("week_start_index", converter.getStartOfWeekInSpecificMonth(i));
            Fragment fragment = new CalendarFragment();
            fragment.setArguments(bundle);
            viewPagerAdapter.addFragment(fragment);
        }
        viewPagerAdapter.notifyDataSetChanged();
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        monthName.setText(converter.getTitle(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.previous_month:
                viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
                break;
            case R.id.next_month:
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                break;
        }
    }

    private class ViewPagerAdapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();

        private ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }


        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        private void addFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

}
