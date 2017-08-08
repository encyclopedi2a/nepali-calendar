package com.ingrails.nepalicalendar.interfaces.converter;

import android.content.Context;

import com.ingrails.nepalicalendar.R;
import com.ingrails.nepalicalendar.interfaces.models.DateModel;

import org.joda.time.DateTime;
import org.joda.time.Days;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by gokarna on 8/8/17.
 * bs to ad converter
 */

public class Converter {
    private Map<Integer, int[]> daysInMonthMap = new LinkedHashMap<>();
    private Map<Integer, int[]> startWeekDayMonthMap = new LinkedHashMap<>();
    private Map<Integer, String> englishEquivalentNepaliYear = new LinkedHashMap<>();
    private Map<Integer, String> englishEquivalentNepaliDay = new LinkedHashMap<>();
    private List<String> nepaliYearWithMonth = new ArrayList<>();
    private List<Integer> noOfDaysInSpecificMonth = new ArrayList<>();
    private List<Integer> startOfWeekInSpecificMonth = new ArrayList<>();

    private Context context;

    public Converter() {
        initializeEnglishEquivalentNepaliDay();
    }

    public Converter(Context context) {
        this.context = context;
        availableDateinBs();
        initializeEnglishEquivalentNepaliYear();
        initializeEnglishEquivalentNepaliYear();
        initializeNepaliDateListWithYear();
        initializeNoOfDaysInSpecificMonth();
        initializeStartOfWeekInSpecificMonth();
    }


    public int getNumberOfDaysInSpecificMonth(int position) {
        return noOfDaysInSpecificMonth.get(position);
    }

    public int getStartOfWeekInSpecificMonth(int position) {
        return startOfWeekInSpecificMonth.get(position);
    }

    public String getEnglishEquivalentNepaliDay(int englishDay) {
        return englishEquivalentNepaliDay.get(englishDay);
    }

    private void initializeNoOfDaysInSpecificMonth() {
        for (Map.Entry<Integer, int[]> map : daysInMonthMap.entrySet()) {
            int[] daysArray = map.getValue();
            int position = 0;
            for (int days : daysArray) {
                if (position > 0) {
                    noOfDaysInSpecificMonth.add(days);
                }
                position++;
            }
        }
    }

    private void initializeStartOfWeekInSpecificMonth() {
        for (Map.Entry<Integer, int[]> map : startWeekDayMonthMap.entrySet()) {
            int[] daysArray = map.getValue();
            int position = 0;
            for (int days : daysArray) {
                if (position > 0) {
                    startOfWeekInSpecificMonth.add(days);
                }
                position++;
            }
        }
    }


    private void initializeNepaliDateListWithYear() {
        for (Map.Entry<Integer, String> entry : englishEquivalentNepaliYear.entrySet()) {
            int position = 0;
            while (position < 12) {
                nepaliYearWithMonth.add(entry.getValue() + "  " + getNepaliMonth(position));
                position++;
            }
        }
    }

    public String getTitle(int position) {
        return nepaliYearWithMonth.get(position);
    }

    private void initializeEnglishEquivalentNepaliDay() {
        englishEquivalentNepaliDay.put(1, "१");
        englishEquivalentNepaliDay.put(2, "२");
        englishEquivalentNepaliDay.put(3, "३");
        englishEquivalentNepaliDay.put(4, "४");
        englishEquivalentNepaliDay.put(5, "५");
        englishEquivalentNepaliDay.put(6, "६");
        englishEquivalentNepaliDay.put(7, "७");
        englishEquivalentNepaliDay.put(8, "८");
        englishEquivalentNepaliDay.put(9, "९");
        englishEquivalentNepaliDay.put(10, "१०");
        englishEquivalentNepaliDay.put(11, "११");
        englishEquivalentNepaliDay.put(12, "१२");
        englishEquivalentNepaliDay.put(13, "१३");
        englishEquivalentNepaliDay.put(14, "१४");
        englishEquivalentNepaliDay.put(15, "१५");
        englishEquivalentNepaliDay.put(16, "१६");
        englishEquivalentNepaliDay.put(17, "१७");
        englishEquivalentNepaliDay.put(18, "१८");
        englishEquivalentNepaliDay.put(19, "१९");
        englishEquivalentNepaliDay.put(20, "२०");
        englishEquivalentNepaliDay.put(21, "२१");
        englishEquivalentNepaliDay.put(22, "२२");
        englishEquivalentNepaliDay.put(23, "२३");
        englishEquivalentNepaliDay.put(24, "२४");
        englishEquivalentNepaliDay.put(25, "२५");
        englishEquivalentNepaliDay.put(26, "२६");
        englishEquivalentNepaliDay.put(27, "२७");
        englishEquivalentNepaliDay.put(28, "२८");
        englishEquivalentNepaliDay.put(29, "२९");
        englishEquivalentNepaliDay.put(30, "३०");
        englishEquivalentNepaliDay.put(31, "३१");
        englishEquivalentNepaliDay.put(32, "३२");
    }

    private void initializeEnglishEquivalentNepaliYear() {
        englishEquivalentNepaliYear.put(2000, "२०००");
        englishEquivalentNepaliYear.put(2001, "२००१");
        englishEquivalentNepaliYear.put(2002, "२००२");
        englishEquivalentNepaliYear.put(2003, "२००३");
        englishEquivalentNepaliYear.put(2004, "२००४");
        englishEquivalentNepaliYear.put(2005, "२००५");
        englishEquivalentNepaliYear.put(2006, "२००६");
        englishEquivalentNepaliYear.put(2007, "२००७");
        englishEquivalentNepaliYear.put(2008, "२००८");
        englishEquivalentNepaliYear.put(2009, "२००९");
        englishEquivalentNepaliYear.put(2010, "२०१०");
        englishEquivalentNepaliYear.put(2011, "२०११");
        englishEquivalentNepaliYear.put(2012, "२०१२");
        englishEquivalentNepaliYear.put(2013, "२०१३");
        englishEquivalentNepaliYear.put(2014, "२०१४");
        englishEquivalentNepaliYear.put(2015, "२०१५");
        englishEquivalentNepaliYear.put(2016, "२०१६");
        englishEquivalentNepaliYear.put(2017, "२०१७");
        englishEquivalentNepaliYear.put(2018, "२०१८");
        englishEquivalentNepaliYear.put(2019, "२०१९");
        englishEquivalentNepaliYear.put(2020, "२०२०");
        englishEquivalentNepaliYear.put(2021, "२०२१");
        englishEquivalentNepaliYear.put(2022, "२०२२");
        englishEquivalentNepaliYear.put(2023, "२०२३");
        englishEquivalentNepaliYear.put(2024, "२०२४");
        englishEquivalentNepaliYear.put(2025, "२०२५");
        englishEquivalentNepaliYear.put(2026, "२०२६");
        englishEquivalentNepaliYear.put(2027, "२०२७");
        englishEquivalentNepaliYear.put(2028, "२०२८");
        englishEquivalentNepaliYear.put(2029, "२०२९");
        englishEquivalentNepaliYear.put(2030, "२०३०");
        englishEquivalentNepaliYear.put(2031, "२०३१");
        englishEquivalentNepaliYear.put(2032, "२०३२");
        englishEquivalentNepaliYear.put(2033, "२०३३");
        englishEquivalentNepaliYear.put(2034, "२०३४");
        englishEquivalentNepaliYear.put(2035, "२०३५");
        englishEquivalentNepaliYear.put(2036, "२०३६");
        englishEquivalentNepaliYear.put(2037, "२०३७");
        englishEquivalentNepaliYear.put(2038, "२०३८ ");
        englishEquivalentNepaliYear.put(2039, "२०३९ ");
        englishEquivalentNepaliYear.put(2040, "२०४० ");
        englishEquivalentNepaliYear.put(2041, "२०४१ ");
        englishEquivalentNepaliYear.put(2042, "२०४२");
        englishEquivalentNepaliYear.put(2043, "२०४३");
        englishEquivalentNepaliYear.put(2044, "२०४४");
        englishEquivalentNepaliYear.put(2045, "२०४५");
        englishEquivalentNepaliYear.put(2046, "२०४६");
        englishEquivalentNepaliYear.put(2047, "२०४७");
        englishEquivalentNepaliYear.put(2048, "२०४८");
        englishEquivalentNepaliYear.put(2049, "२०४९");
        englishEquivalentNepaliYear.put(2050, "२०५०");
        englishEquivalentNepaliYear.put(2051, "२०५१");
        englishEquivalentNepaliYear.put(2052, "२०५२");
        englishEquivalentNepaliYear.put(2053, "२०५३");
        englishEquivalentNepaliYear.put(2054, "२०५४");
        englishEquivalentNepaliYear.put(2055, "२०५५");
        englishEquivalentNepaliYear.put(2056, "२०५६");
        englishEquivalentNepaliYear.put(2057, "२०५७");
        englishEquivalentNepaliYear.put(2058, "२०५८");
        englishEquivalentNepaliYear.put(2059, "२०५९");
        englishEquivalentNepaliYear.put(2060, "२०६०");
        englishEquivalentNepaliYear.put(2061, "२०६१");
        englishEquivalentNepaliYear.put(2062, "२०६२");
        englishEquivalentNepaliYear.put(2063, "२०६३");
        englishEquivalentNepaliYear.put(2064, "२०६४");
        englishEquivalentNepaliYear.put(2065, "२०६५");
        englishEquivalentNepaliYear.put(2066, "२०६६");
        englishEquivalentNepaliYear.put(2067, "२०६७");
        englishEquivalentNepaliYear.put(2068, "२०६८");
        englishEquivalentNepaliYear.put(2069, "२०६९");
        englishEquivalentNepaliYear.put(2070, "२०७०");
        englishEquivalentNepaliYear.put(2071, "२०७१");
        englishEquivalentNepaliYear.put(2072, "२०७२");
        englishEquivalentNepaliYear.put(2073, "२०७३");
        englishEquivalentNepaliYear.put(2074, "२०७४");
        englishEquivalentNepaliYear.put(2075, "२०७५");
        englishEquivalentNepaliYear.put(2076, "२०७६");
        englishEquivalentNepaliYear.put(2077, "२०७७");
        englishEquivalentNepaliYear.put(2078, "२०७८");
        englishEquivalentNepaliYear.put(2079, "२०७९");
        englishEquivalentNepaliYear.put(2080, "२०८०");
        englishEquivalentNepaliYear.put(2081, "२०८१");
        englishEquivalentNepaliYear.put(2082, "२०८२");
        englishEquivalentNepaliYear.put(2083, "२०८३");
        englishEquivalentNepaliYear.put(2084, "२०८४");
        englishEquivalentNepaliYear.put(2085, "२०८५");
        englishEquivalentNepaliYear.put(2086, "२०८६");
        englishEquivalentNepaliYear.put(2087, "२०८७");
        englishEquivalentNepaliYear.put(2088, "२०८८");
        englishEquivalentNepaliYear.put(2089, "२०८९");
        englishEquivalentNepaliYear.put(2090, "२०९०");
    }

    private void availableDateinBs() {
        daysInMonthMap.put(2000, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2001, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2002, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2003, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2004, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2005, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2006, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2007, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2008, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        daysInMonthMap.put(2009, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2010, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2011, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2012, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        daysInMonthMap.put(2013, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2014, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2015, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2016, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        daysInMonthMap.put(2017, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2018, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2019, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2020, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2021, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2022, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        daysInMonthMap.put(2023, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2024, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2025, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2026, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2027, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2028, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2029, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2030, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2031, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2032, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2033, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2034, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2035, new int[]{0, 30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        daysInMonthMap.put(2036, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2037, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2038, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2039, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        daysInMonthMap.put(2040, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2041, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2042, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2043, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        daysInMonthMap.put(2044, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2045, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2046, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2047, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2048, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2049, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        daysInMonthMap.put(2050, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2051, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2052, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2053, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        daysInMonthMap.put(2054, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2055, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2056, new int[]{0, 31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2057, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2058, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2059, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2060, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2061, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2062, new int[]{0, 30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2063, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2064, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2065, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2066, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31});
        daysInMonthMap.put(2067, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2068, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2069, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2070, new int[]{0, 31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30});
        daysInMonthMap.put(2071, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2072, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2073, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31});
        daysInMonthMap.put(2074, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2075, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2076, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        daysInMonthMap.put(2077, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31});
        daysInMonthMap.put(2078, new int[]{0, 31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2079, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30});
        daysInMonthMap.put(2080, new int[]{0, 31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30});
        daysInMonthMap.put(2081, new int[]{0, 31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2082, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2083, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2084, new int[]{0, 31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2085, new int[]{0, 31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2086, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2087, new int[]{0, 31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2088, new int[]{0, 30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2089, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});
        daysInMonthMap.put(2090, new int[]{0, 30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30});

        startWeekDayMonthMap.put(2000, new int[]{0, 4, 6, 3, 6, 3, 6, 1, 3, 5, 6, 1, 2});
        startWeekDayMonthMap.put(2001, new int[]{0, 5, 1, 4, 1, 4, 7, 3, 5, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2002, new int[]{0, 6, 2, 5, 2, 6, 2, 4, 6, 7, 2, 3, 5});
        startWeekDayMonthMap.put(2003, new int[]{0, 7, 3, 7, 3, 7, 3, 5, 7, 2, 3, 4, 6});
        startWeekDayMonthMap.put(2004, new int[]{0, 2, 4, 1, 4, 1, 4, 6, 1, 3, 4, 6, 7});
        startWeekDayMonthMap.put(2005, new int[]{0, 3, 6, 2, 6, 2, 5, 1, 3, 4, 6, 7, 2});
        startWeekDayMonthMap.put(2006, new int[]{0, 4, 7, 3, 7, 4, 7, 2, 4, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2007, new int[]{0, 5, 1, 5, 1, 5, 1, 3, 5, 7, 1, 2, 4});
        startWeekDayMonthMap.put(2008, new int[]{0, 7, 3, 6, 2, 6, 2, 5, 6, 1, 3, 4, 5});
        startWeekDayMonthMap.put(2009, new int[]{0, 1, 4, 7, 4, 7, 3, 6, 1, 2, 4, 5, 7});
        startWeekDayMonthMap.put(2010, new int[]{0, 2, 5, 1, 5, 2, 5, 7, 2, 3, 5, 6, 1});
        startWeekDayMonthMap.put(2011, new int[]{0, 3, 6, 3, 6, 3, 6, 1, 3, 5, 6, 7, 2});
        startWeekDayMonthMap.put(2012, new int[]{0, 5, 1, 4, 7, 4, 7, 3, 4, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2013, new int[]{0, 6, 2, 5, 2, 5, 1, 4, 6, 7, 2, 3, 5});
        startWeekDayMonthMap.put(2014, new int[]{0, 7, 3, 6, 3, 7, 3, 5, 7, 1, 3, 4, 6});
        startWeekDayMonthMap.put(2015, new int[]{0, 1, 4, 1, 4, 1, 4, 6, 1, 3, 4, 5, 7});
        startWeekDayMonthMap.put(2016, new int[]{0, 3, 6, 2, 5, 2, 5, 1, 2, 4, 6, 7, 2});
        startWeekDayMonthMap.put(2017, new int[]{0, 4, 7, 3, 7, 3, 6, 2, 4, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2018, new int[]{0, 5, 1, 5, 1, 5, 1, 3, 5, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2019, new int[]{0, 6, 2, 6, 2, 6, 2, 4, 6, 1, 2, 4, 5});
        startWeekDayMonthMap.put(2020, new int[]{0, 1, 4, 7, 3, 7, 3, 6, 1, 2, 4, 5, 7});
        startWeekDayMonthMap.put(2021, new int[]{0, 2, 5, 1, 5, 1, 4, 7, 2, 3, 5, 6, 1});
        startWeekDayMonthMap.put(2022, new int[]{0, 3, 6, 3, 6, 3, 6, 1, 3, 5, 6, 7, 2});
        startWeekDayMonthMap.put(2023, new int[]{0, 4, 7, 4, 7, 4, 7, 2, 4, 6, 7, 2, 3});
        startWeekDayMonthMap.put(2024, new int[]{0, 6, 2, 5, 1, 5, 1, 4, 6, 7, 2, 3, 5});
        startWeekDayMonthMap.put(2025, new int[]{0, 7, 3, 6, 3, 6, 2, 5, 7, 1, 3, 4, 6});
        startWeekDayMonthMap.put(2026, new int[]{0, 1, 4, 1, 4, 1, 4, 6, 1, 3, 4, 5, 7});
        startWeekDayMonthMap.put(2027, new int[]{0, 3, 5, 2, 5, 2, 5, 7, 2, 4, 5, 7, 1});
        startWeekDayMonthMap.put(2028, new int[]{0, 4, 7, 3, 7, 3, 6, 2, 4, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2029, new int[]{0, 5, 1, 4, 1, 4, 1, 3, 5, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2030, new int[]{0, 6, 2, 6, 2, 6, 2, 4, 6, 1, 2, 3, 5});
        startWeekDayMonthMap.put(2031, new int[]{0, 1, 3, 7, 3, 7, 3, 5, 7, 2, 3, 5, 6});
        startWeekDayMonthMap.put(2032, new int[]{0, 2, 5, 1, 5, 1, 4, 7, 2, 3, 5, 6, 1});
        startWeekDayMonthMap.put(2033, new int[]{0, 3, 6, 2, 6, 3, 6, 1, 3, 4, 6, 7, 2});
        startWeekDayMonthMap.put(2034, new int[]{0, 4, 7, 4, 7, 4, 7, 2, 4, 6, 7, 1, 3});
        startWeekDayMonthMap.put(2035, new int[]{0, 6, 1, 5, 1, 5, 1, 4, 5, 7, 2, 3, 4});
        startWeekDayMonthMap.put(2036, new int[]{0, 7, 3, 6, 3, 6, 2, 5, 7, 1, 3, 4, 6});
        startWeekDayMonthMap.put(2037, new int[]{0, 1, 4, 7, 4, 1, 4, 6, 1, 2, 4, 5, 7});
        startWeekDayMonthMap.put(2038, new int[]{0, 2, 5, 2, 5, 2, 5, 7, 2, 4, 5, 6, 1});
        startWeekDayMonthMap.put(2039, new int[]{0, 4, 7, 3, 6, 3, 6, 2, 3, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2040, new int[]{0, 5, 1, 4, 1, 4, 7, 3, 5, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2041, new int[]{0, 6, 2, 5, 2, 6, 2, 4, 6, 7, 2, 3, 5});
        startWeekDayMonthMap.put(2042, new int[]{0, 7, 3, 7, 3, 7, 3, 5, 7, 2, 3, 4, 6});
        startWeekDayMonthMap.put(2043, new int[]{0, 2, 5, 1, 4, 1, 4, 7, 1, 3, 5, 6, 1});
        startWeekDayMonthMap.put(2044, new int[]{0, 3, 6, 2, 6, 2, 5, 1, 3, 4, 6, 7, 2});
        startWeekDayMonthMap.put(2045, new int[]{0, 4, 7, 4, 7, 4, 7, 2, 4, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2046, new int[]{0, 5, 1, 5, 1, 5, 1, 3, 5, 7, 1, 2, 4});
        startWeekDayMonthMap.put(2047, new int[]{0, 7, 3, 6, 2, 6, 2, 5, 7, 1, 3, 4, 6});
        startWeekDayMonthMap.put(2048, new int[]{0, 1, 4, 7, 4, 7, 3, 6, 1, 2, 4, 5, 7});
        startWeekDayMonthMap.put(2049, new int[]{0, 2, 5, 2, 5, 2, 5, 7, 2, 4, 5, 6, 1});
        startWeekDayMonthMap.put(2050, new int[]{0, 3, 6, 3, 6, 3, 6, 1, 3, 5, 6, 1, 2});
        startWeekDayMonthMap.put(2051, new int[]{0, 5, 1, 4, 7, 4, 7, 3, 5, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2052, new int[]{0, 6, 2, 5, 2, 5, 1, 4, 6, 7, 2, 3, 5});
        startWeekDayMonthMap.put(2053, new int[]{0, 7, 3, 7, 3, 7, 3, 5, 7, 2, 3, 4, 6});
        startWeekDayMonthMap.put(2054, new int[]{0, 1, 4, 1, 4, 1, 4, 6, 1, 3, 4, 6, 7});
        startWeekDayMonthMap.put(2055, new int[]{0, 3, 6, 2, 6, 2, 5, 1, 3, 4, 6, 7, 2});
        startWeekDayMonthMap.put(2056, new int[]{0, 4, 7, 3, 7, 3, 7, 2, 4, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2057, new int[]{0, 5, 1, 5, 1, 5, 1, 3, 5, 7, 1, 2, 4});
        startWeekDayMonthMap.put(2058, new int[]{0, 7, 2, 6, 2, 6, 2, 4, 6, 1, 2, 4, 5});
        startWeekDayMonthMap.put(2059, new int[]{0, 1, 4, 7, 4, 7, 3, 6, 1, 2, 4, 5, 7});
        startWeekDayMonthMap.put(2060, new int[]{0, 2, 5, 1, 5, 2, 5, 7, 2, 3, 5, 6, 1});
        startWeekDayMonthMap.put(2061, new int[]{0, 3, 6, 3, 6, 3, 6, 1, 3, 5, 6, 7, 2});
        startWeekDayMonthMap.put(2062, new int[]{0, 5, 7, 4, 7, 4, 7, 3, 4, 6, 7, 2, 3});
        startWeekDayMonthMap.put(2063, new int[]{0, 6, 2, 5, 2, 5, 1, 4, 6, 7, 2, 3, 5});
        startWeekDayMonthMap.put(2064, new int[]{0, 7, 3, 6, 3, 7, 3, 5, 7, 1, 3, 4, 6});
        startWeekDayMonthMap.put(2065, new int[]{0, 1, 4, 1, 4, 1, 4, 6, 1, 3, 4, 5, 7});
        startWeekDayMonthMap.put(2066, new int[]{0, 3, 6, 2, 5, 2, 5, 1, 2, 4, 6, 7, 1});
        startWeekDayMonthMap.put(2067, new int[]{0, 4, 7, 3, 7, 3, 6, 2, 4, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2068, new int[]{0, 5, 1, 4, 1, 5, 1, 3, 5, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2069, new int[]{0, 6, 2, 6, 2, 6, 2, 4, 6, 1, 2, 3, 5});
        startWeekDayMonthMap.put(2070, new int[]{0, 1, 4, 7, 3, 7, 3, 6, 7, 2, 4, 5, 7});
        startWeekDayMonthMap.put(2071, new int[]{0, 2, 5, 1, 5, 1, 4, 7, 2, 3, 5, 6, 1});
        startWeekDayMonthMap.put(2072, new int[]{0, 3, 6, 3, 6, 3, 6, 1, 3, 4, 6, 7, 2});
        startWeekDayMonthMap.put(2073, new int[]{0, 4, 7, 4, 7, 4, 7, 2, 4, 6, 7, 1, 3});
        startWeekDayMonthMap.put(2074, new int[]{0, 6, 2, 5, 1, 5, 1, 4, 6, 7, 2, 3, 5});
        startWeekDayMonthMap.put(2075, new int[]{0, 7, 3, 6, 3, 6, 2, 5, 7, 1, 3, 4, 6});
        startWeekDayMonthMap.put(2076, new int[]{0, 1, 4, 1, 4, 1, 4, 6, 1, 3, 4, 5, 7});
        startWeekDayMonthMap.put(2077, new int[]{0, 2, 5, 2, 5, 2, 5, 7, 2, 4, 5, 7, 1});
        startWeekDayMonthMap.put(2078, new int[]{0, 4, 7, 3, 6, 3, 6, 2, 4, 5, 7, 1, 3});
        startWeekDayMonthMap.put(2079, new int[]{0, 5, 1, 4, 1, 4, 7, 3, 5, 6, 1, 2, 4});
        startWeekDayMonthMap.put(2080, new int[]{0, 6, 2, 6, 2, 6, 2, 4, 6, 1, 2, 3, 5});
        startWeekDayMonthMap.put(2081, new int[]{0, 7, 3, 6, 3, 7, 3, 5, 7, 2, 3, 5, 7});
        startWeekDayMonthMap.put(2082, new int[]{0, 2, 4, 1, 4, 1, 4, 6, 1, 3, 4, 6, 1});
        startWeekDayMonthMap.put(2083, new int[]{0, 3, 6, 2, 6, 2, 5, 7, 2, 4, 5, 7, 2});
        startWeekDayMonthMap.put(2084, new int[]{0, 4, 7, 3, 7, 3, 6, 1, 3, 5, 6, 1, 3});
        startWeekDayMonthMap.put(2085, new int[]{0, 5, 1, 5, 1, 5, 7, 3, 5, 7, 1, 3, 5});
        startWeekDayMonthMap.put(2086, new int[]{0, 7, 2, 6, 2, 6, 2, 4, 6, 1, 2, 4, 6});
        startWeekDayMonthMap.put(2087, new int[]{0, 1, 4, 7, 4, 7, 3, 6, 1, 3, 4, 6, 1});
        startWeekDayMonthMap.put(2088, new int[]{0, 3, 5, 1, 5, 2, 4, 7, 2, 4, 5, 7, 2});
        startWeekDayMonthMap.put(2089, new int[]{0, 4, 6, 3, 6, 3, 6, 1, 3, 5, 6, 1, 3});
        startWeekDayMonthMap.put(2090, new int[]{0, 5, 7, 4, 7, 4, 7, 2, 4, 6, 7, 2, 4});
    }

    /*convert nepali date into english date*/
    public DateModel getEnglishDate(int nepYY, int nepMM, int nepDD) {

        if (isNepDateInRange(nepYY, nepMM, nepDD)) {

            int startingEngYear = 1943;
            int startingEngMonth = 4;
            int startingEngDay = 14;

            int startingDayOfWeek = Calendar.WEDNESDAY; // 2000/1/1 is Wednesday

            int startingNepYear = 2000;
            int startingNepMonth = 1;
            int startingNepDay = 1;

            DateModel tempModel = new DateModel();

            int engYY, engMM, engDD;
            long totalNepDaysCount = 0;

            //count total no of days in nepali year from our starting range
            for (int i = startingNepYear; i < nepYY; i++) {
                for (int j = 1; j <= 12; j++) {
                    totalNepDaysCount = totalNepDaysCount + daysInMonthMap.get(i)[j];
                }
            }

            //Log.d("KG: BS->AD :toYDayCount", "" + totalNepDaysCount);

            //count total days in terms of month
            for (int j = startingNepMonth; j < nepMM; j++) {
                totalNepDaysCount = totalNepDaysCount + daysInMonthMap.get(nepYY)[j];
            }
            //Log.d("KG: BS->AD :toMDayCount", "" + totalNepDaysCount);

            //count total days in terms of date
            totalNepDaysCount += nepDD - startingNepDay;
            //Log.d("KG: BS->AD :toDDayCount", "" + totalNepDaysCount);

            int[] daysInMonth = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            int[] daysInMonthOfLeapYear = new int[]{0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            engYY = startingEngYear;
            engMM = startingEngMonth;
            engDD = startingEngDay;
            int endDayOfMonth = 0;
            int dayOfWeek = startingDayOfWeek;
            while (totalNepDaysCount != 0) {
                if (isLeapYear(engYY)) {
                    endDayOfMonth = daysInMonthOfLeapYear[engMM];
                } else {
                    endDayOfMonth = daysInMonth[engMM];
                }
                engDD++;
                dayOfWeek++;

                //Log.d("KG: BS->AD :engDD",""+engDD);
                if (engDD > endDayOfMonth) {
                    engMM++;
                    engDD = 1;
                    //Log.d("KG: BS->AD :engMM", "" + engMM);
                    if (engMM > 12) {
                        engYY++;
                        engMM = 1;
                    }
                }
                //Log.d("KG: BS->AD :engYY",""+engYY);
                if (dayOfWeek > 7) {
                    dayOfWeek = 1;
                }
                //Log.d("KG: BS->AD :totDayCount",""+totalNepDaysCount);
                totalNepDaysCount--;
            }

            tempModel.setDay(engDD);
            tempModel.setYear(engYY);
            tempModel.setMonth(engMM - 1);
            tempModel.setDayOfWeek(dayOfWeek);
            return tempModel;
        } else throw new IllegalArgumentException("Out of Range: Date is out of range to Convert");
    }

    /*convert english date into nepali date*/
    public DateModel getNepaliDate(int engYY, int engMM, int engDD) {

        if (isEngDateInRange(engYY, engMM, engDD)) {

            int startingEngYear = 1944;
            int startingEngMonth = 1;
            int startingEngDay = 1;

            int startingDayOfWeek = Calendar.SATURDAY; // 1944/1/1 is Saturday

            int startingNepYear = 2000;
            int startingNepMonth = 9;
            int startingNepDay = 17;

            int nepYY, nepMM, nepDD;
            int dayOfWeek = startingDayOfWeek;

            DateModel tempModel = new DateModel();

           /*calculate the days between two english date*/
            DateTime base = new DateTime(startingEngYear, startingEngMonth, startingEngDay, 0, 0); // June 20th, 2010
            DateTime newDate = new DateTime(engYY, engMM, engDD, 0, 0); // July 24th
            long totalEngDaysCount = Days.daysBetween(base, newDate).getDays();

            nepYY = startingNepYear;
            nepMM = startingNepMonth;
            nepDD = startingNepDay;

            while (totalEngDaysCount != 0) {
                int daysInMonth = daysInMonthMap.get(nepYY)[nepMM];
                nepDD++;
                if (nepDD > daysInMonth) {
                    nepMM++;
                    nepDD = 1;
                }
                if (nepMM > 12) {
                    nepYY++;
                    nepMM = 1;
                }
                dayOfWeek++;
                if (dayOfWeek > 7) {
                    dayOfWeek = 1;
                }
                totalEngDaysCount--;
            }
            tempModel.setYear(nepYY);
            tempModel.setMonth(nepMM - 1);
            tempModel.setDay(nepDD);
            tempModel.setDayOfWeek(dayOfWeek);

            return tempModel;
        } else throw new IllegalArgumentException("Out of Range: Date is out of range to Convert");
    }

    /*calculate whether english year is leap year or not*/
    private boolean isLeapYear(int year) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, year);
        return cal.getActualMaximum(Calendar.DAY_OF_YEAR) > 365;
    }

    /*returns nepali month*/
    private String getNepaliMonth(int month) {
        switch (month) {
            case 0:
                return context.getString(R.string.Baisakh);
            case 1:
                return context.getString(R.string.Jestha);
            case 2:
                return context.getString(R.string.Ashar);
            case 3:
                return context.getString(R.string.Shrawan);
            case 4:
                return context.getString(R.string.Bhadra);
            case 5:
                return context.getString(R.string.Ashoj);
            case 6:
                return context.getString(R.string.Kartik);
            case 7:
                return context.getString(R.string.Mangsir);
            case 8:
                return context.getString(R.string.Poush);
            case 9:
                return context.getString(R.string.Magh);
            case 10:
                return context.getString(R.string.Falgun);
            case 11:
                return context.getString(R.string.Chaitra);
        }
        return null;
    }

    public int getNepaliMonthIndex(String month) {
        if (month.equals(context.getString(R.string.Baisakh))) {
            return 1;
        }
        if (month.equals(context.getString(R.string.Jestha))) {
            return 2;
        }
        if (month.equals(context.getString(R.string.Ashar))) {
            return 3;
        }
        if (month.equals(context.getString(R.string.Shrawan))) {
            return 4;
        }
        if (month.equals(context.getString(R.string.Bhadra))) {
            return 5;
        }
        if (month.equals(context.getString(R.string.Ashoj))) {
            return 6;
        }
        if (month.equals(context.getString(R.string.Kartik))) {
            return 7;
        }
        if (month.equals(context.getString(R.string.Mangsir))) {
            return 8;
        }
        if (month.equals(context.getString(R.string.Poush))) {
            return 9;
        }
        if (month.equals(context.getString(R.string.Magh))) {
            return 10;
        }
        if (month.equals(context.getString(R.string.Falgun))) {
            return 11;
        }
        if (month.equals(context.getString(R.string.Chaitra))) {
            return 12;
        }
        return -1;
    }


    /*check if english date is in the range of conversion*/
    private static boolean isEngDateInRange(int yy, int mm, int dd) {
        return (yy >= 1944 && yy <= 2033) && (mm >= 1 && mm <= 12) && (dd >= 1 && dd <= 31);
    }

    /*check if nepali date is in the range of conversion*/
    private static boolean isNepDateInRange(int yy, int mm, int dd) {
        return (yy >= 2000 && yy <= 2090) && (mm >= 1 && mm <= 12) && (dd >= 1 && dd <= 32);
    }

    /*returns the day of week start for 1st nepali month*/
    public int getFirstWeekDayMonth(int yy, int mm) {
        return startWeekDayMonthMap.get(yy)[mm];
    }

    /*returns the no of days in a particular month of a nepali year*/
    public int noOfDaysInMonth(int yy, int mm) {
        return daysInMonthMap.get(yy)[mm];
    }

    public int getCalendarSize() {
        return daysInMonthMap.size() * 12;
    }

}
