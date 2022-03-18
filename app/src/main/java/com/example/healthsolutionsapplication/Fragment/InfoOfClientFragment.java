package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.healthsolutionsapplication.Activity.ChangeInformationActivity;

import com.example.healthsolutionsapplication.Activity.ChangePasswordActivity;
import com.example.healthsolutionsapplication.R;

public class InfoOfClientFragment extends Fragment implements View.OnClickListener{
    ImageView imgEditCustomer;
    Button btnChangePassword;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_info_of_client, container, false);

        imgEditCustomer = view.findViewById(R.id.img_editCustomer);
        btnChangePassword = view.findViewById(R.id.btn_changePassword);

        //set clicked event
        imgEditCustomer.setOnClickListener(this);
        btnChangePassword.setOnClickListener(this);

        return view;
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