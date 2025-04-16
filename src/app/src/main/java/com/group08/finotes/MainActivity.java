package com.group08.finotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;
import com.group08.finotes.AppDataStructure.Address;
import com.group08.finotes.AppDataStructure.CloudDBModel;
import com.group08.finotes.AppDataStructure.User;
import com.group08.finotes.AppDataStructure.Wallet;

import java.math.BigDecimal;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView emailText, nameText, readText;
    private ImageView imgAvatar;
    CloudDBModel myCloudDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);

        initUi();
        showUserInfo();

    }

    private void initUi(){
        emailText = findViewById(R.id.main_email);
        nameText = findViewById(R.id.main_name);
        imgAvatar = findViewById(R.id.main_avatar);
        readText = findViewById(R.id.main_read_data);
        myCloudDB = (CloudDBModel) getIntent().getSerializableExtra("cloud");
    }

    private void showUserInfo(){
        User user = myCloudDB.getCurrentUser();
        if(user == null){
            emailText.setText("No user has been found");
            return;
        }
        String name = user.getUserName();
        String email = user.getUserEmail();
        Uri photoURL = null;
        //System.out.println(email);
        if(email != null) {
            emailText.setText(email);
        }else{
            emailText.setText("Email ?");
        }
        if(name != null) {
            nameText.setText(name);
        }else{
            nameText.setText("Name ?");
        }
        Glide.with(this).load(photoURL).error(R.drawable.ic_default_avatar).into(imgAvatar);
    }

}