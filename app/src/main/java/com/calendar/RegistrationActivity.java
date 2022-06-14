package com.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;
    private HashMap<String, String> users = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);


    }

    // method to switch to calendar page
    // also saves the username to transfer to CalendarActivity
    private void switchToCalendar() {
        Intent switchActivityIntent = new Intent(this, CalendarActivity.class);
        switchActivityIntent.putExtra("name", username.getText().toString());
        startActivity(switchActivityIntent);
    }
}