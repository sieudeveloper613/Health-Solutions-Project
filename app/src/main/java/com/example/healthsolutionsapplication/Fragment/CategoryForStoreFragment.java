package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Activity.CategoryHealthActivity;
import com.example.healthsolutionsapplication.Activity.CategoryHouseholdActivity;
import com.example.healthsolutionsapplication.Adapter.ProductAdapter;
import com.example.healthsolutionsapplication.Adapter.TypeAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Type;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.card.MaterialCardView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryForStoreFragment extends Fragment implements View.OnClickListener{
    // View and ViewGroup
    MaterialCardView cardItemStore;
    RecyclerView recyclerView;

    // Object and Reference
    int idCategory = 4;
    List<Type> typeList;
    TypeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_for_store, container, false);

        // Define id for view
        initView(view);

        // Define method
        getTypeByCategory();

        return view;
    }

    private void initView(View view) {
        cardItemStore = view.findViewById(R.id.card_itemStore);
        recyclerView = view.findViewById(R.id.rv_itemStore);

        // set event
        cardItemStore.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_itemBaby:
                startActivity(new Intent(getContext(), CategoryHealthActivity.class));
                break;
        }
    }

    private void getTypeByCategory() {
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                .selectTypeByCategory(idCategory);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if (response.body().getResult() == Constants.RESULT_1){
                            typeList = new ArrayList<>();
                            typeList = response.body().getTypeList();
                            recyclerView.setHasFixedSize(true);
                            GridLayoutManager gridLayoutManager =
                                    new GridLayoutManager(getContext(), 2);
                            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
                            recyclerView.setLayoutManager(gridLayoutManager);

                            adapter = new TypeAdapter(getContext(), typeList);
                            recyclerView.setAdapter(adapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }

}