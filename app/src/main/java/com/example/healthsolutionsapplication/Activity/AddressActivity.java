package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

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

public class AddressActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edHouseNumber, edWard, edDistrict, edCity;

    MaterialButton btnSaveAddress;

    // Object and Reference
    int idCustomer;
    Customer customer;
    SharedPreferences sharedPref;
    ToastGenerate toastGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        changeStatusBarColor();
        customToolBar("Địa chỉ cư trú");

        // define reference
        toastGenerate = new ToastGenerate(AddressActivity.this);

        // define id for view
        initView();

        // define method

    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_addingAddress);
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
        switch(view.getId()){
            case R.id.btn_addAddressCustomer:
                //toastGenerate.createToastMessage("aaaa", 1);
                performAddress();
                break;
        }
    }

    private void initView() {
        edHouseNumber = findViewById(R.id.ed_houseNumberCustomer);
        edWard = findViewById(R.id.ed_wardCustomer);
        edDistrict = findViewById(R.id.ed_districtCustomer);
        edCity = findViewById(R.id.ed_cityCustomer);
        btnSaveAddress = findViewById(R.id.btn_addAddressCustomer);

        // set event for view
        btnSaveAddress.setOnClickListener(this::onClick);
    }

    private void performAddress() {
        String getAddress = edHouseNumber.getText().toString() + ", ";
        getAddress += edWard.getText().toString() + ", ";
        getAddress += edDistrict.getText().toString() + ", ";
        getAddress += edCity.getText().toString();
        String finalGetAddress = getAddress;

        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());

        if(finalGetAddress.isEmpty()){
            toastGenerate.createToastMessage("Không được để trống", 2);
        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .updateMainAddress(idCustomer, finalGetAddress);

            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    ServerResponse serverResponse = response.body();
                    if (response.code() == 200){
                        if (serverResponse.getStatus().equals(Constants.SUCCESS)){
                            if (serverResponse.getResult() == Constants.RESULT_1){
                                    toastGenerate.createToastMessage("Cập nhật địa chỉ thành công", 1);
                                    Intent intent = new Intent(getApplicationContext(), ChangeInformationActivity.class);
                                    startActivity(intent);
                            }else{
                                toastGenerate.createToastMessage("Thêm thất bại", 2);
                            }

                        }else{
                            toastGenerate.createToastMessage("Lỗi cập nhật", 2);
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



}