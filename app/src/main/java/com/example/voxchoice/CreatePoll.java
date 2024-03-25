package com.example.voxchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class CreatePoll extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_poll);

        Button button_add_option = (Button) findViewById(R.id.button_add_option);
        Button button_create_poll = (Button) findViewById(R.id.button_create_poll);


    }
}