package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Model.diaChiNhanHangModel;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class diaChiNhanHangAdapter extends RecyclerView.Adapter<diaChiNhanHangAdapter.ViewHolder> {
    private List<diaChiNhanHangModel> list;
    private Context context;
    public diaChiNhanHangAdapter(List<diaChiNhanHangModel> list, Context context){
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public diaChiNhanHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_dia_chi_nhan_hang, null);
        return new diaChiNhanHangAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull diaChiNhanHangAdapter.ViewHolder holder, int position) {
        holder.tv_fullNameReceiver.setText(list.get(position).getName());
        holder.tv_phoneNumberReceiver.setText(list.get(position).getTel());
        holder.img_editProfile.setImageResource(list.get(position).getImage());
        holder.tv_addressReceiver.setText(list.get(position).getAddress());
        holder.tv_defaultAddress.setText(list.get(position).getDefaultAddress());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_editProfile;
        TextView tv_fullNameReceiver, tv_defaultAddress, tv_phoneNumberReceiver, tv_addressReceiver;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_editProfile = itemView.findViewById(R.id.img_editProfile);
            tv_fullNameReceiver = itemView.findViewById(R.id.tv_fullNameReceiver);
            tv_phoneNumberReceiver = itemView.findViewById(R.id.tv_phoneNumberReceiver);
            tv_addressReceiver = itemView.findViewById(R.id.tv_addressReceiver);
            tv_defaultAddress = itemView.findViewById(R.id.tv_defaultAddress);

        }
    }




}
