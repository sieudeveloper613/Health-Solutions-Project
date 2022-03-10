package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.healthsolutionsapplication.Adapter.sanPhamDaMuaAdapter;
import com.example.healthsolutionsapplication.Model.sanPhamDaMuaModel;
import com.example.healthsolutionsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class sanPhamDaMuaActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<sanPhamDaMuaModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_san_pham_da_mua);
        recyclerView = findViewById(R.id.rv_purchasedProduct);
        // lay du lieu
        list.add(new sanPhamDaMuaModel(R.mipmap.ic_launcher, "Máy đo nhịp tim cao cấp", "Còn Hàng","Chi Tiết"));
        list.add(new sanPhamDaMuaModel(R.mipmap.ic_launcher, "Máy đo nhịp tim cao cấp 2", "Hết Hàng","Chi Tiết"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        sanPhamDaMuaAdapter adapter = new sanPhamDaMuaAdapter(list,this);
        recyclerView.setAdapter(adapter);
    }
}