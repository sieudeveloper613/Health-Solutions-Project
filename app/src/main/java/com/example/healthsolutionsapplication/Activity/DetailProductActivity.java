package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.Model.RequestInterface;
import com.example.healthsolutionsapplication.Model.ServerResponse;
import com.example.healthsolutionsapplication.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;



public class DetailProductActivity extends AppCompatActivity {
    Product product;
    int ids;
    String product_name, product_price, product_category, product_branch, product_img, product_where, product_type;
    TextView tv_NameOfProduct, tv_PriceOfProduct, tv_catelogyProduct, tv_typeProduct, tv_originProduct, tv_BranchOfProduct;
    LinearLayout imgProduct;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        tv_NameOfProduct = findViewById(R.id.tv_NameOfProduct);
        tv_PriceOfProduct = findViewById(R.id.tv_PriceOfProduct);
        tv_catelogyProduct = findViewById(R.id.tv_catelogyProduct);
        tv_typeProduct = findViewById(R.id.tv_typeProduct);
        tv_originProduct = findViewById(R.id.tv_originProduct);
        tv_BranchOfProduct = findViewById(R.id.tv_BranchOfProduct);
        imgProduct = findViewById(R.id.img_detail);
        Intent intent = getIntent();
        ids = intent.getIntExtra("product_Ids", 0);
        product_name = intent.getStringExtra("product_name");
        product_price = intent.getStringExtra("product_price");
        product_category = intent.getStringExtra("product_category");
        product_branch = intent.getStringExtra("product_branch");
        product_img = intent.getStringExtra("product_img");
        product_where = intent.getStringExtra("product_where");
        product_type = intent.getStringExtra("product_type");
        if(ids != 0){
            getDetail();

        }
    }
    private void getDetail() {
        tv_NameOfProduct.setText(product_name);
        tv_PriceOfProduct.setText(String.valueOf(product_price));
        tv_catelogyProduct.setText(product_category);
        tv_typeProduct.setText(product_type);
        tv_originProduct.setText(product_where);
        tv_BranchOfProduct.setText(product_branch);
        Picasso.get().load(product_img).into(new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                imgProduct.setBackground(new BitmapDrawable(bitmap));
            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });


    }
}
