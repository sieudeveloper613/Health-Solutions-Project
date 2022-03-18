package com.example.healthsolutionsapplication.Activity;

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

import com.example.healthsolutionsapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;


public class ChangeInformationActivity extends AppCompatActivity implements View.OnClickListener{
    MaterialCardView cardFullName, cardDob, cardGender, cardPhoneNumber, cardAddress, cardEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);
        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Thay đổi thông tin");
        // define id for view
        cardFullName = findViewById(R.id.card_moveToFullName);
        cardDob = findViewById(R.id.card_moveToDob);
        cardGender = findViewById(R.id.card_moveToGender);
        cardPhoneNumber = findViewById(R.id.card_moveToPhoneNumber);
        cardAddress = findViewById(R.id.card_moveToAddress);
        cardEmail = findViewById(R.id.card_moveToEmail);

        // define event for view
        cardFullName.setOnClickListener(this);
        cardDob.setOnClickListener(this);
        cardGender.setOnClickListener(this);
        cardPhoneNumber.setOnClickListener(this);
        cardAddress.setOnClickListener(this);
        cardEmail.setOnClickListener(this);

    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_changeInfo);
        materialToolbar.setTitle(titleString);
        materialToolbar.setTitleCentered(true);
        materialToolbar.setTitleTextColor(Color.WHITE);
        materialToolbar.setBackgroundColor(Color.parseColor("#0088FF"));
        materialToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_moveToFullName:
                startActivity(new Intent(ChangeInformationActivity.this, FullNameActivity.class));
                break;

            case R.id.card_moveToDob:
                startActivity(new Intent(ChangeInformationActivity.this, DobActivity.class));
                break;

            case R.id.card_moveToGender:
                startActivity(new Intent(ChangeInformationActivity.this, GenderActivity.class));
                break;

            case R.id.card_moveToPhoneNumber:
                startActivity(new Intent(ChangeInformationActivity.this, PhoneNumberActivity.class));
                break;

            case R.id.card_moveToAddress:
                startActivity(new Intent(ChangeInformationActivity.this, DeliveryAddressActivity.class));
                break;

            case R.id.card_moveToEmail:
                startActivity(new Intent(ChangeInformationActivity.this, EmailActivity.class));
                break;
        }
    }
}