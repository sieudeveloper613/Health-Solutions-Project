package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.contentcapture.DataShareRequest;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class EmailActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edChangeEmail;
    ImageView imgDeleteEmail;
    MaterialButton btnSaveEmail;

    // Object and Reference
    int idCustomer;
    Customer customer;
    SharedPreferences sharedPref;
    ToastGenerate toastGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email);

        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Email");

        // define object and Reference
        toastGenerate = new ToastGenerate(EmailActivity.this);

        // define id for view
        initView();


        // define method
        getEmailEdit();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_deleteEmail:
                edChangeEmail.setText("");
                break;

            case R.id.btn_saveEmail:
                performChangeEmail();
                break;
        }
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_email);
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

    private void initView() {
        edChangeEmail = findViewById(R.id.ed_changeEmail);
        imgDeleteEmail = findViewById(R.id.img_deleteEmail);
        btnSaveEmail = findViewById(R.id.btn_saveEmail);

        // set event for view
        btnSaveEmail.setOnClickListener(this::onClick);
        imgDeleteEmail.setOnClickListener(this::onClick);
    }

    private void getEmailEdit(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        String getEmail = sharedPref.getString("getEmail", customer.getEmail());
        edChangeEmail.setText(getEmail);
    }

    private void performChangeEmail() {
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", customer.getId());
        String changeEmail = edChangeEmail.getText().toString();

        if (changeEmail.isEmpty() && idCustomer != 0) {
            toastGenerate.createToastMessage("Vui lòng nhập Email", 2);
        } else {
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performEmail(idCustomer, changeEmail);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200) {
                        if (response.body().getStatus().equals(Constants.SUCCESS)) {
                            if (response.body().getResult() == Constants.RESULT_1) {
                                if(isValidEmailId(edChangeEmail.getText().toString().trim())){
                                    toastGenerate.createToastMessage("Cập nhật Email thành công", 1);
                                }else{
                                    toastGenerate.createToastMessage("Email sai định dạng", 2);
                                }

                            } else {
                                toastGenerate.createToastMessage("Cập nhật Email thất bại", 2);
                            }
                        } else {
                            toastGenerate.createToastMessage("Lỗi Cập nhật", 2);
                        }
                    } else {
                        toastGenerate.createToastMessage("System Error", 0);
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.d("Err", t.getMessage());
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