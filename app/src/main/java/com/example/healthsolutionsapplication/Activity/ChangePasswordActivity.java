package com.example.healthsolutionsapplication.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener{
    // set view here
    EditText edRecentPassword, edNewPassword, edEnterNewPassword;
    MaterialButton btnSavePassword;
    
    // set Object and Reference here
    Customer customer;
    SharedPreferences sharedPref;
    int idCustomer;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Đổi mật khẩu");
        
        // set init for view
        initView();
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_changePassword);
        materialToolbar.setTitle(titleString);
        materialToolbar.setTitleCentered(true);
        materialToolbar.setTitleTextColor(Color.WHITE);
        materialToolbar.setBackgroundColor(Color.parseColor("#0088FF"));
        materialToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_saveNewPassword:
                performPassword();
                break;
        }
    }

    private void initView(){
        // define id for view
        edRecentPassword = findViewById(R.id.ed_recentPassword);
        edNewPassword = findViewById(R.id.ed_changeNewPassword);
        edEnterNewPassword = findViewById(R.id.ed_reEnterNewPassword);
        btnSavePassword = findViewById(R.id.btn_saveNewPassword);
        
        // define event for view
        btnSavePassword.setOnClickListener(this::onClick);
    }
    
    
    private void performPassword(){
        customer = new Customer();
        String password = edNewPassword.getText().toString();
        String recentPassword = edRecentPassword.getText().toString();
        String newPassword = edNewPassword.getText().toString();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", customer.getId());
        String getRecentPassword = sharedPref.getString("getPassword", customer.getPassword());

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performPassword(idCustomer, password);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().equals("success")){
                        if (response.body().getResult() == 1) {
                            
                            if(recentPassword.isEmpty() && password.isEmpty() && newPassword.isEmpty()){
                                Toast.makeText(ChangePasswordActivity.this, "Please fill your password", Toast.LENGTH_SHORT).show();
                            }else{
                                if (!recentPassword.equals(getRecentPassword)){
                                    Toast.makeText(ChangePasswordActivity.this, "your old password went wrong", Toast.LENGTH_SHORT).show();
                                }else{
                                    if (!password.equals(newPassword)){
                                        Toast.makeText(ChangePasswordActivity.this, "New password is not same", Toast.LENGTH_SHORT).show();
                                    }else{
                                        edRecentPassword.setText("");
                                        edNewPassword.setText("");
                                        edEnterNewPassword.setText("");
                                        Toast.makeText(ChangePasswordActivity.this, "Successful - Password changed", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }

                            
                        }else{
                            Toast.makeText(ChangePasswordActivity.this, "Change Password failed", Toast.LENGTH_SHORT).show();
                        }
                        
                    }else{
                        Toast.makeText(ChangePasswordActivity.this, "failed", Toast.LENGTH_SHORT).show();
                    }
                    
                }else{
                    Toast.makeText(ChangePasswordActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
        
    }
}