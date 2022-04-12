package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuPopupHelper;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Model.Address;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DeliveryAddressAdapter extends RecyclerView.Adapter<DeliveryAddressAdapter.ViewHolder>{
    private List<Address> mList;
    private Context context;

    public DeliveryAddressAdapter(List<Address> mList, Context context){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public DeliveryAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_delivery_address, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryAddressAdapter.ViewHolder holder, int position) {
        Address address = mList.get(position);
        if (address != null){
            SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
            Customer customer = new Customer();
            String customerName = sharedPref.getString("getName", customer.getName());
            String customerPhone = sharedPref.getString("getPhone", customer.getPhone());
            holder.tvFullNameReceiver.setText(customerName);
            holder.tvPhoneNumberReceiver.setText(customerPhone);
            holder.tvAddressReceiver.setText(address.getAddress());
            holder.tvDefaultAddress.setText(address.isDefault() ? "" : "Địa chỉ mặt định");
        }


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgEditProfile;
        TextView tvFullNameReceiver, tvPhoneNumberReceiver, tvAddressReceiver, tvDefaultAddress;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgEditProfile = itemView.findViewById(R.id.img_editProfile);
            tvFullNameReceiver = itemView.findViewById(R.id.tv_fullNameReceiver);
            tvPhoneNumberReceiver = itemView.findViewById(R.id.tv_phoneNumberReceiver);
            tvAddressReceiver = itemView.findViewById(R.id.tv_addressReceiver);
            tvDefaultAddress = itemView.findViewById(R.id.tv_defaultAddress);

            imgEditProfile.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PopupMenu popup = new PopupMenu(imgEditProfile.getContext(), itemView);
                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            switch (item.getItemId()){
                                case R.id.action_selectAddress:
                                    return true;

                                case R.id.action_updateAddress:
                                    return true;

                                case R.id.action_deleteAddress:
                                    return true;

                                default:
                                    return false;
                            }

                        }
                    });
                    popup.inflate(R.menu.menu_item_recycler_view);
                    popup.setGravity(Gravity.RIGHT);

                    try {
                        Field[] fields = popup.getClass().getDeclaredFields();
                        for (Field field : fields) {
                            if ("mPopup".equals(field.getName())) {
                                field.setAccessible(true);
                                Object menuPopupHelper = field.get(popup);
                                Class<?> classPopupHelper = Class.forName(menuPopupHelper
                                        .getClass().getName());
                                Method setForceIcons = classPopupHelper.getMethod(
                                        "setForceShowIcon", boolean.class);
                                setForceIcons.invoke(menuPopupHelper, true);
                                break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                    popup.show();

                }
            });

        }
    }






}
