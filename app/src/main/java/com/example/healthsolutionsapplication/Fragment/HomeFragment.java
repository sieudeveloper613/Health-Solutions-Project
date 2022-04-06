package com.example.healthsolutionsapplication.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.healthsolutionsapplication.Activity.DetailProductActivity;
import com.example.healthsolutionsapplication.Adapter.HomeAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.Model.RequestInterface;
import com.example.healthsolutionsapplication.Model.ServerResponse;
import com.example.healthsolutionsapplication.R;
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
    DetailProductActivity detailProduct;
    CardView item_prodcut;
    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        rcv =  view.findViewById(R.id.rv_bestPriceToday);
        item_prodcut = (CardView) view.findViewById(R.layout.item_product);
        getall_product();
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

    }


    private void  getall_product(){
        OkHttpClient builder = new OkHttpClient.Builder()
                .readTimeout(5000, TimeUnit.MILLISECONDS)
                .writeTimeout(5000, TimeUnit.MILLISECONDS)
                .connectTimeout(10000, TimeUnit.MILLISECONDS)
                .retryOnConnectionFailure(true)
                .build();


        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl(Constants.BASE_URL)
                .client(builder)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        RequestInterface requestInterface =
                retrofit.create(RequestInterface.class);


        Call<ServerResponse> response = requestInterface.getall_product();
        response.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call,
                                   Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                if(resp.getResult().equals("true")){
                    mProducts = new ArrayList<>();
                    mProducts = resp.getProduct();
                    rcv.setHasFixedSize(true);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                    mHomeAdapter = new HomeAdapter(getContext(), mProducts);
                    rcv.setLayoutManager(linearLayoutManager);
                    rcv.setAdapter(mHomeAdapter);
                    Toast.makeText(getContext(), mProducts.size()+"", Toast.LENGTH_SHORT).show();
                }
                else {
//                    Toast.makeText(getContext(), "Không lấy được dữ liệu", Toast.LENGTH_SHORT).show();
                }

            }
            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {


                Toast.makeText(getContext(), "Không lấy được dữ liệu", Toast.LENGTH_SHORT).show();


            }
        });



    }
}