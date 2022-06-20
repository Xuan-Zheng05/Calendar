/**
 * Name: Xuan
 * Date: 06/19/2022
 * Description: MyCalenderEvent inherits from CalenderEvent
 *              Used to demonstrate inheritance
 */

package com.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.widget.Button;

import androidx.annotation.Nullable;
import com.skyhope.eventcalenderlibrary.CalenderEvent;

import java.util.Calendar;

public class MyCalenderEvent extends CalenderEvent {
    private Button buttonPrevious, buttonNext;
    public MyCalenderEvent(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        buttonPrevious = findViewById(com.skyhope.eventcalenderlibrary.R.id.button_previous);
        buttonNext = findViewById(com.skyhope.eventcalenderlibrary.R.id.button_next);
        removeAll();
    }

    public MyCalenderEvent(Context context) {
        super(context);
        removeAll();
    }

    /**
     * remove all events from the calender
     */
    public void removeAll() {
        SharedPreferences sharedPreferences = getContext().getSharedPreferences("EventCalender", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public void refresh(){
        onClick(buttonPrevious);
        onClick(buttonNext);
    }
}
