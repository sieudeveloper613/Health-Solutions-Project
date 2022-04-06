package com.example.healthsolutionsapplication.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    int idCustomer;
    Customer customer;
    SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gender);

        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Giới tính");

        // define id from view
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

    private void performGender(){
        sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());
//        selectedGender = rgGender.getCheckedRadioButtonId();
        int gender = radioButton.getId();

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performGender(idCustomer, gender);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if (response.body().getStatus().equals("success")){
                        if(response.body().getResult() == 1){
                            String getGenderType = String.valueOf(gender);
                                if (rbMale.isChecked()) {
                                   getGenderType = rbMale.getText().toString();

                                } else if (rbFemale.isChecked()) {
                                    getGenderType = rbFemale.getText().toString();

                                } else if (rbOther.isChecked()) {
                                    getGenderType = rbOther.getText().toString();
                                }
                            Toast.makeText(GenderActivity.this, "Successful - " + getGenderType, Toast.LENGTH_SHORT).show();
                        
                        }else{
                            Toast.makeText(GenderActivity.this, "Change Gender Failed", Toast.LENGTH_SHORT).show();
                        }
                        
                    }else{
                        Toast.makeText(GenderActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }
                    
                }else{
                    Toast.makeText(GenderActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

}