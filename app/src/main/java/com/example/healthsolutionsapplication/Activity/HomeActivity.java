package com.example.healthsolutionsapplication.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthsolutionsapplication.Fragment.CartFragment;
import com.example.healthsolutionsapplication.Fragment.ChatFragment;
import com.example.healthsolutionsapplication.Fragment.CategoryFragment;
import com.example.healthsolutionsapplication.Fragment.PersonFragment;
import com.example.healthsolutionsapplication.Fragment.HomeFragment;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        changeStatusBarColor();
        loadFragment(new HomeFragment());
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                       HomeFragment homeFragment = new HomeFragment();
                       loadFragment(homeFragment);
                        return true;

                    case R.id.action_type:
                        CategoryFragment categoryFragment = new CategoryFragment();
                        loadFragment(categoryFragment);
                        return true;

                    case R.id.action_cart:
                        CartFragment cartFragment = new CartFragment();
                        loadFragment(cartFragment);
                        return true;

                    case R.id.action_mess:
                        ChatFragment chatFragment = new ChatFragment();
                        loadFragment(chatFragment);
                        return true;

                    case R.id.action_user:
                        PersonFragment personalFragment = new PersonFragment();
                        loadFragment(personalFragment);
                        return true;
                }
                return false;
            }
        });
    }

    private void loadFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }



}