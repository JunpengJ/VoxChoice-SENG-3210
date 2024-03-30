package com.example.voxchoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voxchoice.interfaces.OptionAdapter;
import com.example.voxchoice.model.Poll;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VoteActivity extends AppCompatActivity {
    private String pollTitle;
    private RecyclerView optionsRecyclerView;
    private OptionAdapter optionAdapter;
    private TextView questionTextView;
    private Poll poll;

    private DatabaseReference pollReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        // Retrieve pollTitle from intent extras
        pollTitle = getIntent().getStringExtra("pollTitle");

        questionTextView = findViewById(R.id.questionTextView);
        optionsRecyclerView = findViewById(R.id.optionsRecyclerView);
        optionsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        pollReference = FirebaseDatabase.getInstance().getReference("polls");
        pollReference.orderByChild("title").equalTo(pollTitle).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    poll = snapshot.getValue(Poll.class);
                    if (poll != null) {
                        displayPoll();
                        return;
                    }
                }
                // Handle error: Poll not found
                Toast.makeText(VoteActivity.this, "Poll not found", Toast.LENGTH_SHORT).show();
                finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
                Toast.makeText(VoteActivity.this, "Error: " + databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void voteForOption(int position) {
        if (poll != null && position >= 0 && position < poll.getOptions().size()) {
            DatabaseReference pollNodeReference = pollReference.child(pollTitle);
            ArrayList<Integer> votes = new ArrayList<>(poll.getVotes());
            votes.set(position, votes.get(position) + 1);
            pollNodeReference.child("votes").setValue(votes);

            Toast.makeText(this, "Voted for: " + poll.getOptions().get(position), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "An error occurred", Toast.LENGTH_SHORT).show();
        }
    }

    private void displayPoll() {
        questionTextView.setText(poll.getQuestion());
        optionAdapter = new OptionAdapter(poll.getOptions(), new OptionAdapter.OnOptionClickListener() {
            @Override
            public void onOptionClick(int position) {
                voteForOption(position);
            }
        });
        optionsRecyclerView.setAdapter(optionAdapter);
    }
}