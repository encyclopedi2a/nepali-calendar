package com.ingrails.nepalicalendar.interfaces.views.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ingrails.nepalicalendar.R;
import com.ingrails.nepalicalendar.interfaces.converter.Converter;
import com.ingrails.nepalicalendar.interfaces.models.CalendarModel;
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
    private ViewPagerAdapter viewPagerAdapter;
    private RecyclerView calendarRecyclerView;
    private List<CalendarModel> calendarModelList;
    private CalendarRecyclerViewAdapter calendarRecyclerViewAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarModelList = new ArrayList<>();
        String[] dateArray = {"२०७४", "२०७४", "२०७४", "२०७४", "२०७४", "२०७४", "२०७४", "२०७४", "२०७४", "२०७४", "२०७४", "२०७४"};
        String[] monthArray = {getString(R.string.Baisakh), getString(R.string.Jestha), getString(R.string.Ashar), getString(R.string.Shrawan), getString(R.string.Bhadra), getString(R.string.Ashoj), getString(R.string.Kartik), getString(R.string.Mangsir), getString(R.string.Poush), getString(R.string.Magh), getString(R.string.Falgun), getString(R.string.Chaitra)};
        String[] dayArray = {"१", "२", "३", "४", "५", "६", "७", "८", "९", "१०", "११", "१२"};
        String[] nameArray = {"मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो", "मेरो नाम राम हो"};
        for (int i = 0; i < dateArray.length; i++) {
            CalendarModel calendarModel = new CalendarModel();
            calendarModel.setDate(dateArray[i]);
            calendarModel.setMonth(monthArray[i]);
            calendarModel.setName(nameArray[i]);
            calendarModel.setDay(dayArray[i]);
            calendarModelList.add(calendarModel);
        }
        initializeView();
        initializeListeners();
        initialiseRecyclerView();
        setUpViewPager();
    }

    @Override
    public void initializeView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        monthName = (TextView) findViewById(R.id.month_name);
        previousMonth = (ImageView) findViewById(R.id.previous_month);
        nextMonth = (ImageView) findViewById(R.id.next_month);
        englishMonthName = (TextView) findViewById(R.id.english_month_name);
        calendarRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
    }

    @Override
    public void initializeListeners() {
        viewPager.addOnPageChangeListener(this);
        previousMonth.setOnClickListener(this);
        nextMonth.setOnClickListener(this);
    }


    private void initialiseRecyclerView() {
        calendarRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        calendarRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        calendarRecyclerViewAdapter = new CalendarRecyclerViewAdapter();
        calendarRecyclerView.setAdapter(calendarRecyclerViewAdapter);
    }

    private class CalendarRecyclerViewAdapter extends RecyclerView.Adapter {
        private List<CalendarModel> calendarModelList = new ArrayList<>();

        @Override
        public android.support.v7.widget.RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_footer, parent, false);
            return new VHItem(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            VHItem vhItem = (VHItem) holder;
            CalendarModel calendarModel = calendarModelList.get(position);
            vhItem.dateTextView.setText(calendarModel.getMonth());
            vhItem.dayTextView.setText(calendarModel.getDay());
            vhItem.eventTextView.setText(calendarModel.getName());
        }

        @Override
        public int getItemCount() {
            return calendarModelList.size();
        }

        class VHItem extends RecyclerView.ViewHolder {
            TextView eventTextView;
            TextView dateTextView;
            TextView dayTextView;

            private VHItem(View itemView) {
                super(itemView);
                eventTextView = itemView.findViewById(R.id.eventTitleTv);
                dateTextView = itemView.findViewById(R.id.dateTv);
                dayTextView = itemView.findViewById(R.id.dayTv);
            }
        }
    }

    private void setUpViewPager() {
        converter = new Converter(this);
        monthName.setText(converter.getTitle(0));
        currentEnglishYear = Calendar.getInstance().get(Calendar.YEAR);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
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
        ((CalendarFragment) viewPagerAdapter.getItem(position)).onUpdateView();
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
        /**
         * initialise event here
         */
        calendarRecyclerViewAdapter.calendarModelList.clear();
        for (CalendarModel calendarModel : calendarModelList) {
            String titleArray[] = monthName.getText().toString().split("  ");
            if (titleArray[0].equals(calendarModel.getDate()) && titleArray[1].equals(calendarModel.getMonth())) {
                calendarRecyclerViewAdapter.calendarModelList.add(calendarModel);
            }
        }
        calendarRecyclerViewAdapter.notifyDataSetChanged();
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
