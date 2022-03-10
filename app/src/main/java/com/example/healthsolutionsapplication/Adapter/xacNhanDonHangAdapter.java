package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Model.xacNhanDonHangModel;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class xacNhanDonHangAdapter extends RecyclerView.Adapter<xacNhanDonHangAdapter.ViewHolder> {
    private List<xacNhanDonHangModel> list;
    private Context context;
    public xacNhanDonHangAdapter(List<xacNhanDonHangModel> list, Context context) {
        this.list = list;
        this.context= context;
    }

    // tao view
    @NonNull
    @Override
    public xacNhanDonHangAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_xac_nhan_don_hang, null);
        return new xacNhanDonHangAdapter.ViewHolder(view);
    }
    // gan du lieu view
    @Override
    public void onBindViewHolder(@NonNull xacNhanDonHangAdapter.ViewHolder holder, int position) {
        holder.img_purchasedProduct.setImageResource(list.get(position).getImage());
        holder.tv_purchasedProductName.setText(list.get(position).getName());
        holder.tv_productPrice.setText(list.get(position).getPrice());
        holder.tv_productAmount.setText(list.get(position).getAmount());
    }
    // so luong Item
    @Override
    public int getItemCount() {
        return list.size();
    }
    // lop lien ket
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_purchasedProduct;
        TextView tv_purchasedProductName, tv_productPrice, tv_productAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_purchasedProduct = itemView.findViewById(R.id.img_purchasedProduct);
            tv_purchasedProductName = itemView.findViewById(R.id.tv_purchasedProductName);
            tv_productPrice = itemView.findViewById(R.id.tv_productPrice);
            tv_productAmount = itemView.findViewById(R.id.tv_productAmount);

        }
    }
}
