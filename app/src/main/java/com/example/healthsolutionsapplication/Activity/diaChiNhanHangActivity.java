package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.healthsolutionsapplication.Adapter.diaChiNhanHangAdapter;
import com.example.healthsolutionsapplication.Model.diaChiNhanHangModel;
import com.example.healthsolutionsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class diaChiNhanHangActivity extends AppCompatActivity {

    Button button;
    RecyclerView recyclerView;
    List<diaChiNhanHangModel> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_nhan_hang);
        recyclerView = findViewById(R.id.rv_deliveryAddress);
        button = findViewById(R.id.btn_addAddress);
        // lay du lieu
        list.add(new diaChiNhanHangModel("Đinh Thị Hoài Thu", "0912345678",R.drawable.ic_three_dot_24px,"38 Nhà Thờ Bến Cát, đường Hải Thượng Lãn Ông, phường 14, Gò Vấp TP.HCM", "Địa chỉ mặc định"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        diaChiNhanHangAdapter adapter = new diaChiNhanHangAdapter(list,this);
        recyclerView.setAdapter(adapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(diaChiNhanHangActivity.this, diaChiNhanHangThemActivity.class);
                startActivity(intent);
            }
        });


    }

}