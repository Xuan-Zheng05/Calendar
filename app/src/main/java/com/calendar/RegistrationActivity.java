package com.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class RegistrationActivity extends AppCompatActivity {

    String temp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        SharedPreferences sharedPreferences = getPreferences(MODE_PRIVATE);
        temp = sharedPreferences.getString("aaa", "");
        System.out.println(temp);
        Log.d("abc", temp);
    }
}