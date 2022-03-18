package com.example.healthsolutionsapplication.Activity;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.healthsolutionsapplication.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

public class QuoteActivity extends AppCompatActivity implements View.OnClickListener{
    ShapeableImageView imgQuotes;
    TextView tvTitleQuotes, tvContentQuotes;
    MaterialButton btnUnderstood;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quote);
        // custom Status Bar and Toolbar
        changeStatusBarColor();
        customToolBar("Quotes Today");

        // define id for View
        imgQuotes = findViewById(R.id.img_quotes);
        tvTitleQuotes = findViewById(R.id.tv_titleQuotes);
        tvContentQuotes = findViewById(R.id.tv_contentQuotes);
        btnUnderstood = findViewById(R.id.btn_understood);
        btnUnderstood.setOnClickListener(this);
    }

    private void changeStatusBarColor(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue,this.getTheme()));
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(getResources().getColor(R.color.deep_blue));
        }
    }

    private void customToolBar(String titleString){
        MaterialToolbar materialToolbar = findViewById(R.id.mToolbar_quotes);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_understood:
                onBackPressed();
        }
    }
}
