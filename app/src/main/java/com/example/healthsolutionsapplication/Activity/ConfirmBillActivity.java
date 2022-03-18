package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.healthsolutionsapplication.Adapter.xacNhanDonHangAdapter;
import com.example.healthsolutionsapplication.Model.xacNhanDonHangModel;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class ConfirmBillActivity extends AppCompatActivity implements View.OnClickListener {
    MaterialButton btnPayTheBill;
    RecyclerView recyclerView;
    List<xacNhanDonHangModel> list = new ArrayList<xacNhanDonHangModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_bill);
        changeStatusBarColor();
        customToolBar("Xác nhận đơn hàng");
        // get id from view
        init();
        recyclerView = findViewById(R.id.rv_paymentProduct);
        // lay du lieu
        list.add(new xacNhanDonHangModel(R.mipmap.ic_launcher, "Máy đo nhịp tim ", "1500000","SL2"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        xacNhanDonHangAdapter adapter = new xacNhanDonHangAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }
    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_confirmBill);
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

    private void init(){
        btnPayTheBill = findViewById(R.id.btn_payTheBill);
        btnPayTheBill.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_payTheBill:
                startActivity(new Intent(ConfirmBillActivity.this, BillActivity.class));
                break;
        }
    }
}