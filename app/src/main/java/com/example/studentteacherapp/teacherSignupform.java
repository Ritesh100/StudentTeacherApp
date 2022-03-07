package com.example.studentteacherapp;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class teacherSignupform extends AppCompatActivity {

Button teacherloginbackbtn,go;
TextInputLayout teachernamesignup, teacheraddresssignup,teacheremailsignup,teachernumbersignup,teacherpasswordsignup;
Spinner teacherspinner;
FirebaseDatabase rootNode;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signupform);
        teacherloginbackbtn = findViewById(R.id.teacherloginbackbtn);
        go = findViewById(R.id.go);
        teachernamesignup=findViewById(R.id.teachernamesignup);
        teacheraddresssignup=findViewById(R.id.teacheraddresssignup);
        teacheremailsignup=findViewById(R.id.teacheremailsignup);
        teachernumbersignup=findViewById(R.id.teachernumbersignup);
        teacherpasswordsignup=findViewById(R.id.teacherpasswordsignup);

        //save data in forebase on buttonclick
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("teacher");

                //get all the values from textfields
                String name = teachernamesignup.getEditText().getText().toString();
                String address = teacheraddresssignup.getEditText().getText().toString();
                String email = teacheremailsignup.getEditText().getText().toString();
                String contact = teachernumbersignup.getEditText().getText().toString();
                String password = teacherpasswordsignup.getEditText().getText().toString();
              //  String classes = teacherspinner.getEditText().getText().toString();

                TeacherHelperClass helperClass = new TeacherHelperClass(name,address,email,contact,password );
                reference.child(contact).setValue(helperClass);
            }
        });

        teacherloginbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signinteacher();
            }
            private void signinteacher() {
                Intent sigininteacherIntent = new Intent(getApplicationContext(),TeacherLogin.class);
                startActivity(sigininteacherIntent);
            }
        });
    }
}