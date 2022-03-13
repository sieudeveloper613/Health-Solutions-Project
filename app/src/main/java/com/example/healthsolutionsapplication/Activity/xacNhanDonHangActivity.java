package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.example.healthsolutionsapplication.Adapter.xacNhanDonHangAdapter;
import com.example.healthsolutionsapplication.Model.xacNhanDonHangModel;
import com.example.healthsolutionsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class xacNhanDonHangActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<xacNhanDonHangModel> list = new ArrayList<xacNhanDonHangModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xac_nhan_don_hang);
        customActionBar();
        recyclerView = findViewById(R.id.rv_paymentProduct);
        // lay du lieu
        list.add(new xacNhanDonHangModel(R.mipmap.ic_launcher, "Máy đo nhịp tim ", "1500000","SL2"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        xacNhanDonHangAdapter adapter = new xacNhanDonHangAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }
    private void customActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Địa Chỉ Nhận Hàng");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0088FF")));
        Drawable drawable= getResources().getDrawable(R.drawable.ic_arrow_back_24px);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }
}