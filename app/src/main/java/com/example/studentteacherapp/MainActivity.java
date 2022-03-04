package com.example.studentteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button student, teacher,admin,signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup = findViewById(R.id.signupbtn);
        student = findViewById(R.id.studentbtn);
        teacher = findViewById(R.id.teacherbtn);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }
        });

    }

    private void signup() {
        Intent signupIntent = new Intent(getApplicationContext(), signupscreen.class);
        startActivity(signupIntent);
    }
}