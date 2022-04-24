package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ConfigChangePassActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edNewPassword, edEnterNewPassword;
    MaterialButton btnChangePassword;

    // Object and Reference
    ToastGenerate toastGenerate;
    int idCustomer;
    String password, newPassword;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_change_pass);
        //getSupportActionBar().hide();
        changeStatusBarColor();

        // Define Reference
        toastGenerate = new ToastGenerate(ConfigChangePassActivity.this);


        // Define id for view
        initView();

        // Define method

    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    private void initView(){
        edNewPassword = findViewById(R.id.ed_newPassword);
        edEnterNewPassword = findViewById(R.id.ed_enterNewPassword);
        btnChangePassword = findViewById(R.id.btn_changePassword);

        // set event
        btnChangePassword.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_changePassword:
                performChangePassword();
                break;
        }
    }

    private void performChangePassword() {
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        Customer customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());

        password = edNewPassword.getText().toString();
        newPassword = edEnterNewPassword.getText().toString();
        if (password.isEmpty() && newPassword.isEmpty()){
            toastGenerate.createToastMessage("Mật khẩu không được để trống", 2);
        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performPassword(idCustomer, password);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200){
                        if(response.body().getStatus().equals(Constants.SUCCESS)){
                            if (response.body().getResult() == Constants.RESULT_1){
                                if (!password.equals(newPassword)){
                                    toastGenerate.createToastMessage("Mật khẩu không giống nhau", 2);
                                }else{
                                    toastGenerate.createToastMessage("Đổi mật khẩu thành công", 1);
                                    startActivity(new Intent(ConfigChangePassActivity.this, LoginActivity.class));
                                    finish();
                                }

                            }else{
                                Log.d(Constants.ERR, "Đổi mật khẩu thất bại");
                            }

                        }else{
                            Log.d(Constants.ERR, "Lỗi Đổi mật khẩu");
                        }

                    }else{
                        Log.d(Constants.ERR, "System Error");
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.d(Constants.ERR, t.getMessage() );
                }
            });
        }

    }
}