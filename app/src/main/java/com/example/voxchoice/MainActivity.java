package com.example.voxchoice;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.voxchoice.interfaces.FirebaseConnector;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView username = (TextView) findViewById(R.id.username_log_in);
        TextView password = (TextView) findViewById(R.id.password_log_in);
        Button log_in_button = (Button) findViewById(R.id.log_in_button);
        TextView register_button = (TextView) findViewById(R.id.register_clickable);

        log_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String un = username.getText().toString();
                String pass = password.getText().toString();

//                NOTE: Uncomment to test login offline
/*
                if (username.getText().toString().equals("admin") && password.getText().toString().equals("1234")) {
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, AdminMain.class));
                } else if(username.getText().toString().equals("user") && password.getText().toString().equals("1234")){
                    Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this, VoterMain.class));
                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
*/
                if (username.getText().toString().isEmpty() || password.getText().toString().isEmpty()) {
                    if (!username.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Password Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    } else if (!password.getText().toString().isEmpty()) {
                        Toast.makeText(MainActivity.this, "Username Cannot Be Empty", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(MainActivity.this, "Fields Cannot Be Blank", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    FirebaseConnector.checkLogin(un, pass, new FirebaseConnector.LoginCallback() {
                        @Override
                        public void onLoginResult(String accountType) {
                            String loginAttempt = accountType;
                            System.out.println(loginAttempt);
                            if (loginAttempt == "ADMIN") {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, AdminMain.class));
                                password.setText("");
                            } else if (loginAttempt == "VOTER") {
                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, VoterMain.class));
                                password.setText("");
                            } else {
                                Toast.makeText(MainActivity.this, "Wrong Username or Password", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Registration.class));
            }
        });
    }
}