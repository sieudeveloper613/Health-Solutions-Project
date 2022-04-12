package com.example.healthsolutionsapplication.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthsolutionsapplication.Adapter.HomeAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    RecyclerView rcv;
    List<Product> mProducts;
    HomeAdapter mHomeAdapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
                   if (resp.getStatus().equals("success")){
                       if (resp.getResult() == 1){
                           mProducts = new ArrayList<>();
                           mProducts = resp.getProduct();
                           rcv.setHasFixedSize(true);
                           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                           linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                           mHomeAdapter = new HomeAdapter(getContext(), mProducts);
                           rcv.setLayoutManager(linearLayoutManager);
                           rcv.setAdapter(mHomeAdapter);
                           Toast.makeText(getContext(), mProducts.size()+" - get List successful", Toast.LENGTH_SHORT).show();

                       }else{
                           Toast.makeText(getContext(), "get List Failed", Toast.LENGTH_SHORT).show();
                       }

                   }else{
                       Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();

                   }

               }else{
                   Toast.makeText(getContext(), "Something went Wrongs", Toast.LENGTH_SHORT).show();

               }
           }
           @Override
           public void onFailure(Call<ServerResponse> call, Throwable t) {
               Toast.makeText(getContext(), "Không lấy được dữ liệu", Toast.LENGTH_SHORT).show();

           }
       });
    }


}
