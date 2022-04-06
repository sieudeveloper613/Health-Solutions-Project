package com.example.healthsolutionsapplication.Activity;

import android.content.Context;
import android.content.Intent;
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


public class FullNameActivity extends AppCompatActivity implements View.OnClickListener{
    EditText edChangeFullName;
    ImageView imgDeleteFullName;
    MaterialButton btnSaveFullName;
    int idCustomer;
    Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_name);

        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Họ và Tên");

        // set id from view
        initView();

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
                            edChangeFullName.setText(customer.getName());

                        }else{
                            Toast.makeText(FullNameActivity.this, "Can't get Name from database", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(FullNameActivity.this, "get Name Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(FullNameActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

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

    private void performChangeName(){
    String changeName = edChangeFullName.getText().toString();
        if (changeName.isEmpty() && idCustomer != 0){
            Toast.makeText(FullNameActivity.this, "Please fill Your Name", Toast.LENGTH_SHORT).show();
        }else{
            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                    .performName(idCustomer, changeName);
            callback.enqueue(new Callback<ServerResponse>() {
                @Override
                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                    if (response.code() == 200){
                        if (response.body().getStatus().equals("success")){
                            if (response.body().getResult() == 1){
                                Toast.makeText(FullNameActivity.this, "Your Name is Changed", Toast.LENGTH_SHORT).show();
                                // String account = response.body().getCustomer().getAccount();
                                edChangeFullName.setText("");

                                Customer customer = response.body().getCustomer();
                                String name = customer.getName();
                                SharedPreferences sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putString("getName", name);
                                editor.commit();
                                Log.d("name", name);
                                Intent intent = new Intent(FullNameActivity.this, ChangeInformationActivity.class);
                                startActivity(intent);

                            }else{
                                Toast.makeText(FullNameActivity.this, "Failed...", Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(FullNameActivity.this, "Change Name Failed...", Toast.LENGTH_SHORT).show();
                        }
                    }else{
                        Toast.makeText(FullNameActivity.this, "Something is wrong...", Toast.LENGTH_SHORT).show();
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