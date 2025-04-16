package com.group08.finotes;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationBarView;
import com.group08.finotes.AppDataStructure.CloudDBModel;
import com.group08.finotes.AppDataStructure.LocalDBModel;

public class NavigationActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    HomeActivity homeFragment = new HomeActivity();
    CurrencyActivity currencyFragment = new CurrencyActivity();
    WalletActivity walletFragment = new WalletActivity();
    AccountActivity accountFragment = new AccountActivity();

    private CloudDBModel cloudModel;
    private LocalDBModel localDBModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_bar);
        cloudModel = (CloudDBModel)getIntent().getSerializableExtra("cloud");
        ImageButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent addBill = new Intent(NavigationActivity.this, Create_bill.class);
                startActivity(addBill);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment).commit();


        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home_view)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,homeFragment).commit();
                }
                else if (item.getItemId() == R.id.currency_view)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,currencyFragment).commit();
                }
                else if (item.getItemId() == R.id.wallet_view)
                {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,walletFragment).commit();
                    
                } else if (item.getItemId() == R.id.account_view) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,accountFragment).commit();
                }
                return true;
            }
        });

    }

}
