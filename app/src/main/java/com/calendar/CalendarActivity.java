package com.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    private String name = "hl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);

//        TextView display_name = (TextView) findViewById(R.id.username_display);
//        Bundle extras = getIntent().getExtras();
//        if (extras != null){
//            name = extras.getString("name");
//        }
//        display_name.setText(name);
    }

    /* temp code for finding current date

    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1;
    int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    */
}