package com.example.healthsolutionsapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.session.PlaybackState;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FeedBackActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    MaterialButton btnFeedbackToUs;
    TextView tvFromCustomer;
    EditText edTitleFeedback, edContentFeedback;

    // Object and Reference
    SharedPreferences sharePref;
    int idCustomer;
    String nameCustomer, title, content;
    ToastGenerate toastGenerate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed_back);
        changeStatusBarColor();
        customToolBar("Feedback");

        // Define Reference
        toastGenerate = new ToastGenerate(FeedBackActivity.this);

        // Define id for view
        initView();

        // Define method
        getNameFeedback();
    }


    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_feedback);
        materialToolbar.setTitle(titleString);
        materialToolbar.setTitleCentered(true);
        materialToolbar.setTitleTextColor(Color.WHITE);
        materialToolbar.setBackgroundColor(Color.parseColor("#0088FF"));
        materialToolbar.setNavigationIcon(R.drawable.ic_arrow_back_24px);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }

    private void initView() {
        btnFeedbackToUs = findViewById(R.id.btn_feedBackToUs);
        tvFromCustomer = findViewById(R.id.tv_fromCustomer);
        edTitleFeedback = findViewById(R.id.ed_titleFeedBack);
        edContentFeedback = findViewById(R.id.ed_contentFeedBack);

        // set event
        btnFeedbackToUs.setOnClickListener(this::onClick);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_feedBackToUs:
                performFeedback();
                break;
        }
    }

    private void performFeedback() {
        sharePref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        idCustomer = sharePref.getInt("getId", new Customer().getId());
        nameCustomer = sharePref.getString("getName", new Customer().getName());

        title = edTitleFeedback.getText().toString();
        content = edContentFeedback.getText().toString();

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .insertFeedback(idCustomer, nameCustomer, title, content);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if(response.body().getResult() == Constants.RESULT_1){
                            toastGenerate.createToastMessage("Feedback thành công", 1);
                            edTitleFeedback.setText("");
                            edContentFeedback.setText("");


                            SharedPreferences.Editor editor = sharePref.edit();
                            int idFeedback = response.body().getFeedback().getIdFeedback();
                            String titleFeedback = response.body().getFeedback().getTitleFeedback();
                            editor.putInt("getIdFeedback", idFeedback);
                            editor.putString("getTitleFeedback", titleFeedback);
                        }

                    }else{
                        toastGenerate.createToastMessage("Feedback thất bại", 2);
                    }

                }else{
                    toastGenerate.createToastMessage("Lỗi Feedback", 2);
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.ERR, t.getMessage());
            }
        });

    }

    private void getNameFeedback(){
        sharePref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        nameCustomer = sharePref.getString("getName", new Customer().getName());
        tvFromCustomer.setText(nameCustomer);
    }
}