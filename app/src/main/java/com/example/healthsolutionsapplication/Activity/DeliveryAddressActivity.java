package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.healthsolutionsapplication.Adapter.DeliveryAddressAdapter;
import com.example.healthsolutionsapplication.Adapter.HomeAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.PreLoadingLinearLayoutManager;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Address;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.BaselineLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeliveryAddressActivity extends AppCompatActivity implements View.OnClickListener{
    // view and view group
    MaterialButton btnAddAddress;
    RecyclerView recyclerView;
    SwipeRefreshLayout refreshLayout;

    // Object and Reference
    List<Address> list;
    DeliveryAddressAdapter addressAdapter;
    int idCustomer;
    Customer customer;
    Address address;
    SharedPreferences sharedPref;
    ToastGenerate toastGenerate;
    PreLoadingLinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
        changeStatusBarColor();
        customToolBar("Địa chỉ nhận hàng");

        // define Reference
        toastGenerate = new ToastGenerate(DeliveryAddressActivity.this);

        // define id for view
        initView();

        //define method and event
        getAddressById();

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                getAddressById();
                refreshLayout.setRefreshing(false);
            }
        });




    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_address);
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

    private void initView(){
        recyclerView = findViewById(R.id.rv_deliveryAddress);
        btnAddAddress = findViewById(R.id.btn_addAddress);
        refreshLayout = findViewById(R.id.refresh_layout);

        // set event for view
        btnAddAddress.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btn_addAddress:
                Intent intent = new Intent(DeliveryAddressActivity.this,
                                                        DeliveryAddressAddingActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void getAddressById(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", customer.getId());

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performGetAddressList(idCustomer);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().equals(Constants.SUCCESS)){
                        if(response.body().getResult() == Constants.RESULT_1){

                            list = new ArrayList<>();
                            list = response.body().getAddressList();


                                recyclerView.setHasFixedSize(true);
                                // VERTICAL = 1 ------ HORIZONTAL = 0
                                linearLayoutManager = new PreLoadingLinearLayoutManager(DeliveryAddressActivity.this, 1, false);
                                recyclerView.setLayoutManager(linearLayoutManager);
                                addressAdapter = new DeliveryAddressAdapter(list, DeliveryAddressActivity.this);
                                recyclerView.setAdapter(addressAdapter);
                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        addressAdapter.notifyDataSetChanged();
                                    }
                                });

                        }else{
                            toastGenerate.createToastMessage("Lấy địa chỉ thất bại", 2);
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



    private void reloadList(){
        list = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        linearLayoutManager = new PreLoadingLinearLayoutManager(DeliveryAddressActivity.this, 1, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        addressAdapter = new DeliveryAddressAdapter(list, DeliveryAddressActivity.this);
        recyclerView.setAdapter(addressAdapter);
    }
}