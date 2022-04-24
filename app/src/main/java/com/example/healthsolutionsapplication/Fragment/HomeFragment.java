package com.example.healthsolutionsapplication.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthsolutionsapplication.Activity.MoreSaleActivity;
import com.example.healthsolutionsapplication.Activity.SearchingActivity;
import com.example.healthsolutionsapplication.Activity.ToastActivity;
import com.example.healthsolutionsapplication.Adapter.HomeAdapter;
import com.example.healthsolutionsapplication.Adapter.ProductAdapter;
import com.example.healthsolutionsapplication.Adapter.ReviewPagerAdapter;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.Model.Review;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment implements View.OnClickListener{
    // View and ViewGroup
    RecyclerView rvNewest, rvSale, rvSuggest;
    private ViewPager viewPager;
    private CircleIndicator circleIndicator;
    TextView tvMoreSale;
    LinearLayout layoutSearching;

    // Object and Reference
    private List<Review> reviewList;
    List<Product> productList;
    ProductAdapter productAdapter;
    ReviewPagerAdapter reviewPagerAdapter;
    ToastGenerate toastGenerate;

    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            if (viewPager.getCurrentItem() == reviewList.size() - 1){
                viewPager.setCurrentItem(0);
            }else {
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        }
    };


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        // Define Preference
        toastGenerate = new ToastGenerate(getContext());

        // Define id for view
        initView(view);

        // Define method
        getListProductByNewest();
        getListProductByACS();
        setViewPager();
        loadFragment();

        // return
        return view;

    }

    private void loadFragment() {
        FragmentManager fragmentManager = getChildFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // Dashboard Fragment
        DashboardFragment dashboardFragment = new DashboardFragment();
        transaction.add(R.id.container_dashboard, dashboardFragment);


//        // Newest Fragment
//        NewestFragment newestFragment = new NewestFragment();
//        transaction.add(R.id.rv_container_newest, newestFragment);
//
//        // Suggested Fragment
//        SuggestTodayFragment suggestTodayFragment = new SuggestTodayFragment();
//        transaction.add(R.id.rv_container_suggestToday, suggestTodayFragment);

        transaction.commit();
    }

    private void initView(View view){
        rvNewest =  view.findViewById(R.id.rv_container_newest);
        rvSuggest = view.findViewById(R.id.rv_container_suggestToday);
        viewPager = view.findViewById(R.id.view_pager);
        circleIndicator = view.findViewById(R.id.circle_indicator);
        tvMoreSale = view.findViewById(R.id.tv_moreSale);
        layoutSearching = view.findViewById(R.id.linear_searching);

        // set event
        tvMoreSale.setOnClickListener(this::onClick);
        layoutSearching.setOnClickListener(this::onClick);
    }

    private void getListProductByNewest(){
       Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                            .getNewProduct();
       callback.enqueue(new Callback<ServerResponse>() {
           @Override
           public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
               ServerResponse resp = response.body();
               if (response.code() == 200){
                   if (resp.getStatus().equals(Constants.SUCCESS)){
                       if (resp.getResult() == Constants.RESULT_1){
                           productList = new ArrayList<>();
                           productList = resp.getProductList();
                           rvNewest.setHasFixedSize(true);
                           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                           linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                           productAdapter = new ProductAdapter(getContext(), productList);
                           rvNewest.setLayoutManager(linearLayoutManager);
                           rvNewest.setAdapter(productAdapter);
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

    private void getListProductByACS(){
        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                .getSuggestProduct();
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                ServerResponse resp = response.body();
                if (response.code() == 200){
                    if (resp.getStatus().equals(Constants.SUCCESS)){
                        if (resp.getResult() == Constants.RESULT_1){
                            productList = new ArrayList<>();
                            productList = resp.getProductList();
                            rvSuggest.setHasFixedSize(true);
                            GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
                            gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
                            productAdapter = new ProductAdapter(getContext(), productList);
                            rvSuggest.setLayoutManager(gridLayoutManager);
                            rvSuggest.setAdapter(productAdapter);
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


    // Viewpager
    private void setViewPager(){
        reviewList = getListReview();
        reviewPagerAdapter = new ReviewPagerAdapter(reviewList);
        viewPager.setAdapter(reviewPagerAdapter);

        circleIndicator.setViewPager(viewPager);

        mHandler.postDelayed(mRunnable, 3000);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private List<Review> getListReview(){
        List<Review> list = new ArrayList<>();
        list.add(new Review(R.drawable.health_pager_1 ));
        list.add(new Review(R.drawable.health_pager_2 ));
        list.add(new Review(R.drawable.health_pager_3 ));
        list.add(new Review(R.drawable.health_pager_4 ));
        list.add(new Review(R.drawable.health_pager_5 ));

        return list;

    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        mHandler.removeCallbacks(mRunnable);
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        mHandler.postDelayed(mRunnable, 3000);
//    }


    @Override
    public void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 3000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_moreSale:
                startActivity(new Intent(getContext(), MoreSaleActivity.class));
                break;

            case R.id.linear_searching:
                startActivity(new Intent(getContext(), SearchingActivity.class));


        }
    }
}
