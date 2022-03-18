package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Model.DeliveryAddress;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class DeliveryAddressAdapter extends RecyclerView.Adapter<DeliveryAddressAdapter.ViewHolder> {
    private List<DeliveryAddress> list;
    private Context context;
    public DeliveryAddressAdapter(List<DeliveryAddress> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DeliveryAddressAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_delivery_address, null);
        return new DeliveryAddressAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DeliveryAddressAdapter.ViewHolder holder, int position) {
        DeliveryAddress address = list.get(position);
        holder.tvFullNameReceiver.setText(address.getNameAddress());
        holder.tvPhoneNumberReceiver.setText(address.getPhoneNumberAddress());
        holder.tvAddressReceiver.setText(address.getLocationAddress());
        holder.tvDefaultAddress.setText(address.isDefaultAddress() ? "Địa chỉ mặt định" : "");
        holder.imgEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "updating", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
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

        }
    }




}
