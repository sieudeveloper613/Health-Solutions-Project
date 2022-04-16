package com.example.healthsolutionsapplication.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhoneNumberActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edChangePhone;
    ImageView imgDeletePhone;
    MaterialButton btnSavePhone;

    // Object and Reference
    SharedPreferences sharedPref;
    Customer customer;
    ToastGenerate toastGenerate;
    int idCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Số điện thoại");

        // define Object and Reference
        toastGenerate = new ToastGenerate(PhoneNumberActivity.this);

        // set id from view
        initView();

        // set method
        getPhoneEdit();

    }



    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_phoneNumber);
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
        edChangePhone = findViewById(R.id.ed_changePhoneNumber);
        imgDeletePhone = findViewById(R.id.img_deletePhoneNumber);
        btnSavePhone = findViewById(R.id.btn_savePhoneNumber);

        // set event for view
        btnSavePhone.setOnClickListener(this::onClick);
        imgDeletePhone.setOnClickListener(this::onClick);
    }

    private void performChangePhone() {
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());
        String changePhone = edChangePhone.getText().toString();
        if (changePhone.isEmpty() && idCustomer != 0) {
            toastGenerate.createToastMessage("Nhập số điện thoại", 2);

        } else if(changePhone.length() != 10) {
            toastGenerate.createToastMessage("Độ dài số điện thoại phải bằng 10", 2);

        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performPhone(idCustomer, changePhone);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200) {
                        if (response.body().getStatus().equals(Constants.SUCCESS)) {
                            if (response.body().getResult() == Constants.RESULT_1) {
                                    toastGenerate.createToastMessage("Cập nhật thành công", 1);


                            } else {
                                toastGenerate.createToastMessage("Cập nhật thất bại", 2);
                            }
                        } else {
                            toastGenerate.createToastMessage("Lỗi cập nhật", 2);
                        }
                    } else {
                        toastGenerate.createToastMessage("System Error...",0);
                    }
                }

                @Override
                public void onFailure(Call<ServerResponse> call, Throwable t) {
                    Log.d("Err", t.getMessage());
                }
            });
        }
    }

    private void getPhoneEdit(){
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        String getPhone = sharedPref.getString("getPhone", customer.getPhone());
        edChangePhone.setText(getPhone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_deletePhoneNumber:
                edChangePhone.setText("");
                break;

            case R.id.btn_savePhoneNumber:
                performChangePhone();
                break;
        }
    }
}