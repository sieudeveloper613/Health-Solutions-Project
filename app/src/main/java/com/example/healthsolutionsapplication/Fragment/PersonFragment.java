package com.example.healthsolutionsapplication.Fragment;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.example.healthsolutionsapplication.Activity.DetailProductActivity;
import com.example.healthsolutionsapplication.Activity.QuoteActivity;
import com.example.healthsolutionsapplication.Activity.UploadAvatarActivity;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonFragment extends Fragment implements View.OnClickListener{
    MaterialCardView cardUploadAvatar, cardQuoteToday;
    TextView tvNameCustomer, tvEmailCustomer;
    ShapeableImageView imgAvatarCustomer;
    int idCustomer;
    Customer customer;
    SharedPreferences sharedPref;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_person, container, false);
        customToolBar(view, "Thông tin cá nhân");

        // define id for view
        cardUploadAvatar = view.findViewById(R.id.card_uploadAvatar);
        cardQuoteToday = view.findViewById(R.id.card_quoteToday);
        tvNameCustomer = view.findViewById(R.id.tv_nameCustomer);
        tvEmailCustomer = view.findViewById(R.id.tv_emailCustomer);
        imgAvatarCustomer = view.findViewById(R.id.img_avatarCustomer);

        SharedPreferences sharedPref = getContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        Customer customer = new Customer();
        String getNameCustomer = sharedPref.getString("getName", "");
        String getEmailCustmer = sharedPref.getString("getEmail", customer.getEmail());
        tvNameCustomer.setText(getNameCustomer);
        tvEmailCustomer.setText(getEmailCustmer);
        sharedPref = getContext().getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", customer.getId());
        // define event
        cardUploadAvatar.setOnClickListener(this);
        cardQuoteToday.setOnClickListener(this);
        getimg_CS();
        // set up fragment in PersonFragment
        setUpAllFragment();
        return view;
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.card_uploadAvatar:
                startActivity(new Intent(getContext(), UploadAvatarActivity.class));
                break;

            case R.id.card_quoteToday:
                startActivity(new Intent(getContext(), QuoteActivity.class));
                break;
        }
    }
    private void getimg_CS() {
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                .getimg_CS(idCustomer);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if (response.body().getResult() == Constants.RESULT_1){
                            customer = response.body().getCustomer();
                            Glide.with(getContext()).load("http://192.168.100.39/HealthSolutionsDB/upload/"+customer.getAvatar()).into(imgAvatarCustomer);
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