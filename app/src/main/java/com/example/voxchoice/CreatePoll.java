package com.example.voxchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.voxchoice.model.Poll;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CreatePoll extends AppCompatActivity {
    protected List<String> options = new ArrayList<>();
    protected List<Integer> placeholderVotes = new ArrayList<>();;
    FirebaseDatabase database;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        Button button_add_option = findViewById(R.id.button_add_option);
        Button button_create_poll = findViewById(R.id.button_create_poll);

        button_add_option.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addOption();
            }
        });
        button_create_poll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createPoll(v);
            }
        });
    }

    public void addOption() {
        EditText optionText = findViewById(R.id.optionEditText);
        String option = optionText.getText().toString();
        options.add(option);
        placeholderVotes.add(0);
        optionText.setText("");
        Toast.makeText(CreatePoll.this, "Option Added", Toast.LENGTH_SHORT).show();
    }

    public void createPoll(View view) {
        EditText titleEditText = findViewById(R.id.titleEditText);
        EditText questionEditText = findViewById(R.id.questionEditText);

        String title = titleEditText.getText().toString();
        String question = questionEditText.getText().toString();
        if (!title.isEmpty() && !question.isEmpty() && !options.isEmpty() && !placeholderVotes.isEmpty()) {
            Poll newPoll = new Poll(title, question, options, placeholderVotes);

            // Get reference to the "polls" node in the database
            database = FirebaseDatabase.getInstance("https://voxchoice-5d7c9-default-rtdb.firebaseio.com/");
            databaseReference = database.getReference("polls");

            // Set the poll under the title node directly
            databaseReference.child(title).setValue(newPoll);

            Toast.makeText(CreatePoll.this, "Poll Created", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(CreatePoll.this, "Fields Cannot Be Empty", Toast.LENGTH_SHORT).show();
        }
    }
}