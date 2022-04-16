package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.R;

public class ToastActivity extends AppCompatActivity implements View.OnClickListener{
    Button btnFail, btnWarn, btnSuccess;
    ToastGenerate toastGenerate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        btnFail = findViewById(R.id.btn_fail);
        btnWarn = findViewById(R.id.btn_warning);
        btnSuccess = findViewById(R.id.btn_success);

        btnFail.setOnClickListener(this);
        btnWarn.setOnClickListener(this);
        btnSuccess.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_fail:
            toastGenerate = new ToastGenerate(ToastActivity.this);
            toastGenerate.createToastMessage("Đăng nhập thất bại", 0);
                break;

            case R.id.btn_warning:
                toastGenerate = new ToastGenerate(ToastActivity.this);
                toastGenerate.createToastMessage("Tài khoảng, mật khẩu sai", 2);
                break;

            case R.id.btn_success:
                toastGenerate = new ToastGenerate(ToastActivity.this);
                toastGenerate.createToastMessage("Đăng nhập thành công", 1);
                break;
        }
    }
}