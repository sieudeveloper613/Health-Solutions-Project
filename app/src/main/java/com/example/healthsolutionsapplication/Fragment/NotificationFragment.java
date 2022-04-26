package com.example.healthsolutionsapplication.Fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.resources.Compatibility;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.healthsolutionsapplication.Adapter.NotificationAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.Model.Notification;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NotificationFragment extends Fragment {
    // View and ViewGroup
    RecyclerView recyclerView;

    // Object and Reference
    NotificationAdapter adapter;
    List<Notification> notificationList;
    int idCustomer;
    SharedPreferences sharedPref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment;
        View view = inflater.inflate(R.layout.fragment_notification, container, false);
        customToolBar(view, "Thông báo");
        // Define id for view
        initView(view);

        // Define method
        getNotificationForCustomer();

        return view;
    }

    private void customToolBar(View view, String titleString){
        MaterialToolbar materialToolbar = view.findViewById(R.id.mToolbar_notification);
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

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.rv_notification);
    }

    private void getNotificationForCustomer(){
        sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", new Customer().getId());

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .selectIdNotification(idCustomer);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if(response.body().getResult() == Constants.RESULT_1){
                            notificationList = new ArrayList<>();
                            notificationList = response.body().getNotificationList();
                            recyclerView.setHasFixedSize(true);
                            LinearLayoutManager manager = new LinearLayoutManager(getContext());
                            manager.setOrientation(RecyclerView.VERTICAL);
                            adapter = new NotificationAdapter(getContext(), notificationList);
                            recyclerView.setLayoutManager(manager);
                            recyclerView.setAdapter(adapter);
                        }

                    }else{
                        Snackbar snackbar = Snackbar.make(getView(), "Failed", Snackbar.LENGTH_SHORT);
                        snackbar.show();
                    }

                }else{
                    Snackbar snackbar = Snackbar.make(getView(), "System Error", Snackbar.LENGTH_SHORT);
                    snackbar.show();
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.ERR, t.getMessage());
            }
        });
    }

}