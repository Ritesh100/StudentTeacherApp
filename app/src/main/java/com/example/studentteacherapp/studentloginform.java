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

public class studentloginform extends AppCompatActivity {
Button stsignup, forgetpassword,studentlogin;
TextInputLayout studentNumber,studentpassword;
    DatabaseReference reference;
    FirebaseDatabase rootNode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentloginform);
        stsignup = findViewById(R.id.stsignup);
        forgetpassword = findViewById(R.id.forgetpassword);
        studentlogin = findViewById(R.id.studentlogin);
        studentNumber = findViewById(R.id.studentNumber);
        studentpassword = findViewById(R.id.studentpassword);

//signup ko laagi
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

        //signin ko laagi
        studentlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

    }
    private Boolean validateemail(){
        String val = studentNumber.getEditText().getText().toString();
//        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            studentNumber.setError("Field cannot be empty");
            return false;
        }
        else{
            studentNumber.setError(null);
            studentNumber.setErrorEnabled(false);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val = studentpassword.getEditText().getText().toString();
        if(val.isEmpty()){
            studentpassword.setError("Field cannot be empty");
            return false;
        }
        else{
            studentpassword.setError(null);
            studentpassword.setErrorEnabled(false);
            return true;
        }

    }

    public void loginUser(){
        //validate login Info
        if(!validateemail() | !validatePassword()){
            return;

        }
        else{
            //fetech the data from firebase
            isUser();
        }
    }

    private void isUser() {
        String userEnteredUseremail = studentNumber.getEditText().getText().toString().trim();
        String userEnteredstudentpassword = studentpassword.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("students");

        Query checkUser = reference.orderByChild("contact").equalTo(userEnteredUseremail);
        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    studentNumber.setError(null);
                    studentNumber.setErrorEnabled(false);

                    String passwordFromDB = snapshot.child(userEnteredUseremail).child("password").getValue(String.class);
                    if(passwordFromDB.equals(userEnteredstudentpassword)){

                        studentpassword.setError(null);
                        studentpassword.setErrorEnabled(false);

                        String nameFromDB = snapshot.child(userEnteredUseremail).child("name").getValue(String.class);
                        String addressFromDB = snapshot.child(userEnteredUseremail).child("address").getValue(String.class);
                        String contactFromDB = snapshot.child(userEnteredUseremail).child("contact").getValue(String.class);
                        String emailFromDB = snapshot.child(userEnteredUseremail).child("email").getValue(String.class);



                        Intent intent = new Intent(getApplicationContext(),studentDashboard.class);

                        intent.putExtra("email", emailFromDB);
                        intent.putExtra("name", nameFromDB);
                        intent.putExtra("address", addressFromDB);
                        intent.putExtra("contact", contactFromDB);
                        intent.putExtra("password", passwordFromDB);
                        startActivity(intent);
                    }else{
                        studentpassword.setError("Wrong Password");
                        studentpassword.requestFocus();

                    }
                } else{
                    studentNumber.setError("no such user exist");
                    studentNumber.requestFocus();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No file found", Toast.LENGTH_SHORT).show();
            }
        });

    }

}