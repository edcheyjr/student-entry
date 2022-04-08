package com.example.classproject;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;



public class MainActivity extends AppCompatActivity {
    // declare global variables
    private EditText userName;
    private TextView welcomeTextStudentActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button loginUser = findViewById(R.id.login_btn);
        final Button viewUsers = findViewById(R.id.view_entry_btn);
        userName = findViewById(R.id.name);
        loginUser.setOnClickListener(view -> {
            // check if the userName is not empty and if this  a string then start a new activity
            checkUser();

        });
        viewUsers.setOnClickListener(view -> {
            startActivity(new Intent(this, EntriesActivity.class));
        });

    }

    private void checkUser(){
        String userNameText = userName.getText().toString();
        if(userNameText.isEmpty()) {
            Toast.makeText(this, "please enter your name", Toast.LENGTH_SHORT).show();
        }
        else {
//            hence move it to the next view Student registration
            Toast.makeText(this, "welcome " + userNameText, Toast.LENGTH_SHORT).show();
            Intent studentRegistration = new Intent(this, StudentRegistration.class);
            startActivity(studentRegistration);
        }
    }
}