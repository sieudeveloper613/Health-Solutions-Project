package com.example.healthsolutionsapplication.Constant;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthsolutionsapplication.R;
import com.google.android.material.card.MaterialCardView;

public class ToastGenerate {
    private static ToastGenerate ourInstance;
    private TextView tvContentToast;
    private ImageView imgToast;
    private Context context;

    public ToastGenerate(Context context) {
        this.context = context;
    }
    public static ToastGenerate getInstance(Context context) {
        if (ourInstance == null)
            ourInstance = new ToastGenerate(context);
        return ourInstance;
    }

    //pass message and message type to this method
    public void createToastMessage(String message,int type){
    //inflate the custom layout
        LayoutInflater layoutInflater = (LayoutInflater)
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        MaterialCardView toastLayout = (MaterialCardView) layoutInflater.inflate(R.layout.layout_custom_toast,null);
        tvContentToast = toastLayout.findViewById(R.id.tv_contentToast);
        imgToast = toastLayout.findViewById(R.id.img_toast);


        switch (type){
            case 0:
                //if the message type is 0 fail toaster method will call
                createFailToast(toastLayout, tvContentToast,message);
                break;
            case 1:
                //if the message type is 1 success toaster method will call
                createSuccessToast(toastLayout, tvContentToast,message);
                break;

            case 2:
                createWarningToast( toastLayout, tvContentToast, message);
                //if the message type is 2 warning toaster method will call
                break;
            default:
                createFailToast(toastLayout,tvContentToast,message);

        }
    }

    //Failure toast message method
    private final void createFailToast(MaterialCardView toastLayout,TextView toastMessage,String message){

        toastLayout.setBackgroundColor(context.getResources().getColor(R.color.red));
        imgToast.setImageResource(R.drawable.ic_baseline_cancel_address);
        toastMessage.setText(message);
        toastMessage.setTextColor(context.getResources().getColor(R.color.white));
        showToast(context,toastLayout);
    }

    //warning toast message method
    private final void createWarningToast( MaterialCardView toastLayout, TextView toastMessage,
                                           String message) {
        toastLayout.setBackgroundColor(context.getResources().getColor(R.color.dark_yellow));
        imgToast.setImageResource(R.drawable.ic_baseline_warning);
        toastMessage.setText(message);
        toastMessage.setTextColor(context.getResources().getColor(R.color.white));
        showToast(context, toastLayout);
    }
    //success toast message method
    private final void createSuccessToast(MaterialCardView toastLayout,TextView toastMessage,String
            message){
        toastLayout.setBackgroundColor(context.getResources().getColor(R.color.dark_green));
        imgToast.setImageResource(R.drawable.ic_baseline_success_outline);
        toastMessage.setText(message);
        toastMessage.setTextColor(context.getResources().getColor(R.color.white));
        showToast(context,toastLayout);
    }

        private void showToast(Context context, View view){
        Toast toast = new Toast(context);
        toast.setGravity(Gravity.RIGHT,5,800); // show message in the top of the device
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.show();
    }
}

