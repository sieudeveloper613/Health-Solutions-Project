package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.button.MaterialButton;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class ForgotPassActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edEnterEmail;
    MaterialButton btnConfirmEmail;
    TextView tvReturn;

    // Object and Reference
    ToastGenerate toastGenerate;
    SharedPreferences sharedPref;
    int idCustomer;
    String getEmail = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);
        changeStatusBarColor();

        // define Reference
        toastGenerate = new ToastGenerate(ForgotPassActivity.this);

        // define id for view
        initView();

        // define method


    }



    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    private void initView() {
        edEnterEmail = findViewById(R.id.ed_enterEmail);
        btnConfirmEmail = findViewById(R.id.btn_confirmEmail);
        tvReturn = findViewById(R.id.tv_return);

        // set event
        btnConfirmEmail.setOnClickListener(this::onClick);
        tvReturn.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_return:
                onBackPressed();

                break;

            case R.id.btn_confirmEmail:
                performValidateEmail();
                break;
        }
    }

    private void performValidateEmail() {
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", new Customer().getId());
        getEmail = edEnterEmail.getText().toString();
        if (getEmail.isEmpty()){
            toastGenerate.createToastMessage("Email không được để trống", 2);
        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .getValidateEmail(getEmail);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200){
                        if (response.body().getStatus().equals(Constants.SUCCESS)){
                            if(response.body().getResult() == Constants.RESULT_1){

                                    if (isValidEmailId(edEnterEmail.getText().toString().trim())){
                                        toastGenerate.createToastMessage("Xác thực thành công", 1);
                                        Intent intent = new Intent(ForgotPassActivity.this, ConfigChangePassActivity.class);
                                        idCustomer = new Customer().getId();
                                        intent.putExtra("getId", idCustomer);
                                        startActivity(intent);

                                    }

                            }else{
                                toastGenerate.createToastMessage("Email sai/không tồn tại", 2);
                            }

                        }else{
                            toastGenerate.createToastMessage("Lỗi xác thực", 2);

                        }

                    }else{
                        toastGenerate.createToastMessage("System Error", 0);
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.d(Constants.ERR, t.getMessage());
                }
            });
        }

    }

    private boolean isValidEmailId(String email){
        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches(); }

}