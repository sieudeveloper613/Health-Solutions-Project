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

public class ForgotPassActivity extends AppCompatActivity {
    EditText edEnterPhoneNumber;
    MaterialButton btnConfirmPhoneNumber;
    TextView tvReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        changeStatusBarColor();

        btnConfirmPhoneNumber = findViewById(R.id.btn_confirmPhoneNumber);
        tvReturn = findViewById(R.id.tv_return);

        btnConfirmPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ForgotPassActivity.this, OTPActivity.class));
                finish();
            }
        });

        tvReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ForgotPassActivity.this, LoginActivity.class);
                startActivity(i);
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