package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import com.example.healthsolutionsapplication.Fragment.ClientBillOfPersonFragment;
import com.example.healthsolutionsapplication.Fragment.InfoOfClientFragment;
import com.example.healthsolutionsapplication.Fragment.OtherOfPersonFragment;
import com.example.healthsolutionsapplication.R;

public class PersonalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal);

        // setting status bar and action bar
        changeStatusBarColor();
        customActionBar();

        // set up fragment in PersonActivity
        setUpAllFragment();
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
        actionBar.setTitle("Thông tin cá nhân");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0088FF")));

        //actionBar.hide();
        // hide actionBar if you want

        Drawable drawable= getResources().getDrawable(R.drawable.ic_arrow_back_24px);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setHomeAsUpIndicator(drawable);
    }

    private void setUpAllFragment(){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Client of Bill Fragment
        ClientBillOfPersonFragment clientBillOfPersonFragment = new ClientBillOfPersonFragment();
        transaction.replace(R.id.container_billOfClient, clientBillOfPersonFragment);


        // Info Of Client Fragment
        InfoOfClientFragment infoOfClientFragment = new InfoOfClientFragment();
        transaction.replace(R.id.container_infoOfClient, infoOfClientFragment);


        // Other Task of Person
        OtherOfPersonFragment otherOfPersonFragment = new OtherOfPersonFragment();
        transaction.replace(R.id.container_otherOfClient, otherOfPersonFragment);
        transaction.commit();
    }
}