package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        recyclerView = findViewById(R.id.rv_paymentProduct);
        // lay du lieu
        list.add(new xacNhanDonHangModel(R.mipmap.ic_launcher, "Máy đo nhịp tim ", "1500000","SL2"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        xacNhanDonHangAdapter adapter = new xacNhanDonHangAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }
}