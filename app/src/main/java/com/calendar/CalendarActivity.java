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
import android.widget.EditText;
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
    private HashMap<Long, String> events = new HashMap<>();
    private String name = "";
    private long current_time;
    private EditText event_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        event_text = findViewById(R.id.event_text);
        // sets the top text to display current username
        TextView display_name = findViewById(R.id.username_display);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
        }
        display_name.setText(name);

        // initializes MyCalendarEvent
        MyCalenderEvent calenderEvent = findViewById(R.id.calender_event);
        Calendar calendar = Calendar.getInstance();

        // code runs every time the user clicks on a new date
        calenderEvent.initCalderItemClickCallback(new CalenderDayClickListener() {
            @Override
            public void onGetDay(DayContainerModel dayContainerModel) {
                Toast.makeText(CalendarActivity.this, dayContainerModel.getDate(), Toast.LENGTH_SHORT).show();
                Event event = new Event(dayContainerModel.getTimeInMillisecond(), "o", Color.RED);
                calenderEvent.addEvent(event);

                String event_text_string = event_text.getText().toString();
                current_time = dayContainerModel.getTimeInMillisecond();
                events.put(current_time, event_text_string);

            }
        });
    }
}