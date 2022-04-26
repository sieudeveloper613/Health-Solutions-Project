package com.example.healthsolutionsapplication.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthsolutionsapplication.Activity.CategoryBabyActivity;
import com.example.healthsolutionsapplication.Activity.ConfirmBillActivity;
import com.example.healthsolutionsapplication.Adapter.CartAdapter;
import com.example.healthsolutionsapplication.Adapter.ProductAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.PreLoadingLinearLayoutManager;
import com.example.healthsolutionsapplication.Model.Cart;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CartFragment extends Fragment implements View.OnClickListener{
    // View and ViewGroup
    MaterialButton btnBuyProduct;
    RecyclerView recyclerView;
    ImageView imgRemoveAllCart;
    CheckBox cbSelectAllProduct;
    TextView tvTotalPrice;


    // Object and Reference
    PreLoadingLinearLayoutManager linearLayoutManager;
    SharedPreferences sharedPref;
    int idCustomer;
    List<Cart> cartList;
    CartAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        customToolBar(view, "Giỏ hàng");

        // Define id for view
        initView(view);

        // Define method
        loadList();
        getProductInCart();




        return view;
    }

    private void initView(View view){
        btnBuyProduct = view.findViewById(R.id.btn_buyProduct);
        recyclerView = view.findViewById(R.id.rv_productInCart);
        imgRemoveAllCart = view.findViewById(R.id.img_removeAllProduct);
        cbSelectAllProduct = view.findViewById(R.id.cb_selectAllProduct);
        tvTotalPrice = view.findViewById(R.id.tv_totalPrice);
        tvTotalPrice.setText("Không có sản phẩm");

        // Set Event
        btnBuyProduct.setOnClickListener(this::onClick);
        imgRemoveAllCart.setOnClickListener(this::onClick);

//        if (cbSelectAllProduct.isChecked() == true){
//            double totalPrice = 0;
//            for (int index = 0 ; index <= cartList.size() ; index++){
//                totalPrice += cartList.get(index).getPriceProduct();
//            }
//            tvTotalPrice.setText(String.valueOf(totalPrice));
//
//        }else{
//        }


        cbSelectAllProduct.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(cbSelectAllProduct.isChecked() == true){
                    adapter.selectAll();
                    cbSelectAllProduct.setText("Tất cả (" + cartList.size() + " sản phẩm)");

                    double totalPrice = 0;
//                    totalPrice += cartList.get(cart.getIdCart()).getPriceProduct();
                    cartList = new ArrayList<>();
                    for (int index = 0 ; index < cartList.size() ; index++){
                        totalPrice = totalPrice + cartList.get(index).getPriceProduct();
                    }
                    tvTotalPrice.setText(String.valueOf(totalPrice));

                }
                else if (cbSelectAllProduct.isChecked() == false){
                    adapter.unselectAll();
                    cbSelectAllProduct.setText("Tất cả (0 sản phẩm)");
                    tvTotalPrice.setText("Không có sản phẩm");

                }


            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_buyProduct:
                startActivity(new Intent(getContext(), ConfirmBillActivity.class));
                break;

            case R.id.img_removeAllProduct:
                    deleteAllCart();
                break;


        }
    }

    private void customToolBar(View view, String titleString){
        MaterialToolbar materialToolbar = view.findViewById(R.id.mToolbar_cart);
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

    private void getProductInCart(){
        sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", new Customer().getId());

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                .selectCartByCustomer(idCustomer);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if (response.body().getResult() == Constants.RESULT_1){
                            cartList = new ArrayList<>();
                            cartList = response.body().getCartList();
                            recyclerView.setHasFixedSize(true);
                            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                            linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
                            recyclerView.setLayoutManager(linearLayoutManager);
                            adapter = new CartAdapter(getContext(), cartList);
                            recyclerView.setAdapter(adapter);
                            adapter.notifyDataSetChanged();


                        }else{
                            Toast.makeText(getContext(), "NO GET DATA 2", Toast.LENGTH_SHORT).show();

                        }

                    }else{
                        Toast.makeText(getContext(), "NO GET DATA 1", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getContext(), "SYSTEM ERROR", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.ERR, t.getMessage());
            }
        });

    }

    private void deleteAllCart(){
        sharedPref = getContext().getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", new Customer().getId());

        if(cartList.size() == 0){
            Snackbar snackbar = Snackbar.make(getView(), "Giỏ hàng trống", Snackbar.LENGTH_SHORT);
            snackbar.show();
        }else{
            new AlertDialog.Builder(getContext())
                    .setTitle("Xóa giỏ hàng?")
                    .setMessage("Bạn chắc chắc xóa toàn bộ giỏ hàng?")
                    .setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                    .deleteAllCart(idCustomer);
                            callback.enqueue(new Callback<ServerResponse>() {
                                @Override
                                public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                                    if(response.code() == 200){
                                        if(response.body().getStatus().equals(Constants.SUCCESS)){
                                            if (response.body().getResult() == Constants.RESULT_1){
                                                adapter.notifyItemRemoved(cartList.size());
                                                Snackbar snackbar = Snackbar.make(getView(), "Đã xóa toàn bộ giỏ hàng", Snackbar.LENGTH_SHORT);
                                                snackbar.show();
                                            }

                                        }else{
                                            Snackbar snackbar = Snackbar.make(getView(), "Xóa thất bại", Snackbar.LENGTH_SHORT);
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
                    }).setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            }).show();
        }


    }

    private void performTotalCart(){
        Cart cart = new Cart();
        double price = cartList.get(cart.getIdCart()).getPriceProduct();
        tvTotalPrice.setText(String.valueOf(price));
    }

    private void loadList(){
        cartList = new ArrayList<>();
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartAdapter(getContext(), cartList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}