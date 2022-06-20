/**
 * Name: Xuan
 * Date: 06/08/2022
 * Description: Java program for the calendar page
 *              Loads previously set events and allows user to set new events
 */

package com.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Toast;

import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.listener.CalenderDayClickListener;
import com.skyhope.eventcalenderlibrary.model.DayContainerModel;
import com.skyhope.eventcalenderlibrary.model.Event;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    // initialized variables
    private HashMap<String, HashMap<String, String>> events = new HashMap<>();
    private String name = "";
    private static final String TAG = "CalenderTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        // clears the calendar of events
        SharedPreferences sharedPreferences = this.getSharedPreferences("EventCalender", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        // sets the top text to display current username
        TextView display_name = findViewById(R.id.username_display);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
        }
        display_name.setText(name);

        // initializes CalendarEvent
        MyCalenderEvent calenderEvent = findViewById(R.id.calender_event);
        Calendar calendar = Calendar.getInstance();
        calenderEvent.initCalderItemClickCallback(new CalenderDayClickListener() {
            @Override
            public void onGetDay(DayContainerModel dayContainerModel) {
                Toast.makeText(CalendarActivity.this, dayContainerModel.getDate(), Toast.LENGTH_SHORT).show();
                Event event = new Event(dayContainerModel.getTimeInMillisecond(), "o", Color.RED);
                calenderEvent.addEvent(event);
            }
        });
        //calendar.add(Calendar.DAY_OF_MONTH, 1);
    }
}