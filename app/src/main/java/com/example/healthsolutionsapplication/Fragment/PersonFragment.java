package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.healthsolutionsapplication.Activity.QuoteActivity;
import com.example.healthsolutionsapplication.Activity.RollCallTodayActivity;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;

public class PersonFragment extends Fragment implements View.OnClickListener{
    MaterialCardView cardRollCallToday, cardQuoteToday;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        customToolBar(view, "Thông tin cá nhân");

        // define id for view
        cardRollCallToday = view.findViewById(R.id.card_rollCallToday);
        cardQuoteToday = view.findViewById(R.id.card_quoteToday);

        // define event
        cardRollCallToday.setOnClickListener(this);
        cardQuoteToday.setOnClickListener(this);

        // set up fragment in PersonFragment
        setUpAllFragment();
        return view;
    }

    private void setUpAllFragment(){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Client of Bill Fragment
        ClientBillOfPersonFragment clientBillOfPersonFragment = new ClientBillOfPersonFragment();
        transaction.add(R.id.container_billOfClient, clientBillOfPersonFragment);


        // Info Of Client Fragment
        InfoOfClientFragment infoOfClientFragment = new InfoOfClientFragment();
        transaction.add(R.id.container_infoOfClient, infoOfClientFragment);


        // Other Task of Person
        OtherOfPersonFragment otherOfPersonFragment = new OtherOfPersonFragment();
        transaction.add(R.id.container_otherOfClient, otherOfPersonFragment);
        transaction.commit();
    }

    private void customToolBar(View view, String titleString){
        MaterialToolbar materialToolbar = view.findViewById(R.id.mToolbar_person);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_rollCallToday:
                startActivity(new Intent(getContext(), RollCallTodayActivity.class));
                break;

            case R.id.card_quoteToday:
                startActivity(new Intent(getContext(), QuoteActivity.class));
                break;
        }
    }
}