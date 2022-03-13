package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.healthsolutionsapplication.R;

public class diaChiNhanHangThemActivity extends AppCompatActivity {

    Spinner spinner_city, spinner_district, spinner_ward;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dia_chi_nhan_hang_them);
        customActionBar();
        spinner_city = (findViewById(R.id.spinner_city));
        spinner_district = (findViewById(R.id.spinner_district));
        spinner_ward = (findViewById(R.id.spinner_ward));

        String[] items_city = {"- Vui lòng chọn -","TPHCM", "Hà Nội", "Đà Nẵng"};
        String[] items_district = {"- Vui lòng chọn -","Quận 1", "Quận 10", "Quận 3"};
        String[] items_ward = {"- Vui lòng chọn -","Phường Đa Kao", "Phường 10", "Phường 5"};

        ArrayAdapter<String> adapter_city = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items_city);
        spinner_city.setAdapter(adapter_city);
        ArrayAdapter<String> adapter_district = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items_district);
        spinner_district.setAdapter(adapter_district);
        ArrayAdapter<String> adapter_ward = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,items_ward);
        spinner_ward.setAdapter(adapter_ward);

        spinner_city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_ward.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(getApplicationContext(),spinner.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void customActionBar(){
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Địa Chỉ Nhận Hàng Thêm");
        actionBar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#0088FF")));
        Drawable drawable= getResources().getDrawable(R.drawable.ic_arrow_back_24px);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(drawable);
    }
}