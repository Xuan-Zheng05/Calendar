/**
 * Name: Xuan
 * Date: 06/06/2022
 * Description: Java program for the registration page
 *              Allows user to create a new account with username and password
 */

package com.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    // initialized variables
    private TextView username;
    private TextView password;
    private TextView confirm_pass;
    private HashMap<String, String> users = null;

    // gets the usernames and password of the user into a hashmap
    @Override
    protected void onStart() {
        super.onStart();

        // initializes shared preferences and gson
        SharedPreferences sharedPreferences = getSharedPreferences("user_pass",MODE_PRIVATE);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        // initialized variables
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        confirm_pass = findViewById(R.id.confirm_password);
        Button submit = findViewById(R.id.submit);

        // user presses submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // initialized variables
                Gson gson = new Gson();
                String username_string = username.getText().toString();
                String pass_string = password.getText().toString();
                String confirm_pass_string = confirm_pass.getText().toString();

                // check if both passwords are the same
                if (pass_string.equals(confirm_pass_string)) {

                    // make sure password length is not 0
                    if (pass_string.length() >= 1) {

                        // check if username is already in use
                        if (!users.containsKey(username_string)) {
                            // put new user and password into map
                            users.put(username_string, pass_string);

                            // change Hashmap to String
                            String users_string = gson.toJson(users);

                            // initialized shared preferences
                            SharedPreferences sharedPreferences = getSharedPreferences("user_pass", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();

                            // put String of map into shared preferences
                            editor.putString("user_pass", users_string);
                            editor.apply();

                            // switch to calendar activity
                            switchToCalendar();

                        } else {
                            Toast.makeText(getApplicationContext(), R.string.unique_user, Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), R.string.enter_pass, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), R.string.match_pass, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public TextView getUser() {
        return this.username;
    }

    /*
     * method to switch to the calendar page
     * also sends the name of user to CalendarActivity
     */
    private void switchToCalendar() {
        Intent switchActivityIntent = new Intent(this, CalendarActivity.class);
        switchActivityIntent.putExtra("name", username.getText().toString());
        startActivity(switchActivityIntent);
    }
}