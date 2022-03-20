package com.example.studentteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class signupscreen extends AppCompatActivity {
Button signin,student,teacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupscreen);
        signin = findViewById(R.id.signinbtn);
        student = findViewById(R.id.studentbtn);
        teacher = findViewById(R.id.teacherbtn);

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                studentsigup();
            }

            private void studentsigup() {
                Intent studetsignupIntent = new Intent(getApplicationContext(), studentsignupform.class);
                startActivity(studetsignupIntent);
            }
        });

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signin();
            }

            private void signin() {
                Intent signinIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(signinIntent);
            }
        });
        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                teachersignup();
            }

            private void teachersignup() {
                Intent signinIntent = new Intent(getApplicationContext(),teacherSignupform.class);
                startActivity(signinIntent);
            }
        });

    }
}