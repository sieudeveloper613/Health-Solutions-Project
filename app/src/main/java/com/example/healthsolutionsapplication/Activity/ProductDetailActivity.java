package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.healthsolutionsapplication.R;

public class ProductDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

//        readJSON("http://192.168.1.101/DUAN/config/getIdProduct.php?Ids=1");
    }


}