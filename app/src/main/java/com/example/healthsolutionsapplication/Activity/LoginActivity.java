package com.example.healthsolutionsapplication.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Constant.ToastGenerate;
import com.example.healthsolutionsapplication.Fragment.PersonFragment;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    // View and ViewGroup
    MaterialButton btnLogin, btnGoogle;
    TextView tvForgotPassword, tvRegister;
    EditText edGetAccount, edGetPassword;

    // Object and Reference
    ToastGenerate toastGenerate;
    SharedPreferences sharedPref;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//        Toolbar toolbar = getSupportActionBar().hide();
//        getSupportActionBar().hide();hide
        changeStatusBarColor();
        toastGenerate = new ToastGenerate(LoginActivity.this);
        // set id from view
        initView();
        ggSG();

    }

    private void ggSG() {
        gso =  new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this, gso);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIN();
            }
        });
    }

    private void SignIN() {
        Intent intent = gsc.getSignInIntent();
        startActivityForResult(intent, 1000);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                moveActivi();
            } catch (Exception e) {
                Toast.makeText(this, "Some thing went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void moveActivi() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        }
    }

    private void initView(){
        //??nh x???
        btnGoogle = findViewById(R.id.btn_google);
        btnLogin = findViewById(R.id.btn_login);
        tvRegister = findViewById(R.id.tv_register);
        tvForgotPassword = findViewById(R.id.tv_forgotPassword);
        edGetAccount = findViewById(R.id.ed_getAccount);
        edGetPassword = findViewById(R.id.ed_getPassword);

        // set event for view

        btnLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
    }

    private void performLogin(){
        String getAccount = edGetAccount.getText().toString();
        String getPassword = edGetPassword.getText().toString();

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                .performLogin(getAccount, getPassword);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if (getAccount.isEmpty() && getPassword.isEmpty()){
                        toastGenerate.createToastMessage("Vui l??ng nh???p ????? th??ng tin", 2);
                }else{
                    if (response.code() == 200){
                        if (response.body().getStatus().equals(Constants.SUCCESS)){
                            if (response.body().getResult() == Constants.RESULT_1){

                                sharedPref = getSharedPreferences("MyPreferences", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                int id = response.body().getCustomer().getId();
                                String name = response.body().getCustomer().getName();
                                String email = response.body().getCustomer().getEmail();
                                String phone = response.body().getCustomer().getPhone();
                                int gender = response.body().getCustomer().getGender();
                                String dob = response.body().getCustomer().getDob();
                                String mainAddress = response.body().getCustomer().getMainAddress();
                                String password = response.body().getCustomer().getPassword();
                                String avatar = response.body().getCustomer().getAvatar();
                                editor.putInt("getId", id);
                                editor.putString("getName", name);
                                editor.putString("getEmail", email);
                                editor.putString("getPhone", phone);
                                editor.putInt("getGender", gender);
                                editor.putString("getDob", dob);
                                editor.putString("getMainAddress", mainAddress);
                                editor.putString("getPassword", password);
                                editor.putString("getAvatar", avatar);
                                editor.commit();

                                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }else{
                                toastGenerate.createToastMessage("Vui l??ng ki???m tra l???i", 2);
                            }

                        }else{
                            toastGenerate.createToastMessage("????ng nh???p th???t b???i", 2);

                        }
                    }else{
                        toastGenerate.createToastMessage("System Error...", 2);

                    }
                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d("err", t.getMessage());
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                performLogin();
                break;

            case R.id.tv_register:
                Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(i);
                finish();
                break;

            case R.id.tv_forgotPassword:
                Intent intent = new Intent(LoginActivity.this, ForgotPassActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}