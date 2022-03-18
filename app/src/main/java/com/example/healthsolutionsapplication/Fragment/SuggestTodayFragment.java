package com.example.healthsolutionsapplication.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Adapter.ProductAdapter;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;

import java.util.ArrayList;
import java.util.List;


public class SuggestTodayFragment extends Fragment {
    ProductAdapter adapter;
    List<Product> mListProduct;
    RecyclerView rvSuggestToday;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_suggest_today, container, false);
        init(view);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(RecyclerView.HORIZONTAL);
        mListProduct = new ArrayList<>();
        rvSuggestToday.setLayoutManager(manager);
        createProducts();
        adapter = new ProductAdapter(getContext(), mListProduct);
        rvSuggestToday.setAdapter(adapter);
        return view;
    }

    private void init(View view){
        rvSuggestToday = view.findViewById(R.id.rv_suggestToday);
    }

    private void createProducts(){
        mListProduct.add(new Product(R.drawable.img_item_sale,"Item 1",50000));
        mListProduct.add(new Product(R.drawable.img_item_sale,"Item 2",60000));
        mListProduct.add(new Product(R.drawable.img_item_sale,"Item 3",70000));
    }

}