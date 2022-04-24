package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Activity.CategoryHealthActivity;
import com.example.healthsolutionsapplication.Activity.CategoryHouseholdActivity;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.card.MaterialCardView;

public class CategoryForHouseholdLifeFragment extends Fragment implements View.OnClickListener{
    // View and ViewGroup
    MaterialCardView cardItemHousehold;
    RecyclerView recyclerView;

    // Object and Reference


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_for_household_life, container, false);

        // Define id for view
        initView(view);

        // Define method

        return view;
    }

    private void initView(View view){
        cardItemHousehold = view.findViewById(R.id.card_itemHousehold);
        recyclerView = view.findViewById(R.id.rv_itemHousehold);

        // set event
        cardItemHousehold.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_itemHousehold:
                startActivity(new Intent(getContext(), CategoryHouseholdActivity.class));
                break;
        }
    }
}