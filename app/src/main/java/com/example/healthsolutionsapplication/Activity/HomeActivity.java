package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
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
                        startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                        overridePendingTransition(0,0);

                        Toast.makeText(HomeActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
                        return true;

                    case R.id.action_type:
                        Toast.makeText(HomeActivity.this, "Doanh mục", Toast.LENGTH_SHORT).show();
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
}