package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

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


public class FullNameActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    EditText edChangeFullName;
    ImageView imgDeleteFullName;
    MaterialButton btnSaveFullName;

    // Object and Reference
    ToastGenerate toastGenerate;
    SharedPreferences sharedPref;
    int idCustomer;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_name);
        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Họ và Tên");

        // define Object and Reference
        toastGenerate = new ToastGenerate(FullNameActivity.this);

        // set id from view
        initView();

        // define method
        getNameEdit();


    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_fullName);
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
        edChangeFullName = findViewById(R.id.ed_changeFullName);
        imgDeleteFullName = findViewById(R.id.img_deleteFullName);
        btnSaveFullName = findViewById(R.id.btn_saveFullName);

        // set event for view
        btnSaveFullName.setOnClickListener(this);
        imgDeleteFullName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_deleteFullName:
                edChangeFullName.setText("");
                break;

            case R.id.btn_saveFullName:
                performChangeName();
                break;
        }
    }

    private void getNameEdit(){
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        String getName = sharedPref.getString("getName", customer.getName());
        edChangeFullName.setText(getName);
    }

    private void performChangeName(){
    sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
    customer = new Customer();
    idCustomer = sharedPref.getInt("getId", customer.getId());
    String changeName = edChangeFullName.getText().toString();

        if (changeName.isEmpty() && idCustomer != 0){
            toastGenerate.createToastMessage("Tên không được để trống", 2);
        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performName(idCustomer, changeName);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200){
                        if (response.body().getStatus().equals(Constants.SUCCESS)){
                            if (response.body().getResult() == Constants.RESULT_1){
                                toastGenerate.createToastMessage("Cật nhập tên thành công", 1);
                                edChangeFullName.setText("");

                            }else{
                                toastGenerate.createToastMessage("Cập nhật tên thất bại", 2);
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
                    Log.d("Err", t.getMessage());
                }
            });
        }
    }



}