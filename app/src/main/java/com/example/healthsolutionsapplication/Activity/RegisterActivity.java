package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edResFullName, edResPhoneNumber, edResAccount, edResPassword, edResRePassword;
    MaterialButton btnRegister;
    TextView tvReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        changeStatusBarColor();
        init();


    }

    private void init(){
        edResFullName = findViewById(R.id.ed_resFullName);
        edResAccount = findViewById(R.id.ed_resAccount);
        edResPhoneNumber = findViewById(R.id.ed_resPhoneNumber);
        edResPassword = findViewById(R.id.ed_resPassword);
        edResRePassword = findViewById(R.id.ed_resRePassword);
        btnRegister = findViewById(R.id.btn_register);
        tvReturn = findViewById(R.id.tv_return);

        // set event from view
        tvReturn.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_return:
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
                break;

            case R.id.btn_register:
                performSignUp();
                break;

        }
    }

    private void performSignUp(){
        String fullName = edResFullName.getText().toString();
        String account = edResAccount.getText().toString();
        String password = edResPassword.getText().toString();
        String phoneNumber = edResPhoneNumber.getText().toString();
        String rePassword = edResRePassword.getText().toString();
        if (fullName.isEmpty() && account.isEmpty() && phoneNumber.isEmpty()
                                    && password.isEmpty() && rePassword.isEmpty()){
            Toast.makeText(RegisterActivity.this, "Please fill EditText", Toast.LENGTH_SHORT).show();

        } else if(!password.equals(rePassword)) {
            Toast.makeText(RegisterActivity.this, "Password is not same", Toast.LENGTH_SHORT).show();

        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performRegister(fullName, account, password, phoneNumber);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200){
                        if (response.body().getStatus().equals("success")){
                            if (response.body().getResult() == 1){
                                Toast.makeText(RegisterActivity.this, "Registration success. Now you can login", Toast.LENGTH_SHORT).show();
                                // String account = response.body().getCustomer().getAccount();
                                Intent intent = new Intent();
                                // intent.putExtra("account", account);
                                intent.setClass(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(RegisterActivity.this, "User already exists...", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(RegisterActivity.this, "Registration Failed...", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(RegisterActivity.this, "Something is wrong...", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.d("Err", t.getMessage());
                }
            });
        }
    }
}