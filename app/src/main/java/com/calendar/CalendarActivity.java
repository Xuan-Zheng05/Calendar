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
        String storedHashMapString = sharedPreferences.getString(name, "");
        // checks if the file uses default value, if default, create new json file
        if (storedHashMapString.equals("")) {
            events = new HashMap<>();

            // if not blank, then use gson to convert the string value into a hashmap
        } else {
            java.lang.reflect.Type type = new TypeToken<HashMap<Long, String>>(){}.getType();
            events = gson.fromJson(storedHashMapString, type);
        }
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

        sharedPreferences = getSharedPreferences(name, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        // initializes MyCalendarEvent
        MyCalenderEvent calenderEvent = findViewById(R.id.calender_event);
        Date today = new Date();
        current_time = today.getTime();
        Calendar calendar = Calendar.getInstance();

        // code runs every time the user clicks on a new date
        calenderEvent.initCalderItemClickCallback(new CalenderDayClickListener() {
            @Override
            public void onGetDay(DayContainerModel dayContainerModel) {
                //Long t = dayContainerModel.getTimeInMillisecond();
                current_time = dayContainerModel.getTimeInMillisecond();

                String today_events = events.get(current_time);
                event_text.setText(today_events);

            }
        });

        // press update button code
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String event_text_string = event_text.getText().toString();
                Event event = new Event(current_time, "o", Color.RED);

                if (event_text_string.trim().length() > 0) {
                    calenderEvent.addEvent(event);
                    events.put(current_time, event_text_string);
                } else {
                    calenderEvent.removeEvent(event);
                    events.remove(current_time);
                }

                // storing the new events
                Gson gson = new Gson();

                // change Hashmap to String
                String events_string = gson.toJson(events);

                // put String of Hashmap into shared preferences
                editor.putString(name, events_string);
                editor.apply();
            }
        });
    }
}