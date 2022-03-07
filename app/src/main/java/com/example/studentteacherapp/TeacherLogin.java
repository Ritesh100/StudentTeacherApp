package com.example.studentteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TeacherLogin extends AppCompatActivity {
    Button teachersignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        teachersignup = findViewById(R.id.teachersignup);
        teachersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupteacher();
            }

            private void signupteacher() {
                Intent signupteacherIntent = new Intent(getApplicationContext(),teacherSignupform.class);
                startActivity(signupteacherIntent);
            }
        });
    }
}