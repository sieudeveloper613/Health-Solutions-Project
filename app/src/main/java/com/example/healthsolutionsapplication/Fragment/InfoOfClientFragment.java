package com.example.healthsolutionsapplication.Fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.healthsolutionsapplication.Activity.ChangeInformationActivity;

import com.example.healthsolutionsapplication.Activity.ChangePasswordActivity;
import com.example.healthsolutionsapplication.Model.Address;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;

public class InfoOfClientFragment extends Fragment implements View.OnClickListener{
    // View and ViewGroup
    ImageView imgEditCustomer;
    Button btnChangePassword;
    TextView tvShowFullName, tvShowGender, tvShowPhone, tvShowAddress;

    // Object and Reference
    SharedPreferences sharedPref;
    Customer customer;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_of_client, container, false);

        // define id for view
        initView(view);

        // define method
        getFullName();
        getGender();
        getPhoneNumber();
        getAddress();

        return view;
    }

    private void initView(View view){
        imgEditCustomer = view.findViewById(R.id.img_editCustomer);
        btnChangePassword = view.findViewById(R.id.btn_changePassword);
        tvShowFullName = view.findViewById(R.id.tv_showFullName);
        tvShowGender = view.findViewById(R.id.tv_showGender);
        tvShowPhone = view.findViewById(R.id.tv_showPhoneNumber);
        tvShowAddress = view.findViewById(R.id.tv_showAddress);

        //set clicked event
        imgEditCustomer.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);
    }

    private void getFullName(){
        customer = new Customer();
        sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String getNameCustomer = sharedPref.getString("getName", customer.getName());
        tvShowFullName.setText(getNameCustomer);
    }

    private void getGender(){
        customer = new Customer();
        sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        int getGenderCustomer = sharedPref.getInt("getGender", customer.getGender());

        if (getGenderCustomer == 0){
            tvShowGender.setText("Nam");
        }else if(getGenderCustomer == 1){
            tvShowGender.setText("Nữ");
        }else if (getGenderCustomer == 2){
            tvShowGender.setText("Khác");
        }

    }

    private void getPhoneNumber(){
        customer = new Customer();
        sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String getPhoneNumberCustomer = sharedPref.getString("getPhone", customer.getPhone());
        tvShowPhone.setText(getPhoneNumberCustomer);
    }

    private void getAddress(){
        customer = new Customer();
        sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        String getAddressCustomer = sharedPref.getString("getMainAddress", customer.getMainAddress());
        tvShowAddress.setText(String.valueOf(getAddressCustomer));
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_editCustomer:
                Intent intent = new Intent(getContext(), ChangeInformationActivity.class);
                startActivity(intent);
                break;

            case R.id.btn_changePassword:
                startActivity(new Intent(getContext(), ChangePasswordActivity.class));
                break;
        }
    }
}