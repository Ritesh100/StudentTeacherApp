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

public class studentsignupform extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
 Spinner spinner;
   Button studentloginbackbtn,go;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.studentsignupform);
        spinner = findViewById(R.id.studentspinner);
        studentloginbackbtn =findViewById(R.id.studentloginbackbtn);
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





}