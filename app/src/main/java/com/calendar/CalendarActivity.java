/**
 * Name: Xuan & Yifei
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
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.helper.TimeUtil;
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
    MyCalenderEvent calenderEvent;
    private String name = "";
    private long current_time;
    private EditText event_text;
    private Button update;

    // initialized shared preferences
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    // gets the usernames and password of the user into a hashmap
    @Override
    protected void onStart() {
        super.onStart();
        Gson gson = new Gson();

        // gets the string using shared preferences
        // note: this takes from "name"
        String storedHashMapString = sharedPreferences.getString(name, "");

        // checks if the file uses default value, if default, create new json file and
        // saves to events
        if (storedHashMapString.equals("")) {
            events = new HashMap<>();

            // if not blank, then use gson to convert the string value into a hashmap
        } else {
            java.lang.reflect.Type type = new TypeToken<HashMap<Long, String>>(){}.getType();
            events = gson.fromJson(storedHashMapString, type);
        }

        // loops through all events of the user and initializes them on the calendar
        for (Long key : events.keySet()) {
            Event event = new Event(key, "o", Color.RED);
            calenderEvent.addEvent(event);
        }
        calenderEvent.refresh();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        update = findViewById(R.id.update);
        event_text = findViewById(R.id.event_text);

        // sets the top text to display current username
        TextView display_name = findViewById(R.id.username_display);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
        }
        display_name.setText(name);

        // initializes shared preferences and editor
        sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // initializes MyCalendarEvent
        calenderEvent = findViewById(R.id.calender_event);
        Date today = new Date();

        // make variable for current time
        current_time = today.getTime();
        Calendar calendar = Calendar.getInstance();

        // code runs every time the user clicks on a new date
        calenderEvent.initCalderItemClickCallback(new CalenderDayClickListener() {
            @Override
            public void onGetDay(DayContainerModel dayContainerModel) {

                // time for the date the user has selected
                current_time = dayContainerModel.getTimeInMillisecond();

                // String today_events value is from events hashmap
                // key is current_time and gets value
                String today_events = events.get(current_time);

                // set events of the selected date
                event_text.setText(today_events);
            }
        });

        // user presses update button
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // text of the events
                String event_text_string = event_text.getText().toString();

                // initializes event for the current time
                Event event = new Event(current_time, "o", Color.RED);

                // checks if the text length is larger than 0
                if (event_text_string.trim().length() > 0) {

                    // add event to calendar
                    calenderEvent.addEvent(event);

                    // add event to events hashmap
                    events.put(current_time, event_text_string);

                } else {
                    // length of text is 0, user has deleted event

                    // remove event from calendar
                    calenderEvent.removeEvent(event);

                    // remove event from events hashmap
                    events.remove(current_time);
                }

                // storing the new events
                Gson gson = new Gson();

                // change Hashmap to String
                String events_string = gson.toJson(events);

                // put String of Hashmap into shared preferences
                editor.putString(name, events_string);
                editor.apply();
                calenderEvent.refresh();
            }
        });
    }
}