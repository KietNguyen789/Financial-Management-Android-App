package com.group08.finotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.group08.finotes.AppDataStructure.CloudDBModel;
import com.group08.finotes.AppDataStructure.User;

public class LoginView extends AppCompatActivity {
    private TextView email, password, signUpConfirm;
    private Button signInButton;
    private CloudDBModel myCloudDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_view);
        initUi();
        initListener();
    }

    private void initUi(){
        email = findViewById(R.id.signin_email_textview);
        password = findViewById(R.id.signin_pass_textview);
        signInButton = findViewById(R.id.signin_button);
        signUpConfirm = findViewById(R.id.signin_signup_text_confirm);
        myCloudDB = (CloudDBModel) getIntent().getSerializableExtra("cloud");
    }

    private void initListener(){
        signUpConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup = new Intent(LoginView.this, SignUpActivity.class);
                signup.putExtra("cloud", myCloudDB);
                startActivity(signup);
            }
        });
        signInButton.setOnClickListener(view -> {

            String strEmail = email.getText().toString().trim();
            String strPassword = password.getText().toString().trim();

       //      Check if myCloudDB is initialized before using it
            if (myCloudDB != null) {
                Intent mainAct = new Intent(LoginView.this, NavigationActivity.class);
                mainAct.putExtra("cloud",myCloudDB);
                myCloudDB.isAccountExisting(strEmail,strPassword,LoginView.this,mainAct);
            } else {
                // Handle the situation where myCloudDB is not properly initialized
                Toast.makeText(LoginView.this, "CloudDBModel is not properly initialized.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
