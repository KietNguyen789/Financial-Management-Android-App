package com.group08.finotes.AppDataStructure;

import android.app.Activity;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.group08.finotes.LoginView;
import com.group08.finotes.MainActivity;
import com.group08.finotes.SignUpActivity;

import java.io.Serializable;

public class CloudDBModel implements Serializable {
    static private User currentUser;
    static private  CloudDBModel myCloudInstance;

    static{
        currentUser = null;
        myCloudInstance = new CloudDBModel();
    }
    public static CloudDBModel getCloudDB(){
        return myCloudInstance;
    }
    private CloudDBModel(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = database.getReference();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user != null) {
            dbRef.child("Users").child(user.getUid().toString().trim()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    currentUser = dataSnapshot.getValue(User.class);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    //Do something when error appear
                }
            });
        }
    }
    public boolean isLogin(){
        if(currentUser == null) {
            return false;
        }else{
            return true;
        }
    }

    public User getCurrentUser(){
        return currentUser;
    }

    public void signUpUser(String email, String password, User signedUser,Activity calledAct, Intent nextAct){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseDatabase instance = FirebaseDatabase.getInstance();
                            DatabaseReference dbRef = instance.getReference();
                            dbRef.child("Users").child(user.getUid().toString().trim()).setValue(signedUser);
                            currentUser = signedUser;
                            calledAct.startActivity(nextAct);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(calledAct,"Sign up failed",Toast.LENGTH_SHORT);
                        }
                    }
                });
    }
    public void isAccountExisting(String email, String password, Activity calledAct, Intent nextAct) {
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(calledAct, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(calledAct, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                           // Toast.makeText(calledAct, "Success.", Toast.LENGTH_SHORT).show();

                            // Sign in success, get the signed-in user's UID
                            FirebaseUser user = mAuth.getCurrentUser();

                            if (user != null) {
                                String userId = user.getUid();

                                // Retrieve user data from the database based on the UID
                                DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
                                DatabaseReference userRef = dbRef.child("Users").child(userId);

                                userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        if (snapshot.exists()) {
                                            // Data for the signed-in user is available in 'snapshot'
                                            currentUser = snapshot.getValue(User.class);
                                            // Assign the entire userData object to the signedUser object

                                            // Proceed with further actions after retrieving data
                                            calledAct.startActivity(nextAct);
                                        } else {
                                            // User data does not exist in the database
                                            // Handle accordingly
                                            Toast.makeText(calledAct, "User data does not exist.", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {
                                        // Handle the error, if any
                                        Toast.makeText(calledAct, "Error retrieving user data.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(calledAct, "Account not exist. Check Information", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }



}
