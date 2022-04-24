package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.healthsolutionsapplication.Activity.CategoryBabyActivity;
import com.example.healthsolutionsapplication.Activity.CategoryHealthActivity;
import com.example.healthsolutionsapplication.Activity.CategoryHouseholdActivity;
import com.example.healthsolutionsapplication.Activity.CategoryStoreActivity;
import com.example.healthsolutionsapplication.R;

public class DashboardFragment extends Fragment implements View.OnClickListener{
    LinearLayout dashboardHousehold, dashboardHealth, dashboardBaby, dashboardStore;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        initView(view);

        return view;
    }

    private void initView(View view) {
        dashboardHousehold = view.findViewById(R.id.dashboard_household);
        dashboardHealth = view.findViewById(R.id.dashboard_health);
        dashboardBaby = view.findViewById(R.id.dashboard_boby);
        dashboardStore = view.findViewById(R.id.dashboard_store);

        // set event
        dashboardHousehold.setOnClickListener(this::onClick);
        dashboardHealth.setOnClickListener(this::onClick);
        dashboardBaby.setOnClickListener(this::onClick);
        dashboardStore.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dashboard_household:
                startActivity(new Intent(getContext(), CategoryHouseholdActivity.class));
                break;

            case R.id.dashboard_health:
                startActivity(new Intent(getContext(), CategoryHealthActivity.class));
                break;

            case R.id.dashboard_boby:
                startActivity(new Intent(getContext(), CategoryBabyActivity.class));
                break;

            case R.id.dashboard_store:
                startActivity(new Intent(getContext(), CategoryStoreActivity.class));
                break;
        }
    }
}