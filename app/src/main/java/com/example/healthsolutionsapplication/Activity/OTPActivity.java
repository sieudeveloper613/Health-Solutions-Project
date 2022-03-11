package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.R;
import com.google.android.material.button.MaterialButton;

public class OTPActivity extends AppCompatActivity {
    MaterialButton btnSendOTP, btnConfirmOTP;
    EditText edOTPCode;
    TextView tvOTPSecond;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        changeStatusBarColor();

        btnSendOTP = findViewById(R.id.btn_sendOTP);
        btnConfirmOTP = findViewById(R.id.btn_confirmOTP);
        edOTPCode = findViewById(R.id.ed_otpCode);
        tvOTPSecond = findViewById(R.id.tv_otpSecond);

        btnConfirmOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(OTPActivity.this, ConfigChangePassActivity.class));
                finish();
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
}