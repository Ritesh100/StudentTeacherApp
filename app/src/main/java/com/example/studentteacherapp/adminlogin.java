package com.example.studentteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class adminlogin extends AppCompatActivity {
Button loginadmin;
TextInputLayout teacherpassword,teacheremail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        loginadmin = findViewById(R.id.loginadmin);
        teacherpassword = findViewById(R.id.teacherpassword);
        teacheremail= findViewById(R.id.teacheremail);
        loginadmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(teacheremail.getEditText().equals("admin")&&
                teacherpassword.getEditText().equals("admin")){
                    Toast.makeText(getApplicationContext(),"Redirecting....",Toast.LENGTH_SHORT).show();
                    adminlogin();

                }else
                {
                    Toast.makeText(getApplicationContext(),"Wrong Credentials",Toast.LENGTH_SHORT).show();
                }
            }
            private void adminlogin(){
                Intent intent = new Intent(getApplicationContext(), admindashboard.class);
                startActivity(intent);
            }
        });

    }
}