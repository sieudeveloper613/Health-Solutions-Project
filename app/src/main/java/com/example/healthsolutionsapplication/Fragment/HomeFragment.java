package com.example.healthsolutionsapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthsolutionsapplication.Adapter.ProductAdapter;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    RecyclerView rcv;
    List<Product> mProducts;
    ProductAdapter mProductAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        setUpAllFragment();

        //ánh xạ
        rcv = view.findViewById(R.id.rv_bestPriceToday);
        mProducts=new ArrayList<>();
        mProductAdapter=new ProductAdapter(getContext(),mProducts);
        rcv.setAdapter(mProductAdapter);
        rcv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        createProducts();
        return view;
    }

    private void createProducts(){
        mProducts.add(new Product(R.drawable.img_item_sale,"Item 1",50000));
        mProducts.add(new Product(R.drawable.img_item_sale,"Item 2",60000));
        mProducts.add(new Product(R.drawable.img_item_sale,"Item 3",70000));
    }

    private void setUpAllFragment(){
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Dashboard Fragment
        DashboardFragment dashboardFragment = new DashboardFragment();
        transaction.add(R.id.container_dashboard, dashboardFragment);


        // Best Sale Fragment
        BestSaleFragment bestSaleFragment = new BestSaleFragment();
        transaction.add(R.id.container_bestSale, bestSaleFragment);


        // Newest Fragment
        NewestFragment newestFragment = new NewestFragment();
        transaction.add(R.id.container_newest, newestFragment);


        // Suggest Today Fragment
        SuggestTodayFragment suggestTodayFragment = new SuggestTodayFragment();
        transaction.add(R.id.container_suggestToday, suggestTodayFragment);
        transaction.commit();
    }
}