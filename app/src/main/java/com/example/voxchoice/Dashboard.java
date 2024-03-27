package com.example.voxchoice;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.voxchoice.model.DashboardAdapter;
import com.example.voxchoice.model.Poll;
import com.example.voxchoice.model.ViewPollAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Dashboard extends AppCompatActivity implements ViewPollAdapter.OnPollClickListener {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private ViewPollAdapter viewPollAdapter;
    private List<Poll> pollList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_polls);

        recyclerView = findViewById(R.id.viewPollRecyclerView);
        viewPollAdapter = new ViewPollAdapter(pollList, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(viewPollAdapter);

        View v = null;
        loadPolls(v);
    }

    public void loadPolls(View view) {
        database = FirebaseDatabase.getInstance("https://voxchoice-5d7c9-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("polls");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String value = "dataSnapshot: " + dataSnapshot.getValue().toString();
                    Log.d("firebase", value);
                    pollList.clear();
                    for (DataSnapshot pollSnapshot : dataSnapshot.getChildren()) {
                        Poll poll = pollSnapshot.getValue(Poll.class);
                        pollList.add(poll);
                    }
                    viewPollAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Log.e("DatabaseError", databaseError.getMessage());
            }
        });
    }

    @Override
    public void onPollClick(int position) {
        if (position >= 0 && position < pollList.size()) {
            Poll poll = pollList.get(position);
            if (poll != null) {
                Intent intent = new Intent(Dashboard.this, DashboardItemActivity.class);
                intent.putExtra("pollTitle", poll.getTitle());
                startActivity(intent);
            }
        }
    }
}