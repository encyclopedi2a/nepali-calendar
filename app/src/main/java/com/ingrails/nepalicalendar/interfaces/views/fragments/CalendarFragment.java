package com.ingrails.nepalicalendar.interfaces.views.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ingrails.nepalicalendar.R;
import com.ingrails.nepalicalendar.interfaces.converter.Converter;

/**
 * Created by gokarna on 8/7/17.
 * fragment for calendar
 */

public class CalendarFragment extends CalendarViewFragment {
    private RecyclerView recyclerView;
    private int noOffDays;
    private int weekStartIndex;
    private Converter converter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        noOffDays = getArguments().getInt("no_of_days", -1);
        weekStartIndex = getArguments().getInt("week_start_index", -1);
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
    }

    @Override
    protected void initialiseListener() {

    }

    private void setUpRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 7);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setHasFixedSize(true);
        CalendarRecyclerViewAdapter calendarRecyclerViewAdapter = new CalendarRecyclerViewAdapter();
        recyclerView.setAdapter(calendarRecyclerViewAdapter);
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
            } else if (holder instanceof VHContent) {
                VHContent vhContent = (VHContent) holder;
                if (position - 7 < noOffDays + (weekStartIndex - 1)) {
                    vhContent.day.setText(converter.getEnglishEquivalentNepaliDay(position - 5 - weekStartIndex));
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
                        day.setBackground(ContextCompat.getDrawable(getActivity(), R.drawable.circle));
                    }
                });
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
