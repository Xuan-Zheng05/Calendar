package com.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import com.skyhope.eventcalenderlibrary.CalenderEvent;

public class MyCalenderEvent extends CalenderEvent {

    public MyCalenderEvent(Context context) {
        super(context);
    }

    /**
     *
     */
    public void removeAll() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("EventCalender", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
