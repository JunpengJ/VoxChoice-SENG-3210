package com.example.voxchoice.model;

import android.annotation.SuppressLint;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseConnector {
    static FirebaseDatabase database;
    static DatabaseReference databaseReference;
    public static void addUser(final String username, final String password, final String account_type){
        User newUser = new User(username, password, account_type);
//        database = FirebaseDatabase.getInstance("@strings/firebase_url"); // Not sure if this works
        database = FirebaseDatabase.getInstance("https://voxchoice-5d7c9-default-rtdb.firebaseio.com/");
        databaseReference = database.getReference("users");
//        databaseReference.child(newUser.toString()).setValue(newUser);
        databaseReference.child(newUser.toString()).push().setValue(newUser); // "push()" automatically generates a key for entry
    }
}
