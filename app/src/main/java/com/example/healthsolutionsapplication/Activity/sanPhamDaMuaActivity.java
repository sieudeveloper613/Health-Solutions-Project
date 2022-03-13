package com.example.healthsolutionsapplication.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
        customActionBar();
        // lay du lieu
        list.add(new sanPhamDaMuaModel(R.mipmap.ic_launcher, "Máy đo nhịp tim cao cấp", "Còn Hàng","Chi Tiết"));
        list.add(new sanPhamDaMuaModel(R.mipmap.ic_launcher, "Máy đo nhịp tim cao cấp 2", "Hết Hàng","Chi Tiết"));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        sanPhamDaMuaAdapter adapter = new sanPhamDaMuaAdapter(list,this);
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