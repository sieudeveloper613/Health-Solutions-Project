package com.example.healthsolutionsapplication.Activity;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Adapter.PurchasedProductAdapter;
import com.example.healthsolutionsapplication.Model.PurchasedProduct;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class PurchasedProductActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<PurchasedProduct> list = new ArrayList<>();
    TextView tvStatusPurchasedProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchased_product);
        changeStatusBarColor();
        customToolBar("Sản phẩm đã mua");
        recyclerView = findViewById(R.id.rv_purchasedProduct);


        // lay du lieu
        list.add(new PurchasedProduct(-1, R.drawable.microsoft_icons_celebration, "Máy đo nhịp tim cao cấp Nhật Bản đạt chất lượng cao", "Còn Hàng"));
        list.add(new PurchasedProduct(-1, R.drawable.microsoft_icons_celebration, "Máy đo nhịp tim cao cấp Nhật Bản đạt chất lượng cao 2", "Hết Hàng"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        PurchasedProductAdapter adapter = new PurchasedProductAdapter(list,this);
        recyclerView.setAdapter(adapter);

    }
    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_purchasedProduct);
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

    public void isStatus(String status){
        PurchasedProduct purchasedProduct = new PurchasedProduct();
        if (purchasedProduct.getNamePurchasedProduct() == "Còn hàng"){

        }
    }
}