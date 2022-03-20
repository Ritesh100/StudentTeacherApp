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

public class teacherSignupform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    Spinner teacherspinner;
    Button teacherloginbackbtn,go;
    TextInputLayout teachernamesignup, teacheraddresssignup,teacheremailsignup, teachernumbersignup, teacherpasswordsignup;
    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_signupform);

        teacherspinner = findViewById(R.id.teacherspinner);
        teacherloginbackbtn =findViewById(R.id.teacherloginbackbtn);
        teachernamesignup = findViewById(R.id.teachernamesignup);
        teacheraddresssignup = findViewById(R.id.teacheraddresssignup);
        teacheremailsignup = findViewById(R.id.teacheremailsignup);
        teachernumbersignup = findViewById(R.id.teachernumbersignup);
        teacherpasswordsignup = findViewById(R.id.teacherpasswordsignup);
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
                String name =  teachernamesignup.getEditText().getText().toString();
                String address = teacheraddresssignup.getEditText().getText().toString();
                String email = teacheremailsignup.getEditText().getText().toString();
                String contact = teachernumbersignup.getEditText().getText().toString();
                String password = teacherpasswordsignup.getEditText().getText().toString();
                //  String classes = studentspinner.getEditText().getText().toString();

                TeacherHelperClass helperClass = new TeacherHelperClass(name,address,email,contact,password );
                reference.child(contact).setValue(helperClass);

            }
        });

        //studentlogin btn intent work start from here..
        teacherloginbackbtn.setOnClickListener(new View.OnClickListener() {
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
        teacherspinner.setAdapter(adapter);

        teacherspinner.setOnItemSelectedListener(this);

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
        String val = teachernamesignup.getEditText().getText().toString();
        if(val.isEmpty()){
            teachernamesignup.setError("Field cannot be empty");
            return false;
        }
        else{
            teachernamesignup.setError(null);
            teachernamesignup.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean ValidateAddress(){
        String val = teacheraddresssignup.getEditText().getText().toString();
        if(val.isEmpty()){
            teacheraddresssignup.setError("Field cannot be empty");
            return false;
        }
        else{
            teacheraddresssignup.setError(null);
            teacheraddresssignup.setErrorEnabled(false);
            return true;
        }
    }
    private Boolean validateemail(){
        String val = teacheremailsignup.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            teacheremailsignup.setError("Field cannot be empty");
            return false;
        }else if(val.matches(emailPattern)){
            teacheremailsignup.setError("Invalid email address");
            return false;
        }

        else{
            teacheremailsignup.setError(null);
            return true;
        }

    }
    private Boolean validateContact(){
        String val = teachernumbersignup.getEditText().getText().toString();
        if(val.isEmpty()){
            teachernumbersignup.setError("Field cannot be empty");
            return false;
        }
        else{
            teachernumbersignup.setError(null);
            return true;
        }

    }
    private Boolean validatePassword(){
        String val = teacherpasswordsignup.getEditText().getText().toString();
        if(val.isEmpty()){
            teacherpasswordsignup.setError("Field cannot be empty");
            return false;
        }
        else{
            teacherpasswordsignup.setError(null);
            return true;
        }

    }
}