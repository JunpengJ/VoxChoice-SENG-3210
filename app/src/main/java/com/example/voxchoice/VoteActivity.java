package com.example.voxchoice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voxchoice.model.OptionAdapter;
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
                        return; // Exit loop after finding the poll
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
    // Inside VoteActivity class

    private void voteForOption(int position) {
        // Ensure that poll object is not null
        if (poll != null && position >= 0 && position < poll.getOptions().size()) {
            // Update the vote count for the selected option in the database
            DatabaseReference pollNodeReference = pollReference.child(pollTitle); // Reference to the specific poll node
            ArrayList<Integer> votes = new ArrayList<>(poll.getVotes()); // Create a copy of the votes list
            votes.set(position, votes.get(position) + 1); // Increment the vote count for the selected option
            pollNodeReference.child("votes").setValue(votes); // Update the votes in the database

            // Inform the user that their vote has been counted
            Toast.makeText(this, "Voted for: " + poll.getOptions().get(position), Toast.LENGTH_SHORT).show();
            finish();
        } else {
            // Handle error: Invalid position or poll object is null
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