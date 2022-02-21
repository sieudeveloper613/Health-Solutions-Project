package com.example.healthsolutionsapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.card.MaterialCardView;


public class ChangeInformationActivity extends AppCompatActivity {
    MaterialCardView cardFullName, cardDob, cardGender, cardPhoneNumber, cardAddress, cardEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);

        // setting status bar and action bar
        changeStatusBarColor();
        customActionBar();

        // id and clicked Event
        moveToView();

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
        actionBar.setTitle("Thay đổi thông tin");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0088FF")));

        //actionBar.hide();
        // hide actionBar if you want

        Drawable drawable= getResources().getDrawable(R.drawable.ic_arrow_back_24px);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    private void moveToView(){
        cardFullName = findViewById(R.id.card_moveToFullName);
        cardDob = findViewById(R.id.card_moveToDob);
        cardGender = findViewById(R.id.card_moveToGender);
        cardPhoneNumber = findViewById(R.id.card_moveToPhoneNumber);
        cardAddress = findViewById(R.id.card_moveToAddress);
        cardEmail = findViewById(R.id.card_moveToEmail);

        cardFullName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeInformationActivity.this, FullNameActivity.class));
            }
        });

        cardDob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeInformationActivity.this, DobActivity.class));
            }
        });

        cardGender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeInformationActivity.this, GenderActivity.class));
            }
        });

        cardPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeInformationActivity.this, PhoneNumberActivity.class));
            }
        });
        // this is not my task...
        cardAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(ChangeInformationActivity.this, null.class));
                Toast.makeText(getApplicationContext(), "No Available Activity", Toast.LENGTH_SHORT).show();
            }
        });

        cardEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ChangeInformationActivity.this, EmailActivity.class));
            }
        });


    }
}