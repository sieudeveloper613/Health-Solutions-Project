package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Adapter.ProductAdapter;
import com.example.healthsolutionsapplication.Fragment.CategoryFragment;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView rcv;
    List<Product> mProducts;
    ProductAdapter mProductAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //ánh xạ
        rcv=findViewById(R.id.rv_bestPriceToday);
        mProducts=new ArrayList<>();

        mProductAdapter=new ProductAdapter(this,mProducts);
        rcv.setAdapter(mProductAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        createProducts();
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        overridePendingTransition(0,0);

                        Toast.makeText(HomeActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.action_type:
                        startActivity(new Intent(getApplicationContext(), CategoryFragment.class));
                        overridePendingTransition(0,0);
                        Toast.makeText(HomeActivity.this, "Danh mục", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_cart:
                        Toast.makeText(HomeActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_mess:
                        Toast.makeText(HomeActivity.this, "Tin nhắn", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_user:
                        Toast.makeText(HomeActivity.this, "Cá nhân", Toast.LENGTH_SHORT).show();
                        return true;
                }
                return false;
            }
        });
    }
    private void createProducts(){
        mProducts.add(new Product(R.drawable.img_item_sale,"Item 1",50000));
        mProducts.add(new Product(R.drawable.img_item_sale,"Item 2",60000));
        mProducts.add(new Product(R.drawable.img_item_sale,"Item 3",70000));

    }
}