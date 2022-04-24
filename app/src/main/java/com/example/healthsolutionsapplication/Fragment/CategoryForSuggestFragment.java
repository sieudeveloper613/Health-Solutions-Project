package com.example.healthsolutionsapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthsolutionsapplication.Adapter.TypeAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Type;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryForSuggestFragment extends Fragment {
    // View and ViewGroup
    RecyclerView recyclerView;

    // Object and Reference
    List<Type> typeList;
    TypeAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_category_for_suggest, container, false);

        // Define id for view
        recyclerView = view.findViewById(R.id.rv_itemSuggest);

        // Define method
        getAllType();

        return view;
    }

    private void getAllType() {
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                .selectAllType();
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