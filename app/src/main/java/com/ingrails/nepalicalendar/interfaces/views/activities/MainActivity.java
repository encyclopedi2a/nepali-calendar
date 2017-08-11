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
import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@SuppressWarnings("DanglingJavadoc")
public class MainActivity extends CalendarViewActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private ViewPager viewPager;
    private Converter converter;
    private TextView monthName, englishMonthName;
    private ImageView previousMonth, nextMonth;
    private int selectedEnglishMonthPosition = -1, lastSelectedPosition = 0;
    private int currentEnglishYear;
    private boolean isPoushSelected = false;

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
        englishMonthName = (TextView) findViewById(R.id.english_month_name);
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
        currentEnglishYear = Calendar.getInstance().get(Calendar.YEAR);
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
        viewPager.setCurrentItem(74 * 12);
        final Timer timer = new Timer();
        final int[] count = {0};
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (count[0] < 3) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    count[0]++;
                } else {
                    timer.cancel();
                }
            }
        }, 0, 1);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        monthName.setText(converter.getTitle(position));
        if (position > lastSelectedPosition) {
            /**
             * for english year
             */
            if (converter.getTitle(position).split("  ")[1].equals(getString(R.string.Poush))) {
                isPoushSelected = true;
                currentEnglishYear++;
            } else {
                isPoushSelected = false;
            }
            /**
             * for english month
             */
            selectedEnglishMonthPosition++;
        } else {
            /**
             * for english year
             */
            if (converter.getTitle(position).split("  ")[1].equals(getString(R.string.Poush))) {
                isPoushSelected = true;
                //currentEnglishYear--;
            } else {
                if (isPoushSelected) {
                    currentEnglishYear--;
                }
                isPoushSelected = false;
            }
            /**
             * for english month
             */
            selectedEnglishMonthPosition--;
        }
        if (selectedEnglishMonthPosition > 11) {
            selectedEnglishMonthPosition = 0;
        }
        if (selectedEnglishMonthPosition < 0) {
            selectedEnglishMonthPosition = 11;
        }
        englishMonthName.setText(String.format(" - %s %s", converter.getNepaliEquivalentEnglishMonth(converter.getNepaliMonth(selectedEnglishMonthPosition)), currentEnglishYear));
        lastSelectedPosition = position;
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
