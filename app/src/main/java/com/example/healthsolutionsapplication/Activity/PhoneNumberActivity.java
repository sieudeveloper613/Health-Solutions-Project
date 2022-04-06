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
    EditText edChangePhone;
    ImageView imgDeletePhone;
    MaterialButton btnSavePhone;
    Customer customer;
    int idCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);
        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Số điện thoại");

        // set id from view
        initView();

        // set method
        getPhoneEdit();

    }

    private void initView() {
        edChangePhone = findViewById(R.id.ed_changePhoneNumber);
        imgDeletePhone = findViewById(R.id.img_deletePhoneNumber);
        btnSavePhone = findViewById(R.id.btn_savePhoneNumber);

        // set event for view
        btnSavePhone.setOnClickListener(this::onClick);
        imgDeletePhone.setOnClickListener(this::onClick);
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

    private void performChangePhone() {
        String changePhone = edChangePhone.getText().toString();
        if (changePhone.isEmpty() && idCustomer != 0) {
            Toast.makeText(PhoneNumberActivity.this, "Please fill Your Phone Number", Toast.LENGTH_SHORT).show();
        } else {
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performPhone(idCustomer, changePhone);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200) {
                        if (response.body().getStatus().equals("success")) {
                            if (response.body().getResult() == 1) {
                                if(edChangePhone.getText().toString().length() == 10){
                                    Toast.makeText(PhoneNumberActivity.this, "Changed - Valid Phone Number", Toast.LENGTH_SHORT).show();
                                }else{
                                    Toast.makeText(getApplicationContext(), "InValid Phone Number.", Toast.LENGTH_SHORT).show();
                                }
                                // String account = response.body().getCustomer().getAccount();
                                // edChangeEmail.setText("");

//                                Customer customer = response.body().getCustomer();
//                                String name = customer.getName();
//                                SharedPreferences sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
//                                SharedPreferences.Editor editor = sharedPref.edit();
//                                editor.putString("getName", name);
//                                editor.commit();
//                                Log.d("name", name);
//                                Intent intent = new Intent(EmailActivity.this, ChangeInformationActivity.class);
//                                startActivity(intent);

                            } else {
                                Toast.makeText(PhoneNumberActivity.this, "Failed...", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(PhoneNumberActivity.this, "Change Email Failed...", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(PhoneNumberActivity.this, "Something is wrong...", Toast.LENGTH_SHORT).show();
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
        SharedPreferences sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class).getIdCustomer(idCustomer);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if (response.body().getStatus().equals("success")){
                        if (response.body().getResult() == 1){
                            customer = response.body().getCustomer();
                            edChangePhone.setText(String.valueOf(customer.getPhone()));

                        }else{
                            Toast.makeText(PhoneNumberActivity.this, "Can't get Phone Number from database", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(PhoneNumberActivity.this, "get Phone Number Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(PhoneNumberActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
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