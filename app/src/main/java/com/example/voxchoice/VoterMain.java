package com.example.voxchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoterMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voter_main);

        Button button_view_polls = (Button) findViewById(R.id.button_view_polls);
        Button button_log_out = (Button) findViewById(R.id.button_log_out);

        button_view_polls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoterMain.this, Polls.class));
            }
        });
        button_log_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VoterMain.this, MainActivity.class));
            }
        });
    }
}