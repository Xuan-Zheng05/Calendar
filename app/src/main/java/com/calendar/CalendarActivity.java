/**
 * Name: Xuan
 * Date: 06/08/2022
 * Description: Java program for the calendar page
 *              Loads previously set events and allows user to set new events
 */

package com.calendar;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.CalendarView;
import android.widget.TextView;
import com.skyhope.eventcalenderlibrary.CalenderEvent;
import com.skyhope.eventcalenderlibrary.listener.CalenderDayClickListener;
import com.skyhope.eventcalenderlibrary.model.DayContainerModel;
import com.skyhope.eventcalenderlibrary.model.Event;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    // initialized variables
    private String name = "";
    private static final String TAG = "CalenderTest";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

        TextView display_name = (TextView) findViewById(R.id.username_display);
        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString("name");
        }
        display_name.setText(name);

        CalenderEvent calenderEvent = findViewById(R.id.calender_event);
        Calendar calendar = Calendar.getInstance();
        Event event = new Event(calendar.getTimeInMillis(), "o", Color.RED);
        calenderEvent.addEvent(event);

        calenderEvent.initCalderItemClickCallback(new CalenderDayClickListener() {
            @Override
            public void onGetDay(DayContainerModel dayContainerModel) {
                Log.d(TAG, dayContainerModel.getDate());
            }
        });
        //calendar.add(Calendar.DAY_OF_MONTH, 1);

    }

}