package com.example.voxchoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voxchoice.model.DashboardAdapter;
import com.example.voxchoice.model.Poll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DashboardItemActivity extends AppCompatActivity {
    private String pollTitle;
    private RecyclerView optionsRecyclerView;
    private DashboardAdapter dashboardAdapter;
    private TextView questionTextView;
    private Poll poll;

    private DatabaseReference pollReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard_item);

        // Retrieve pollTitle from intent extras
        pollTitle = getIntent().getStringExtra("pollTitle");

        questionTextView = findViewById(R.id.questionTextView);
        optionsRecyclerView = findViewById(R.id.dashboardItemRecyclerView);
        optionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        pollReference = FirebaseDatabase.getInstance().getReference("polls");
        pollReference.orderByChild("title").equalTo(pollTitle).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    poll = snapshot.getValue(Poll.class);
                    if (poll != null) {
                        displayPoll();
                        return; // Exit loop after finding the poll
                    }
                }
                // Handle error: Poll not found
                Toast.makeText(DashboardItemActivity.this, "Poll not found", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(DashboardItemActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void displayPoll() {
        questionTextView.setText(poll.getQuestion());
        dashboardAdapter = new DashboardAdapter(poll.getOptions(),poll.getVotes());
        optionsRecyclerView.setAdapter(dashboardAdapter);
    }
}
