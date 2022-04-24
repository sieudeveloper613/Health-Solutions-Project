package com.example.healthsolutionsapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthsolutionsapplication.R;


public class NotificationFragment extends Fragment {
    // View and ViewGroup

    // Object and Reference


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment;
        View view = inflater.inflate(R.layout.fragment_notification, container, false);

        // Define id for view
        initView(view);

        // Define method

        return view;
    }

    private void initView(View view) {

    }

}