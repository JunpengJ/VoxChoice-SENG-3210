package com.example.voxchoice.interfaces;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.voxchoice.MainActivity;
import com.example.voxchoice.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;
import java.util.concurrent.TimeUnit;

public class FirebaseConnector {
    static String FIREBASE_URL = "https://voxchoice-5d7c9-default-rtdb.firebaseio.com/";
    static FirebaseDatabase database;
    static DatabaseReference databaseReference;

    public static void addUser(final String username, final String password, final String account_type){
        User newUser = new User(username, password, account_type);
        database = FirebaseDatabase.getInstance(FIREBASE_URL);
        databaseReference = database.getReference("users");
        databaseReference.child(newUser.toString()).push().setValue(newUser); // "push()" automatically generates a key for entry
//        TODO: If successful, toast "Registration successful"; else "Registration failed"
    }

    public interface LoginCallback {
        /*
        LoginCallback is needed because the "databaseReference.child(newUser.toString()).get().addOnCompleteListener(new On..."
        function takes time to access the database, but the program keeps running. The "callback" fixes this because a result is
        only returned (using "callback.onLoginResult") once it has heard back from the database. In other words, these functions
        run concurrently with the Firebase database, so you need to make sure the database has returned a result before you return
        from the outer function.
        */
        void onLoginResult(String accountType);
    }

    public static void checkLogin(final String username, final String password, final LoginCallback callback){
        final String[] accountType = {"NONE"};
        final int[] numOfLoops = {0};
        User[] userType = {
                new User(username, password, "ADMIN"),
                new User(username, password, "VOTER")
        };
        database = FirebaseDatabase.getInstance(FIREBASE_URL);
        databaseReference = database.getReference("users");

        for (User newUser : userType) {
            databaseReference.child(newUser.toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DataSnapshot> user) {
                    if (!user.isSuccessful()) {
                        Log.e("firebase", "Error getting data", user.getException());
                    } else {
                        Log.d("firebase", String.valueOf(user.getResult().getValue()));
                        DataSnapshot snapshot = user.getResult();
                        if (snapshot.exists()) {
                            for (DataSnapshot userSnapshot : snapshot.getChildren()) {
                                User foundUser = userSnapshot.getValue(User.class);
                                if (foundUser != null) {
//                                    System.out.println(foundUser.getAccount_type());
                                    callback.onLoginResult(newUser.account_type);
                                    return;
                                } else if (numOfLoops[0] == 2) {
                                    callback.onLoginResult("Account Not Found");
                                }
                            }
                            numOfLoops[0]++;
                        }
                    }
                }
            });

        }

    }
}
