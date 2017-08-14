package com.ingrails.nepalicalendar.interfaces.views.fragments;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ingrails.nepalicalendar.R;
import com.ingrails.nepalicalendar.interfaces.calendar.Converter;
import com.ingrails.nepalicalendar.interfaces.models.DateModel;
import com.ingrails.nepalicalendar.interfaces.utils.Constants;

/**
 * Created by gokarna on 8/7/17.
 * fragment for calendar
 */

public class CalendarFragment extends CalendarViewFragment implements ViewTreeObserver.OnGlobalLayoutListener {
    private RecyclerView recyclerView;
    private int noOffDays;
    private int weekStartIndex;
    private Converter converter;
    private LinearLayout parent;


    public void onUpdateView() {
        if (recyclerView != null)
            for (int i = 0; i < recyclerView.getChildCount(); i++) {
                LinearLayout linearLayout = (LinearLayout) recyclerView.getChildAt(i);
                TextView textView = linearLayout.findViewById(R.id.day);
                if (textView != null) {
                    textView.setTag("");
                    textView.setBackground(null);
                }
            }
        new CurrentDateSelectorTask().execute();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noOffDays = getArguments().getInt(Constants.NO_OF_DAYS, -1);
        weekStartIndex = getArguments().getInt(Constants.WEEK_START_INDEX, -1);
        converter = new Converter();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_view, container, false);
        initiliseView(view);
        initiliseView(view);
        initialiseListener();
        setUpRecyclerView();
        return view;
    }

    @Override
    protected void initiliseView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        parent = view.findViewById(R.id.parent);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    protected void initialiseListener() {
        parent.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }


    private void setUpRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 7);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        CalendarRecyclerViewAdapter calendarRecyclerViewAdapter = new CalendarRecyclerViewAdapter();
        recyclerView.setAdapter(calendarRecyclerViewAdapter);
    }

    @Override
    public void onGlobalLayout() {
        new CurrentDateSelectorTask().execute();
        CalendarFragment.this.parent.getViewTreeObserver().removeOnGlobalLayoutListener(this);
    }

    private class CurrentDateSelectorTask extends AsyncTask<Void, Void, Void> {
        private String nepaliDay = "";
        private String nepaliMonth = "";
        private String nepaliYear = "";
        private TextView title;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (getActivity() != null)
                title = getActivity().findViewById(R.id.month_name);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            if (getActivity() != null) {
                Converter converter = new Converter(getActivity());
                DateModel dateModel = converter.getCurrentNepaliDate();
                int year = dateModel.getYear();
                int month = dateModel.getMonth() + 1;
                int day = dateModel.getDay();
                nepaliMonth = converter.getNepaliMonthByIndex(month);
                nepaliYear = converter.getEnglishEquivalentNepaliYear(year);
                nepaliDay = CalendarFragment.this.converter.getEnglishEquivalentNepaliDay(day);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            if (getActivity() != null) {
                if (title != null && (!title.getText().toString().contains(nepaliMonth) || !title.getText().toString().contains(nepaliYear))) {
                    return;
                }
                for (int i = 0; i < recyclerView.getChildCount(); i++) {
                    LinearLayout linearLayout = (LinearLayout) recyclerView.getChildAt(i);
                    TextView textView = linearLayout.findViewById(R.id.day);
                    if (textView != null) {
                        if (textView.getText().toString().equals(nepaliDay)) {
                            textView.setTag("current_day_tag");
                            textView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.circle));
                        }
                    }
                }
            }
        }
    }

    private class CalendarRecyclerViewAdapter extends RecyclerView.Adapter {
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_CONTENT = 1;
        private final String[] weekDays = getResources().getStringArray(R.array.week_name);

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == TYPE_HEADER) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_header, parent, false);
                return new VHHeader(view);
            } else if (viewType == TYPE_CONTENT) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_content, parent, false);
                return new VHContent(view);
            }
            throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof VHHeader) {
                VHHeader vhHeader = (VHHeader) holder;
                vhHeader.week.setText(weekDays[position]);
                if (position != 0 && position % 6 == 0) {
                    vhHeader.week.setTextColor(Color.RED);
                }
            } else if (holder instanceof VHContent) {
                VHContent vhContent = (VHContent) holder;
                if (position == 13 || position == 20 || position == 27 || position == 34 || position == 41 || position == 48) {
                    vhContent.day.setTextColor(Color.RED);
                }
                if (position - 7 < noOffDays + (weekStartIndex - 1)) {
                    vhContent.day.setText(converter.getEnglishEquivalentNepaliDay(position - 5 - weekStartIndex));
                    vhContent.day.setTag(vhContent.day.getText().toString());
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (isPositionHeader(position)) {
                return TYPE_HEADER;
            }
            return TYPE_CONTENT;
        }

        private boolean isPositionHeader(int position) {
            return position < 7;
        }

        @Override
        public int getItemCount() {
            return 49;
        }


        class VHContent extends RecyclerView.ViewHolder {
            private TextView day;

            VHContent(View view) {
                super(view);
                day = view.findViewById(R.id.day);
                day.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        decorateView();
                    }
                });
            }

            private void decorateView() {
                if (!TextUtils.isEmpty(day.getText().toString())) {
                    for (int i = 0; i < recyclerView.getChildCount(); i++) {
                        LinearLayout linearLayout = (LinearLayout) recyclerView.getChildAt(i);
                        TextView textView = linearLayout.findViewById(R.id.day);
                        if (textView != null)
                            if (!TextUtils.isEmpty(textView.getText().toString())) {
                                if (i != getAdapterPosition() && textView.getTag() != null && !textView.getTag().toString().equals("current_day_tag")) {
                                    textView.setBackground(null);
                                } else {
                                    textView.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.circle));
                                }
                            }
                    }
                }
            }
        }

        class VHHeader extends RecyclerView.ViewHolder {
            private TextView week;

            VHHeader(View view) {
                super(view);
                week = view.findViewById(R.id.week);
            }
        }
    }
}
