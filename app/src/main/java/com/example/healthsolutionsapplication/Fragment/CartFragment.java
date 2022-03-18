package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthsolutionsapplication.Activity.ConfirmBillActivity;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;


public class CartFragment extends Fragment implements View.OnClickListener{
    MaterialButton btnBuyProduct;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        customToolBar(view, "Giỏ hàng");
        init(view);
        return view;
    }

    private void init(View view){
        btnBuyProduct = view.findViewById(R.id.btn_buyProduct);
        btnBuyProduct.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_buyProduct:
                startActivity(new Intent(getContext(), ConfirmBillActivity.class));
                break;
        }
    }

    private void customToolBar(View view, String titleString){
        MaterialToolbar materialToolbar = view.findViewById(R.id.mToolbar_cart);
        materialToolbar.setTitle(titleString);
        materialToolbar.setTitleCentered(true);
        materialToolbar.setTitleTextColor(Color.WHITE);
        materialToolbar.setBackgroundColor(Color.parseColor("#0088FF"));
//        materialToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
//        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                onBackPressed();
//            }
//        });
    }


}