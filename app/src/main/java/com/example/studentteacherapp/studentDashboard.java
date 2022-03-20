package com.example.studentteacherapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class studentDashboard extends AppCompatActivity {
 RecyclerView recyclerView;
 DatabaseReference databaseReference;
    List<DocumentHelper> uploadList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_dashboard);

        viewAllFiles();


    }

    private void viewAllFiles() {
//     databaseReference = FirebaseDatabase.getInstance().getReference("documents");
//     databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
//         @Override
//         public void onDataChange(@NonNull DataSnapshot snapshot) {
//             if(snapshot.hasChildren()) {
//                 for(DataSnapshot data: snapshot.getChildren()) {
//                     DocumentHelper d = snapshot.getValue(DocumentHelper.class);
//                     uploadList.add(d);
//                     Log.d("Document", d.getUrl());
//                 }
//             }
//         }
//
//         @Override
//         public void onCancelled(@NonNull DatabaseError error) {
//
//         }
//     });
//    }
        List<String> list=new ArrayList<String>();
        //Adding elements in the List
        list.add("PDF1");
        list.add("PDF2");
        list.add("PDF3");
        list.add("PDF4");
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new RecyclerViewAdapter(list));
    }
}