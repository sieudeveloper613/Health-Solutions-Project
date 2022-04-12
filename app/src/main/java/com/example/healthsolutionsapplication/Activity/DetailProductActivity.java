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
import androidx.cardview.widget.CardView;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.button.MaterialButton;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    String getNameProduct, getPriceProduct, getImageProduct, getCategoryProduct,
            getBranchProduct, getOriginProduct, getTypeProduct;
    TextView tvNameProduct, tvPriceProduct, tvCategoryProduct,
            tvTypeProduct, tvOriginProduct, tvBranchProduct;
    LinearLayout imgProduct;
    CardView cardBackPressed;
    MaterialButton btnAdProduct;


    // Object and Reference here
    Product product;
    int ids;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        // define id for view
            initView();

        // set method
        Intent intent = getIntent();
        ids = intent.getIntExtra("product_Ids", 0);

        if(ids != 0){
            getDetail();
        }
//        Intent intent = getIntent();
//        ids = intent.getIntExtra("product_Ids", 0);
//        getNameProduct = intent.getStringExtra("product_name");
//        getPriceProduct = String.valueOf(intent.getDoubleExtra("product_price", 0));
//        getCategoryProduct = intent.getStringExtra("product_category");
//        getBranchProduct = intent.getStringExtra("product_branch");
//        getImageProduct = intent.getStringExtra("product_img");
//        getOriginProduct = intent.getStringExtra("product_where");
//        getTypeProduct = intent.getStringExtra("product_type");

    }
    private void getDetail() {
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performGetIdProduct(ids);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals("success")){
                        if (response.body().getResult() == 1){
                            product = response.body().getProduct1();
                            tvNameProduct.setText(product.getName());
                            tvPriceProduct.setText(String.valueOf(product.getPrice()));
                            //tvCategoryProduct.setText(product.getCategoryId());
                            tvTypeProduct.setText(product.getTypeProduct());
                            tvOriginProduct.setText(product.getWhereProduct());
                            tvBranchProduct.setText(product.getBranchProduct());
                            Picasso.get().load(product.getImage()).into((Target) imgProduct);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });

    }

    private void initView(){
        tvNameProduct = findViewById(R.id.tv_NameOfProduct);
        tvPriceProduct = findViewById(R.id.tv_PriceOfProduct);
        tvCategoryProduct = findViewById(R.id.tv_categoryProduct);
        tvTypeProduct = findViewById(R.id.tv_typeProduct);
        tvOriginProduct = findViewById(R.id.tv_originProduct);
        tvBranchProduct = findViewById(R.id.tv_branchProduct);
        imgProduct = findViewById(R.id.img_detail);
        cardBackPressed = findViewById(R.id.card_backPressed);
        btnAdProduct = findViewById(R.id.btn_addProduct);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_backPressed:
                onBackPressed();
                finish();
            break;

            case R.id.btn_addProduct:
                Toast.makeText(DetailProductActivity.this, "updating...", Toast.LENGTH_SHORT).show();
                break;

            default:
                Toast.makeText(DetailProductActivity.this, "Wrong Clicked", Toast.LENGTH_SHORT).show();
        }
    }
}
