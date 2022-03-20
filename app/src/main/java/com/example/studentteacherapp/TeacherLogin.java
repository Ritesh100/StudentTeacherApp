package com.example.studentteacherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class TeacherLogin extends AppCompatActivity {
    Button loginteacher, forgetpassword,teachersignup;
    TextInputLayout teacherpassword,teachernumber;
    DatabaseReference reference;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        teachersignup = findViewById(R.id.teachersignup);
        forgetpassword = findViewById(R.id.forgetpassword);
        loginteacher = findViewById(R.id.loginteacher);
        teachernumber = findViewById(R.id.number);
        teacherpassword = findViewById(R.id.teacherpassword);

//signup ko laagi
         teachersignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();
            }

            private void signup() {
                Intent signupIntent = new Intent(getApplicationContext(), teacherSignupform.class);
                startActivity(signupIntent);
            }
        });

        //signin ko laagi
        loginteacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                loginUser();
            }
        });

    }
    private Boolean validatenumber() {
        String val = teachernumber.getEditText().getText().toString();
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            teachernumber.setError("Field cannot be empty");
            return false;
        }
        else{
            teachernumber.setError(null);
            teachernumber.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val = teacherpassword.getEditText().getText().toString();
        if(val.isEmpty()){
            teacherpassword.setError("Field cannot be empty");
            return false;
        }
        else{
            teacherpassword.setError(null);
            teacherpassword.setErrorEnabled(false);
            return true;
        }

    }

    public void loginUser(){
        //validate login Info
        if(!validatenumber() | !validatePassword()){
            return;

        }
        else{
            //fetech the data from firebase
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUseremail = teachernumber.getEditText().getText().toString().trim();
        String userEnteredpassword = teacherpassword.getEditText().getText().toString().trim();
        rootNode = FirebaseDatabase.getInstance("https://studentteacherapp-7b78d-default-rtdb.firebaseio.com/");

        DatabaseReference reference = rootNode.getReference("teacher");

        Query checkUser = reference.orderByChild("contact").equalTo(userEnteredUseremail);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    teachernumber.setError(null);
                    teachernumber.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUseremail).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredpassword)){

                        teacherpassword.setError(null);
                        teacherpassword.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredUseremail).child("name").getValue(String.class);
                        String addressFromDB = snapshot.child(userEnteredUseremail).child("address").getValue(String.class);
                        String contactFromDB = snapshot.child(userEnteredUseremail).child("contact").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUseremail).child("email").getValue(String.class);

                        Intent intent = new Intent(getApplicationContext(),teacherdashboard.class);

                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("contact", contactFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);


                    }else{
                        teacherpassword.setError("Wrong Password");
                        teacherpassword.requestFocus();

                    }
                } else{
                    teachernumber.setError("no such user exist");
                    teachernumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No file found", Toast.LENGTH_SHORT).show();

            }
        });

    }

}