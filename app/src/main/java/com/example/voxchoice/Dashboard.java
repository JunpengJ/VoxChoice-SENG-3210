package com.example.voxchoice;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voxchoice.model.DashboardAdapter;
import com.example.voxchoice.model.Poll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DashboardAdapter.ViewPollAdapter DashboardAdapter; // Adjusted adapter name
    private List<Poll> pollList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize RecyclerView
        recyclerView = findViewById(R.id.optionsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Initialize pollList
        pollList = new ArrayList<>();

        // Initialize adapter and set it to RecyclerView
        DashboardAdapter = new DashboardAdapter.ViewPollAdapter(this, pollList);
        recyclerView.setAdapter(DashboardAdapter);

        // Initialize Firebase
        database = FirebaseDatabase.getInstance("https://voxchoice-5d7c9-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("polls");

        // Retrieve poll data from Firebase
        retrievePollData();
    }

    private void retrievePollData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pollList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Poll poll = snapshot.getValue(Poll.class);
                    pollList.add(poll);
                }
                DashboardAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Log.e("FirebaseError", "Error getting data: " + databaseError.getMessage());
            }
        });
    }
}
