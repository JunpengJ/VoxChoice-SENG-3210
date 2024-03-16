package com.example.voxchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voxchoice.model.FirebaseConnector;

import java.util.Objects;

public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        TextView username = (TextView) findViewById(R.id.username_register);
        TextView password = (TextView) findViewById(R.id.password_register);
        Button register_button = (Button) findViewById(R.id.register_button);
        TextView register_back_button = (TextView) findViewById(R.id.register_back_button);
        Switch admin_switch = (Switch) findViewById(R.id.admin_switch);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                TODO: Check DB entry for username existing
//                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
//                    Toast.makeText(Registration.this, "Fields Cannot Be Blank", Toast.LENGTH_SHORT).show();
//                } else {

                String un = username.getText().toString();
                String pass = password.getText().toString();
                String accType = "VOTER";
                if (admin_switch.isChecked()) accType = "ADMIN";

                FirebaseConnector.addUser(un, pass, accType);
            }
        });
        register_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Registration.this, MainActivity.class));
            }
        });
    }
}