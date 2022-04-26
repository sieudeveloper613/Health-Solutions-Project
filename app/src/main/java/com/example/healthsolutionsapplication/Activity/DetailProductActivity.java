package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.bumptech.glide.Glide;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetailProductActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    TextView tvNameProduct, tvPriceProduct, tvCategoryProduct,
             tvTypeProduct, tvOriginProduct, tvBranchProduct, tvDescriptionProduct;
    ImageView imgProduct;
    CardView cardBackPressed;
    MaterialButton btnAddProduct;

    // Object and Reference here
    SharedPreferences sharedPref;
    Product product;
    int idProduct, idCustomer;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);

        // define id for view
            initView();

        // set method
        Intent intent = getIntent();
        idProduct = intent.getIntExtra("idProduct", 0);

        if(idProduct != 0){
            getDetail();
        }

    }
    
    
    private void getDetail() {
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performGetIdProduct(idProduct);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if (response.body().getResult() == Constants.RESULT_1){
                            product = response.body().getProduct();
                            tvNameProduct.setText(product.getNameProduct());
                            tvPriceProduct.setText(String.valueOf(product.getPriceProduct()) + " đ");
                            tvCategoryProduct.setText(product.getNameCategory());
                            tvTypeProduct.setText(product.getTypeProduct());
                            tvOriginProduct.setText(product.getOriginProduct());
                            tvBranchProduct.setText(product.getBranchProduct());
                            tvDescriptionProduct.setText(product.getContentProduct());
                            Glide.with(DetailProductActivity.this).load(product.getImageProduct()).into(imgProduct);
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
        tvDescriptionProduct = findViewById(R.id.tv_descriptionProduct);
        imgProduct = findViewById(R.id.img_detailProduct);
        cardBackPressed = findViewById(R.id.card_backPressed);
        btnAddProduct = findViewById(R.id.btn_addProduct);
        
        // Set event
        cardBackPressed.setOnClickListener(this);
        btnAddProduct.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_backPressed:
                onBackPressed();
                finish();
            break;

            case R.id.btn_addProduct:
                insertProduct();
                break;

            default:
                Toast.makeText(DetailProductActivity.this, "Wrong Clicked", Toast.LENGTH_SHORT).show();
        }
    }

    private void insertProduct() {
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", new Customer().getId());
        Intent intent = getIntent();
        idProduct = intent.getIntExtra("idProduct", 0);
        String name = product.getNameProduct();
        double price = product.getPriceProduct();
        String image = product.getImageProduct();
        
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .insertProductIntoCart(idCustomer, idProduct, name, price, image);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if(response.body().getResult() == Constants.RESULT_1){
                            Toast.makeText(DetailProductActivity.this, "Thêm vào giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(DetailProductActivity.this, "Thêm thất bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }
}
