package com.example.studentteacherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class studentsignupform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
 Spinner spinner;
 Button studentloginbackbtn,go;
 TextInputLayout studentnamesignup, studentaddresssignup,studentemailsignup, studentnumbersignup, studentpasswordsignup;
 FirebaseDatabase rootNode;
 DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentsignupform);
        spinner = findViewById(R.id.studentspinner);
        studentloginbackbtn =findViewById(R.id.studentloginbackbtn);
        studentnamesignup = findViewById(R.id.studentnamesignup);
        studentaddresssignup = findViewById(R.id.studentaddresssignup);
        studentemailsignup = findViewById(R.id.studentemailsignup);
        studentnumbersignup = findViewById(R.id.studentnumbersignup);
        studentpasswordsignup = findViewById(R.id.studentpasswordsignup);
        go= findViewById(R.id.go);

 //save data in firebase for go button
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
               reference = rootNode.getReference("students");


                if(!ValidateName() | !ValidateAddress() | !validatePassword() | !validateContact() | !validatePassword()){
                    return;
                }else{
                    Toast.makeText(getApplicationContext(),"Account Created Sucessfully! Goback to signin", Toast.LENGTH_LONG).show();
                }
                //get all the values from textfields
                String name =  studentnamesignup.getEditText().getText().toString();
                String address = studentaddresssignup.getEditText().getText().toString();
                String email = studentemailsignup.getEditText().getText().toString();
                String contact = studentnumbersignup.getEditText().getText().toString();
                String password = studentpasswordsignup.getEditText().getText().toString();
                //  String classes = studentspinner.getEditText().getText().toString();

                studentHelperClass helperClass = new studentHelperClass(name,address,email,contact,password );
                reference.child(contact).setValue(helperClass);

            }
        });

        //studentlogin btn intent work start from here..
        studentloginbackbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                siginback();
            }

            private void siginback() {
                Intent signinbackIntent = new Intent(getApplicationContext(),studentloginform.class);
                startActivity(signinbackIntent);
            }
        });

        go = findViewById(R.id.go);

        ArrayAdapter<CharSequence>adapter = ArrayAdapter.createFromResource(this,R.array.grades, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String choice = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }//spinner part end..

    //validation part start

private Boolean ValidateName(){
        String val = studentnamesignup.getEditText().getText().toString();
        if(val.isEmpty()){
            studentnamesignup.setError("Field cannot be empty");
            return false;
        }
        else{
            studentnamesignup.setError(null);
            studentnamesignup.setErrorEnabled(false);
            return true;
        }
}
    private Boolean ValidateAddress(){
        String val = studentaddresssignup.getEditText().getText().toString();
        if(val.isEmpty()){
            studentaddresssignup.setError("Field cannot be empty");
            return false;
        }
        else{
            studentaddresssignup.setError(null);
            studentaddresssignup.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateemail(){
        String val = studentemailsignup.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            studentemailsignup.setError("Field cannot be empty");
            return false;
        }else if(val.matches(emailPattern)){
            studentemailsignup.setError("Invalid email address");
            return false;
        }

        else{
            studentemailsignup.setError(null);
            return true;
        }

    }
    private Boolean validateContact(){
        String val = studentnumbersignup.getEditText().getText().toString();
        if(val.isEmpty()){
            studentnumbersignup.setError("Field cannot be empty");
            return false;
        }
        else{
            studentnumbersignup.setError(null);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val = studentpasswordsignup.getEditText().getText().toString();
        if(val.isEmpty()){
            studentpasswordsignup.setError("Field cannot be empty");
            return false;
        }
        else{
            studentpasswordsignup.setError(null);
            return true;
        }

    }
}