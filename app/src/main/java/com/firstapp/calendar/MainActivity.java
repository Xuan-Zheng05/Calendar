package com.firstapp.calendar;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView username;
    private TextView password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialized variables
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button log_in = findViewById(R.id.log_in);
        Button new_user = findViewById(R.id.new_user);

        // button press for Submission, checks username and password
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

    private void switchToRegistration() {
        Intent switchActivityIntent = new Intent(this, RegistrationActivity.class);
        startActivity(switchActivityIntent);
    }
}