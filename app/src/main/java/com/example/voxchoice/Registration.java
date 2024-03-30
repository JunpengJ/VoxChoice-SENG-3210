package com.example.voxchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voxchoice.interfaces.FirebaseConnector;

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
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    if (!username.getText().toString().isEmpty()) {
                        Toast.makeText(Registration.this, "Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    } else if (!password.getText().toString().isEmpty()) {
                        Toast.makeText(Registration.this, "Username Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(Registration.this, "Fields Cannot Be Blank", Toast.LENGTH_SHORT).show();
                    }
                } else {

                    String un = username.getText().toString();
                    String pass = password.getText().toString();
                    String accType = "VOTER";
                    if (admin_switch.isChecked()) accType = "ADMIN";

                    FirebaseConnector.addUser(un, pass, accType);

                    Toast.makeText(Registration.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
        register_back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}