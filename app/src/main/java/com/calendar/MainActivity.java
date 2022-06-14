package com.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;
    private HashMap<String, String> users = null;


    // gets the usernames and password of the user into a hashmap
    @Override
    protected void onStart() {
        super.onStart();

        // initializes shared preferences
        SharedPreferences sharedPreferences = getSharedPreferences("user_pass",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();

        // gets the string using shared preferences
        String storedHashMapString = sharedPreferences.getString("user_pass", "");
        // checks if the file uses default value, if default, create new json file
        if (storedHashMapString.equals("")) {
            users = new HashMap<>();

            // if not blank, then use gson to convert the string value into a hashmap
        } else {
            java.lang.reflect.Type type = new TypeToken<HashMap<String, String>>(){}.getType();
            users = gson.fromJson(storedHashMapString, type);
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialized variables
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button log_in = findViewById(R.id.log_in);
        Button new_user = findViewById(R.id.new_user);

        // button press for Submission, checks username and password
        // if correct, switch to calendar view
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username_string = username.getText().toString();
                String pass_string = password.getText().toString();

                // checks if username is found
                if (users.containsKey(username_string)) {
                    // checks if password is correct
                    if (pass_string.equals(users.get(username_string))) {
                        // switch to calendar activity
                        switchToCalendar();
                    } else {
                        // print text if password is incorrect
                        Toast.makeText(getApplicationContext(),R.string.no_user, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // could not find username, print text
                    Toast.makeText(getApplicationContext(),R.string.incorrect_pass, Toast.LENGTH_SHORT).show();
                }
            }
        });

        // button press for New User?
        new_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchToRegistration();
            }
        });
    }

    public TextView getUser() {
        return this.username;
    }

    public TextView getPassword() {
        return this.password;
    }

    // method to switch to registration page
    private void switchToRegistration() {
        Intent switchActivityIntent = new Intent(this, RegistrationActivity.class);
        startActivity(switchActivityIntent);
    }

    // method to switch to calendar page
    // also saves the username to transfer to CalendarActivity
    private void switchToCalendar() {
        Intent switchActivityIntent = new Intent(this, CalendarActivity.class);
        switchActivityIntent.putExtra("name", username.getText().toString());
        startActivity(switchActivityIntent);
    }


}