package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.healthsolutionsapplication.Adapter.DeliveryAddressAdapter;
import com.example.healthsolutionsapplication.Model.DeliveryAddress;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressActivity extends AppCompatActivity {

    MaterialButton btnAddAddress;
    RecyclerView recyclerView;
    List<DeliveryAddress> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_address);
        changeStatusBarColor();
        customToolBar("Địa chỉ nhận hàng");
        recyclerView = findViewById(R.id.rv_deliveryAddress);
        btnAddAddress = findViewById(R.id.btn_addAddress);


        // lay du lieu
        String addressDemo = "Nhà thờ Bến Cát, đường Hải Thượng Lãn Ông, phường 14, Quận Gò Vấp, Hồ Chí Minh";
        String addressDemo2 = "80/2, Dương Quảng Hàm, phường 05, Quận Gò Vấp, Hồ Chí Minh";
        list.add(new DeliveryAddress(-1, "Đinh Thị Hoài Thu", "0123456789",
                addressDemo, false));

        list.add(new DeliveryAddress(-1, "Hà Khởi Siêu", "0336574407",
                addressDemo2, true));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        DeliveryAddressAdapter adapter = new DeliveryAddressAdapter(list,this);
        recyclerView.setAdapter(adapter);

        btnAddAddress.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryAddressActivity.this, DeliveryAddressAddingActivity.class);
                startActivity(intent);
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
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_address);
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

}