package com.example.healthsolutionsapplication.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.R;


public class EmailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        // setting status bar and action bar
        changeStatusBarColor();
        customActionBar();

    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Địa chỉ Email");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0088FF")));

        //actionBar.hide();
        // hide actionBar if you want

        Drawable drawable= getResources().getDrawable(R.drawable.ic_arrow_back_24px);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

}