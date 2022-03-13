package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.R;
import com.google.android.material.button.MaterialButton;

public class ConfigChangePassActivity extends AppCompatActivity {
    EditText edNewPassword, edEnterNewPassword;
    MaterialButton btnChangePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_change_pass);
        changeStatusBarColor();

        edNewPassword = findViewById(R.id.ed_newPassword);
        edEnterNewPassword = findViewById(R.id.ed_enterNewPassword);
        btnChangePassword = findViewById(R.id.btn_changePassword);

        btnChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ConfigChangePassActivity.this, LoginActivity.class));
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