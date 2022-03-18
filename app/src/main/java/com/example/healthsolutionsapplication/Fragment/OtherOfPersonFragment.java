package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthsolutionsapplication.Activity.FeedBackActivity;
import com.example.healthsolutionsapplication.Activity.HealthSolutionsInfoActivity;
import com.example.healthsolutionsapplication.Activity.LoginActivity;
import com.example.healthsolutionsapplication.Activity.PurchasedProductActivity;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.card.MaterialCardView;

public class OtherOfPersonFragment extends Fragment implements View.OnClickListener{
    MaterialCardView cardPurchasedProduct, cardFeedbackToUs, cardInfoAboutUs, cardLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_other_of_person, container, false);
        init(view);
        eventForView();
        return view;
    }

    private void init(View view){
        cardPurchasedProduct = view.findViewById(R.id.card_purchasedProduct);
        cardFeedbackToUs = view.findViewById(R.id.card_feedbackToUs);
        cardInfoAboutUs = view.findViewById(R.id.card_infoAboutUs);
        cardLogout = view.findViewById(R.id.card_logout);
    }

    private void eventForView(){
        cardPurchasedProduct.setOnClickListener(this::onClick);
        cardFeedbackToUs.setOnClickListener(this::onClick);
        cardInfoAboutUs.setOnClickListener(this::onClick);
        cardLogout.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_purchasedProduct:
                startActivity(new Intent(getContext(), PurchasedProductActivity.class));
                break;

            case R.id.card_feedbackToUs:
                startActivity(new Intent(getContext(), FeedBackActivity.class));
                break;

            case R.id.card_infoAboutUs:
                startActivity(new Intent(getContext(), HealthSolutionsInfoActivity.class));
                break;

            case R.id.card_logout:
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                break;
        }
    }
}