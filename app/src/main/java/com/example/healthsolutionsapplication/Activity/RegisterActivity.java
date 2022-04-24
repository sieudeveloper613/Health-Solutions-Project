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

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edResFullName, edResPhoneNumber, edResAccount, edResPassword, edResRePassword;
    MaterialButton btnRegister;
    TextView tvReturn;

    // Object and Reference
    ToastGenerate toastGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
//        getSupportActionBar().hide();
        changeStatusBarColor();
        toastGenerate = new ToastGenerate(RegisterActivity.this);
        
        // define id for view
        initView();

    }

    private void initView(){
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
                toastGenerate.createToastMessage("Không được để trống",2);

        } else if(!password.equals(rePassword)) {
            toastGenerate.createToastMessage("Mật khẩu không giống nhau",2);

        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performRegister(fullName, account, password, phoneNumber);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200){
                        if (response.body().getStatus().equals(Constants.SUCCESS)){
                            if (response.body().getResult() == Constants.RESULT_1){

                                toastGenerate.createToastMessage("Đăng ký thành công",1);
                                // String account = response.body().getCustomer().getAccount();
                                Intent intent = new Intent();
                                // intent.putExtra("account", account);
                                intent.setClass(RegisterActivity.this, LoginActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                toastGenerate.createToastMessage("Tài khoảng đã tồn tại",2);
                            }
                        }else{
                            toastGenerate.createToastMessage("Tài khoảng đã tồn tại",2);
                        }
                    }else{
                        toastGenerate.createToastMessage("System Error",0);
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