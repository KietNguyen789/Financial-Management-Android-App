package com.group08.finotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.gson.Gson;
import com.group08.finotes.AppDataStructure.CloudDBModel;

import java.io.Serializable;

public class EntryActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        CloudDBModel appCloud = CloudDBModel.getCloudDB();
        setContentView(R.layout.activity_entry);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                newActivity(appCloud);
            }
        },2000);
    }

    private void newActivity(CloudDBModel appCloud) {
        if(!appCloud.isLogin()) {
            //chua login
            Intent login = new Intent(this, LoginView.class);
            login.putExtra("cloud",(Serializable) appCloud);
            startActivity(login);
        }else{
            //Chuyen man hinh main
            Intent mainAct = new Intent(this, NavigationActivity.class);
            mainAct.putExtra("cloud",(Serializable) appCloud);
            startActivity(mainAct);

        }
    }
}