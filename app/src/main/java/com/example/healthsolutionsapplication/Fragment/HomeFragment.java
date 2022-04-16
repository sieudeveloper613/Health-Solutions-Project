package com.example.healthsolutionsapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthsolutionsapplication.Activity.ToastActivity;
import com.example.healthsolutionsapplication.Adapter.HomeAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    RecyclerView rcv;
    List<Product> productList;
    HomeAdapter mHomeAdapter;
    ToastGenerate toastGenerate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        toastGenerate = new ToastGenerate(getContext());
        rcv =  view.findViewById(R.id.rv_bestPriceToday);
        getListProduct();
        return view;

    }
    private void getListProduct(){
       Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                            .performGetList();
       callback.enqueue(new Callback<ServerResponse>() {
           @Override
           public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
               ServerResponse resp = response.body();
               if (response.code() == 200){
                   if (resp.getStatus().equals(Constants.SUCCESS)){
                       if (resp.getResult() == Constants.RESULT_1){
                           productList = new ArrayList<>();
                           productList = resp.getProductList();
                           rcv.setHasFixedSize(true);
                           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                           linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                           mHomeAdapter = new HomeAdapter(getContext(), productList);
                           rcv.setLayoutManager(linearLayoutManager);
                           rcv.setAdapter(mHomeAdapter);
                           toastGenerate.createToastMessage("Tải danh sách thành công", 1);
                       }else{
                           toastGenerate.createToastMessage("Tải danh sách thất bại", 2);                       }

                   }else{
                       toastGenerate.createToastMessage("Lỗi cập nhật", 2);
                   }

               }else{
                   toastGenerate.createToastMessage("System Error", 2);
               }
           }
           @Override
           public void onFailure(Call<ServerResponse> call, Throwable t) {
               toastGenerate.createToastMessage(t.getMessage(), 0);
           }
       });
    }


}
