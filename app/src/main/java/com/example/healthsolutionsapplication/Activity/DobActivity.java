package com.example.healthsolutionsapplication.Activity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;


public class DobActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    DatePicker datePickerSpinner;
    MaterialButton btnSaveDob;

    // Object and Reference
    Customer customer;
    SharedPreferences sharedPref;
    int idCustomer, mYear, mMonth, mDay;
    String getDob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dob);
        // setting status bar and action bar
        changeStatusBarColor();
        customToolBar("Ngày sinh");

        //set Id for view
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
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_dob);
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
        // define id here
        datePickerSpinner = findViewById(R.id.date_picker);
        btnSaveDob = findViewById(R.id.btn_saveDob);

        // define event here
        btnSaveDob.setOnClickListener(this::onClick);
    }

    private void getCalendar(){
        Calendar calendar = Calendar.getInstance();
        datePickerSpinner.init(calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH),
                new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear += 1;
                        String getDate = dayOfMonth + "/" + monthOfYear + "/" + year;
                        Toast.makeText(DobActivity.this, "You clicked : " + getDate, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void performDob(){
//        getCalendar();
        SharedPreferences sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        customer = new Customer();
        idCustomer = sharedPref.getInt("getId", customer.getId());
        final Calendar calendar = Calendar.getInstance();
        mYear = calendar.get(Calendar.YEAR);
        mMonth = calendar.get(Calendar.MONTH);
        mDay = calendar.get(Calendar.DAY_OF_MONTH);
//        getDob = String.valueOf(mYear + mMonth + mDay);

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .performDob(idCustomer, getDob);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().equals("success")){
                        if (response.body().getResult() == 1) {
                            datePickerSpinner.init(mYear, mMonth, mDay,
                                    new DatePicker.OnDateChangedListener() {
                                        @Override
                                        public void onDateChanged(DatePicker datePicker, int mYear, int mMonth, int mDay) {
                                            getDob = mDay + "-" + mMonth + "-" + mYear;
                                        }
                                    });

                            getDob = mYear + "-" + mMonth + "-" + mDay;
                            Toast.makeText(DobActivity.this, "Success - You clicked : " + getDob, Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(DobActivity.this, "Get Dob Failed", Toast.LENGTH_SHORT).show();
                        }

                    }else{
                        Toast.makeText(DobActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(DobActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
            case R.id.btn_saveDob:
                performDob();
                break;
        }
    }
}



