package com.example.healthsolutionsapplication.Activity;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Adapter.ProductAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryHouseholdActivity extends AppCompatActivity {
    // View and ViewGroup
    SearchView searchView;
    RecyclerView recyclerView;

    // Object and Reference
    int idCategory;
    ProductAdapter adapter;
    List<Product> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_household);
        changeStatusBarColor();
        customToolBar("Gia dụng đời sống");
        // Define Reference

        // Define id for view
        initView();


        // Define method
        performProductByCategory();

    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_household);

        materialToolbar.setTitle(titleString);
        materialToolbar.setTitleCentered(true);
        materialToolbar.setTitleTextColor(Color.WHITE);
        materialToolbar.setBackgroundColor(Color.parseColor("#0088FF"));
        materialToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
//        setSupportActionBar(materialToolbar);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
         recyclerView = findViewById(R.id.rv_household);
         searchView = findViewById(R.id.sv_household);
    }

    private void performProductByCategory(){
        idCategory = 1;
        productList = new ArrayList<>();

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .getProductByCategory(idCategory);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if (response.body().getResult() == Constants.RESULT_1){
                            productList = new ArrayList<>();
                            productList = response.body().getProductList();
                            recyclerView.setHasFixedSize(true);
                            GridLayoutManager gridLayoutManager =
                                new GridLayoutManager(CategoryHouseholdActivity.this, 2);
                            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
                            recyclerView.setLayoutManager(gridLayoutManager);

                            adapter = new ProductAdapter(CategoryHouseholdActivity.this, productList);
                            recyclerView.setAdapter(adapter);

                        }
                    }else{
                        Toast.makeText(CategoryHouseholdActivity.this, "NO GET DATA", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {

            }
        });
    }



//    @Override
//    public boolean onCreateOptionsMenu( Menu menu) {
//        getMenuInflater().inflate( R.menu.menu_item, menu);
//
//        MenuItem myActionMenuItem = menu.findItem( R.id.action_search);
//        searchView = (SearchView) myActionMenuItem.getActionView();
//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String query) {
//                // Toast like print
////                UserFeedback.show( "SearchOnQueryTextSubmit: " + query);
//                if( ! searchView.isIconified()) {
//                    searchView.setIconified(true);
//                }
//                myActionMenuItem.collapseActionView();
//                return false;
//            }
//            @Override
//            public boolean onQueryTextChange(String s) {
//                // UserFeedback.show( "SearchOnQueryTextChanged: " + s);
//                return false;
//            }
//        });
//        return true;
//    }

}