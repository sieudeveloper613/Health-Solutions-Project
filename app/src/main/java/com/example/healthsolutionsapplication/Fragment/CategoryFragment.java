package com.example.healthsolutionsapplication.Fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthsolutionsapplication.R;

public class CategoryFragment extends Fragment implements View.OnClickListener{
    LinearLayout linearSuggest, linearHousehold, linearHealth, linearBaby, linearStore;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        linearSuggest = view.findViewById(R.id.linear_suggest);
        linearHousehold = view.findViewById(R.id.linear_life);
        linearHealth = view.findViewById(R.id.linear_health);
        linearBaby = view.findViewById(R.id.linear_baby);
        linearStore = view.findViewById(R.id.linear_store);

        showFragmentFirst(linearSuggest, new CategoryForSuggestFragment());

        linearSuggest.setOnClickListener(this);
        linearHousehold.setOnClickListener(this);
        linearHealth.setOnClickListener(this);
        linearBaby.setOnClickListener(this);
        linearStore.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        FragmentManager fragmentManager =  getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        switch (view.getId()) {
            case R.id.linear_suggest:
                linearSuggest.setBackgroundColor(Color.WHITE);
                linearHousehold.setBackgroundColor(Color.TRANSPARENT);
                linearHealth.setBackgroundColor(Color.TRANSPARENT);
                linearBaby.setBackgroundColor(Color.TRANSPARENT);
                linearStore.setBackgroundColor(Color.TRANSPARENT);
                CategoryForSuggestFragment suggestFragment = new CategoryForSuggestFragment();
                transaction.replace(R.id.fragment_container_category, suggestFragment);
                transaction.commit();
                break;

            case R.id.linear_life:
                linearSuggest.setBackgroundColor(Color.TRANSPARENT);
                linearHousehold.setBackgroundColor(Color.WHITE);
                linearHealth.setBackgroundColor(Color.TRANSPARENT);
                linearBaby.setBackgroundColor(Color.TRANSPARENT);
                linearStore.setBackgroundColor(Color.TRANSPARENT);
                CategoryForHouseholdLifeFragment householdLifeFragment = new CategoryForHouseholdLifeFragment();
                transaction.replace(R.id.fragment_container_category, householdLifeFragment);
                transaction.commit();
                break;

            case R.id.linear_health:
                linearSuggest.setBackgroundColor(Color.TRANSPARENT);
                linearHousehold.setBackgroundColor(Color.TRANSPARENT);
                linearHealth.setBackgroundColor(Color.WHITE);
                linearBaby.setBackgroundColor(Color.TRANSPARENT);
                linearStore.setBackgroundColor(Color.TRANSPARENT);
                CategoryForHealthFragment healthFragment = new CategoryForHealthFragment();
                transaction.replace(R.id.fragment_container_category, healthFragment);
                transaction.commit();
                break;

            case R.id.linear_baby:
                linearSuggest.setBackgroundColor(Color.TRANSPARENT);
                linearHousehold.setBackgroundColor(Color.TRANSPARENT);
                linearHealth.setBackgroundColor(Color.TRANSPARENT);
                linearBaby.setBackgroundColor(Color.WHITE);
                linearStore.setBackgroundColor(Color.TRANSPARENT);
                CategoryForBabyFragment babyFragment = new CategoryForBabyFragment();
                transaction.replace(R.id.fragment_container_category, babyFragment);
                transaction.commit();
                break;

            case R.id.linear_store:
                linearSuggest.setBackgroundColor(Color.TRANSPARENT);
                linearHousehold.setBackgroundColor(Color.TRANSPARENT);
                linearHealth.setBackgroundColor(Color.TRANSPARENT);
                linearBaby.setBackgroundColor(Color.TRANSPARENT);
                linearStore.setBackgroundColor(Color.WHITE);
                CategoryForStoreFragment storeFragment = new CategoryForStoreFragment();
                transaction.replace(R.id.fragment_container_category, storeFragment);
                transaction.commit();
                break;
        }
    }

    private void showFragmentFirst(LinearLayout linearLayout, Fragment fragment){
        linearLayout.setBackgroundColor(Color.WHITE);
        FragmentManager fragmentManager =  getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container_category, fragment);
        transaction.commit();
    }
}