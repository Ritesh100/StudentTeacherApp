package com.example.studentteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class studentloginform extends AppCompatActivity {
Button stsignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentloginform);
        stsignup = findViewById(R.id.stsignup);
        stsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }

            private void signup() {
                Intent signupIntent = new Intent(getApplicationContext(), studentsignupform.class);
                startActivity(signupIntent);
            }
        });
    }
}