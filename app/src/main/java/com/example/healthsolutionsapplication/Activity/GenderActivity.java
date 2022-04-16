package com.example.healthsolutionsapplication.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.sql.Array;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class GenderActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    RadioGroup rgGender;
    RadioButton rbMale, rbFemale, rbOther, radioButton;
    MaterialButton btnSaveGender;

    // Object and Reference
    int idCustomer, gender;
    Customer customer;
    SharedPreferences sharedPref;
    ToastGenerate toastGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        // Define Reference
        toastGenerate = new ToastGenerate(GenderActivity.this);

        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Giới tính");

        // define id from view
        initView();
        getEditGender();

    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_gender);
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
        rgGender = findViewById(R.id.rg_gender);
        rbMale = findViewById(R.id.rb_male);
        rbFemale = findViewById(R.id.rb_female);
        rbOther = findViewById(R.id.rb_other);
        btnSaveGender = findViewById(R.id.btn_saveGender);

        // define event from view
        btnSaveGender.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_saveGender:
                performGender();
                break;
        }
    }

    private void getEditGender(){
        customer = new Customer();
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        int getGenderCustomer = sharedPref.getInt("getGender", customer.getGender());

        if (getGenderCustomer == 0){
            rbMale.setChecked(true);
        }else if(getGenderCustomer == 1){
            rbMale.setChecked(true);
        }else if (getGenderCustomer == 2){
            rbMale.setChecked(true);
        }
    }

    private void performGender(){
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());
//        selectedGender = rgGender.getCheckedRadioButtonId();


        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performGender(idCustomer, gender);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if (response.body().getStatus().equals(Constants.SUCCESS)){
                        if(response.body().getResult() == Constants.RESULT_1){
                            //int getGender = 0;
//                            radioButton = (RadioButton) findViewById(gender);
                            if (rbMale.isChecked()){
                                gender = 0;

                            }else if (rbFemale.isChecked()){
                                gender = 1;

                            }else if(rbOther.isChecked()){
//                              gender = rbMale.isChecked() == true ? 0 : -1;
                                gender = 2;
                            }
                            toastGenerate.createToastMessage("Cập nhật thành công", 1);
                        }else{
                            toastGenerate.createToastMessage("Cập nhật thất bại", 2);
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