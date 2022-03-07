package com.example.healthsolutionsapplication.Activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //ánh xạ
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Toast.makeText(HomeActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_type:
                        Toast.makeText(HomeActivity.this, "Doanh mục", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_cart:
                        Toast.makeText(HomeActivity.this, "Giỏ hàng", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_mess:
                        Toast.makeText(HomeActivity.this, "Tin nhắn", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_user:
                        Toast.makeText(HomeActivity.this, "Cá nhân", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });
    }
}