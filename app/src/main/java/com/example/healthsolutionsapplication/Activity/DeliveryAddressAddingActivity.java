package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Address;
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

public class DeliveryAddressAddingActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edHouseNumber, edWard, edDistrict, edCity, edNameReceiver, edPhoneReceiver;
    CheckBox cbAddressDefault;
    MaterialButton btnSaveAddress;

    // Object and Reference
    int idCustomer;
    boolean isDefault;
    String name, phone;
    Customer customer;
    SharedPreferences sharedPref;
    ToastGenerate toastGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address_adding);
        changeStatusBarColor();
        customToolBar("Thêm địa chỉ nhận hàng");

        // define reference
        toastGenerate = new ToastGenerate(DeliveryAddressAddingActivity.this);

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
            case R.id.btn_addAddress:
                performAddress();
                break;
        }
    }

    private void initView() {
        edNameReceiver = findViewById(R.id.ed_nameReceiverAddress);
        edPhoneReceiver = findViewById(R.id.ed_phoneReceiverAddress);
        edHouseNumber = findViewById(R.id.ed_houseNumber);
        edWard = findViewById(R.id.ed_ward);
        edDistrict = findViewById(R.id.ed_district);
        edCity = findViewById(R.id.ed_city);
        cbAddressDefault = findViewById(R.id.cb_addressDefault);
        btnSaveAddress = findViewById(R.id.btn_addAddress);

        // set event for view
        btnSaveAddress.setOnClickListener(this::onClick);
    }

    private void performAddress() {
        String getAddress = edHouseNumber.getText().toString() + ", ";
        getAddress += edWard.getText().toString() + ", ";
        getAddress += edDistrict.getText().toString() + ", ";
        getAddress += edCity.getText().toString();
        String finalGetAddress = getAddress;
        name = edNameReceiver.getText().toString();
        phone = edPhoneReceiver.getText().toString();
        isDefault = cbAddressDefault.isChecked();

        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .insertAddress(idCustomer, name, phone, finalGetAddress, isDefault);

        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (response.code() == 200){
                    if (serverResponse.getStatus().equals(Constants.SUCCESS)){
                        if (serverResponse.getResult() == Constants.RESULT_1){
                            if (cbAddressDefault.isChecked() == true){
                                isDefault = true;

                                toastGenerate.createToastMessage("Thêm thành công", 1);

                            }else if(cbAddressDefault.isChecked() == false){
                                isDefault = false;

                                toastGenerate.createToastMessage("Thêm thành công", 1);
                            }

                            SharedPreferences.Editor editor = sharedPref.edit();
                            int idAddress = response.body().getAddress().getIdAddress();
                            editor.putInt("getIdAddress", idAddress);
                            editor.commit();

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