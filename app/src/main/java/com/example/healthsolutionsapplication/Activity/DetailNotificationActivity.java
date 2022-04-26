package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.Notification;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailNotificationActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    TextView tvNotification, tvTitle, tvContent;
    MaterialButton btnConfirm;
    FrameLayout frameLayout;

    // Object and References
    Notification notification;
    int idNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_notification);
        // change status bar and Toolbar
        changeStatusBarColor();
        customToolBar("Chi tiết thông báo");

        // Define id for View
        initView();

        // Define method
        Intent intent = getIntent();
        idNotification = intent.getIntExtra("idNotification", 0);
        if(idNotification != 0){
            getDetail();
        }

    }



    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_detailNotification);
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

    private void initView() {
        tvNotification = findViewById(R.id.tv_detailTitleNotification);
        tvTitle = findViewById(R.id.tv_detailTitleFeedback);
        tvContent = findViewById(R.id.tv_detailContentNotification);
        btnConfirm = findViewById(R.id.btn_confirmNotification);
        frameLayout = findViewById(R.id.frame_layout);

        // Set event
        btnConfirm.setOnClickListener(this::onClick);

    }

    private void getDetail() {
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .selectNotificationById(idNotification);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (response.code() == 200){
                    if (response.body().getStatus().equals(Constants.SUCCESS)){
                        if(response.body().getResult() == Constants.RESULT_1){
                            notification = response.body().getNotification();
                            tvNotification.setText(notification.getTitleNotification());
                            tvTitle.setText(notification.getTitleFeedback());
                            tvContent.setText(notification.getContentNotification());
                        }
                    }else{
                        Snackbar snackbar = Snackbar.make(frameLayout, "Failed", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }

                }else{
                    Snackbar snackbar = Snackbar.make(frameLayout, "System Error", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.ERR, t.getMessage());
            }
        });
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_confirmNotification:
                onBackPressed();
                finish();
                break;
        }
    }

}