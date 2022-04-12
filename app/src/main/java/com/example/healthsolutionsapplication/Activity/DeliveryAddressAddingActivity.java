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
    EditText edHouseNumber, edWard, edDistrict, edCity;
    CheckBox cbAddressDefault;
    MaterialButton btnSaveAddress;

    // Object and Reference
    int idCustomer;
    boolean isDefault;
    Customer customer;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address_adding);
        changeStatusBarColor();
        customToolBar("Thêm địa chỉ nhận hàng");

        // define id for view
        initView();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());
        Toast.makeText(getApplicationContext(), "id :" + idCustomer, Toast.LENGTH_SHORT).show();


        // define
       // getId();
    }

    private void initView() {
        edHouseNumber = findViewById(R.id.ed_houseNumber);
        edWard = findViewById(R.id.ed_ward);
        edDistrict = findViewById(R.id.ed_district);
        edCity = findViewById(R.id.ed_city);
        cbAddressDefault = findViewById(R.id.cb_addressDefault);
        btnSaveAddress = findViewById(R.id.btn_addAddress);

        // set event for view
        btnSaveAddress.setOnClickListener(this::onClick);
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

    private void performAddress() {
        String getAddress = edHouseNumber.getText().toString() + ", ";
        getAddress += edWard.getText().toString() + ", ";
        getAddress += edDistrict.getText().toString() + ", ";
        getAddress += edCity.getText().toString();
        String finalGetAddress = getAddress;
        isDefault = cbAddressDefault.isChecked();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", customer.getId());
        //Toast.makeText(getApplicationContext(), "get : " + getAddress + " - " + isDefault, Toast.LENGTH_SHORT).show();
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performAddress(idCustomer, finalGetAddress, isDefault);

        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse serverResponse = response.body();
                if (response.code() == 200){
                    if (serverResponse.getStatus().equals("success")){
                        if (serverResponse.getResult() == 1){
                            if (cbAddressDefault.isChecked() == true){
                                isDefault = true;
                                Toast.makeText(getApplicationContext(), "Adding Address successful" + isDefault, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), DeliveryAddressActivity.class);
                                startActivity(intent);
                            }else{
                                isDefault = false;
                                Toast.makeText(getApplicationContext(), "Adding Address successful" + isDefault, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(), DeliveryAddressActivity.class);
                                startActivity(intent);
                            }

                        }else{
                            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(getApplicationContext(), "Adding Address Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("Err", t.getMessage());
            }
        });
    }

    private void getId(){
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .getIdCustomer(idCustomer);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().equals("success")){
                        if (response.body().getResult() == 1){
                            Toast.makeText(getApplicationContext(), "id :" + idCustomer, Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }
}