package com.example.voxchoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.voxchoice.model.Poll;
import com.example.voxchoice.model.PollAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPolls extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference databaseReference;
    private RecyclerView recyclerView;
    private PollAdapter pollAdapter;
    private List<Poll> pollList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_polls);

//        Button btnAddTask = (Button) findViewById(R.id.btnAdd);
//        Button btnLoadTask = (Button) findViewById(R.id.btnLoad);

        recyclerView = findViewById(R.id.pollRecyclerView);
        pollAdapter = new PollAdapter(pollList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(pollAdapter);

//        btnLoadTask.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                loadTasks(v);
//            }
//        });
        View v = null;
        loadPolls(v);
    }
    public void loadPolls(View view) {
        database = FirebaseDatabase.getInstance("https://voxchoice-5d7c9-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("polls");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                pollList.clear();
                for (DataSnapshot taskSnapshot : dataSnapshot.getChildren()) {
                    Poll poll = taskSnapshot.getValue(Poll.class);
                    pollList.add(poll);
                }
                pollAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors
                Log.e("DatabaseError", databaseError.getMessage());
            }
        });
    }
}