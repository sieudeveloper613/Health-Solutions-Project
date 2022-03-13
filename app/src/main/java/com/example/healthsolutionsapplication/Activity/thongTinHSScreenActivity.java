package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.healthsolutionsapplication.R;

public class thongTinHSScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_hsscreen);
        customActionBar();
    }
    private void customActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Địa Chỉ Nhận Hàng");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0088FF")));
        Drawable drawable= getResources().getDrawable(R.drawable.ic_arrow_back_24px);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }
}