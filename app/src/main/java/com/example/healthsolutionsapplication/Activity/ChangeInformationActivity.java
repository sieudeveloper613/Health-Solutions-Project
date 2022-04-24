package com.example.healthsolutionsapplication.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;


public class ChangeInformationActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    MaterialCardView cardFullName, cardDob, cardGender, cardPhoneNumber, cardMainAddress, cardAddress, cardEmail;
    TextView tvShowEditName, tvShowEditEmail, tvShowEditPhone,
             tvShowEditGender, tvShowEditDob, tvShowEditAddress,
             tvShowEditMainAddress;


    // Object and Reference
    SharedPreferences sharedPref;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_information);
        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Thay đổi thông tin");

        // define id for view
        initView();

        // define method
        getEditName();
        getEditPhone();
        getEditDob();
        getEditGender();
        getEditEmail();
        getEditMainAddress();

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

    private void initView(){
        cardFullName = findViewById(R.id.card_moveToFullName);
        cardDob = findViewById(R.id.card_moveToDob);
        cardGender = findViewById(R.id.card_moveToGender);
        cardPhoneNumber = findViewById(R.id.card_moveToPhoneNumber);
        cardAddress = findViewById(R.id.card_moveToAddress);
        cardEmail = findViewById(R.id.card_moveToEmail);
        cardMainAddress = findViewById(R.id.card_moveToMainAddress);

        tvShowEditName = findViewById(R.id.tv_showEditName);
        tvShowEditEmail = findViewById(R.id.tv_showEditEmail);
        tvShowEditPhone = findViewById(R.id.tv_showEditPhoneNumber);
        tvShowEditGender = findViewById(R.id.tv_showEditGender);
        tvShowEditDob = findViewById(R.id.tv_showEditDob);
//        tvShowEditAddress = findViewById(R.id.tv_showEditAddress);
        tvShowEditMainAddress = findViewById(R.id.tv_showEditMainAddress);

        // define event for view
        cardFullName.setOnClickListener(this);
        cardDob.setOnClickListener(this);
        cardGender.setOnClickListener(this);
        cardPhoneNumber.setOnClickListener(this);
        cardAddress.setOnClickListener(this);
        cardEmail.setOnClickListener(this);
        cardMainAddress.setOnClickListener(this);

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

            case R.id.card_moveToMainAddress:
                startActivity(new Intent(ChangeInformationActivity.this, AddressActivity.class));
        }
    }

    private void getEditName(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String getNameCustomer = sharedPref.getString("getName", customer.getName());
        tvShowEditName.setText(getNameCustomer);
        tvShowEditName.setTextColor(Color.BLACK);
    }

    private void getEditDob(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String getDobCustomer = sharedPref.getString("getDob", customer.getDob());
        tvShowEditDob.setText(getDobCustomer);
        tvShowEditDob.setTextColor(Color.BLACK);
    }

    private void getEditGender(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        int getGenderCustomer = sharedPref.getInt("getGender", customer.getGender());

        if (getGenderCustomer == 0){
            tvShowEditGender.setText("Nam");
            tvShowEditGender.setTextColor(Color.BLACK);
        }else if(getGenderCustomer == 1){
            tvShowEditGender.setText("Nữ");
            tvShowEditGender.setTextColor(Color.BLACK);
        }else if (getGenderCustomer == 2){
            tvShowEditGender.setText("Khác");
            tvShowEditGender.setTextColor(Color.BLACK);
        }
    }

    private void getEditPhone(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String getPhoneNumberCustomer = sharedPref.getString("getPhone", customer.getPhone());
        tvShowEditPhone.setText(getPhoneNumberCustomer);
        tvShowEditPhone.setTextColor(Color.BLACK);
    }

    private void getEditMainAddress(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String getAddressCustomer = sharedPref.getString("getMainAddress", customer.getMainAddress());
        tvShowEditMainAddress.setText(getAddressCustomer);
        tvShowEditMainAddress.setTextColor(Color.BLACK);
    }

    private void getEditEmail(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String getEmailCustomer = sharedPref.getString("getEmail", customer.getEmail());
        tvShowEditEmail.setText(getEmailCustomer);
        tvShowEditEmail.setTextColor(Color.BLACK);
    }
}