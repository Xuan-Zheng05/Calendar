package com.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import java.util.Calendar;
import java.util.TimeZone;

public class CalendarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
    }

    /* temp code for finding current date

    Calendar calendar = Calendar.getInstance(TimeZone.getDefault());

    int currentYear = calendar.get(Calendar.YEAR);
    int currentMonth = calendar.get(Calendar.MONTH) + 1;
    int currentDay = calendar.get(Calendar.DAY_OF_MONTH);
    */
}