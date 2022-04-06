package com.example.healthsolutionsapplication.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.healthsolutionsapplication.R;
import com.google.android.material.button.MaterialButton;

public class SplashActivity extends AppCompatActivity {
    private final int RECORD_REQUEST_CODE = 1;
    MaterialButton btnDiscover;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int permission_write_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int permission_read_storage = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permission_camera = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);



        changeStatusBarColor();
        btnDiscover = findViewById(R.id.btn_discover);
        btnDiscover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (permission_write_storage != PackageManager.PERMISSION_GRANTED
                        || permission_read_storage != PackageManager.PERMISSION_GRANTED
                        || permission_camera != PackageManager.PERMISSION_GRANTED) {
                    makeRequest();
                    Toast.makeText(SplashActivity.this, "Vào cài đặt -> Quyền -> Camera & Thư Mục để cấp quyền cho ứng dụng", Toast.LENGTH_LONG).show();
                }
//
//                else if (permission_write_storage != PackageManager.PERMISSION_GRANTED
//                        || permission_read_storage != PackageManager.PERMISSION_GRANTED
//                        || permission_camera != PackageManager.PERMISSION_GRANTED) {
//                    Toast.makeText(SplashActivity.this, "Vào cài đặt -> Quyền -> Camera & Thư Mục để cấp quyền cho ứng dụng", Toast.LENGTH_LONG).show();
//                }

                else if (permission_write_storage == PackageManager.PERMISSION_GRANTED
                        || permission_read_storage == PackageManager.PERMISSION_GRANTED
                        || permission_camera != PackageManager.PERMISSION_DENIED) {
                    Intent i = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(i);
                    finish();
                }


            }
        });


    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    private void makeRequest() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.VIBRATE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, RECORD_REQUEST_CODE);
    }
}