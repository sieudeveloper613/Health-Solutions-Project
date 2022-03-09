package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Model.sanPhamDaMuaModel;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class sanPhamDaMuaAdapter extends RecyclerView.Adapter<sanPhamDaMuaAdapter.ViewHolder> {
    private List<sanPhamDaMuaModel> list;
    private Context context;
    public sanPhamDaMuaAdapter(List<sanPhamDaMuaModel> list, Context context) {
        this.list = list;
        this.context= context;
    }

    // tao view
    @NonNull
    @Override
    public sanPhamDaMuaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_sp_da_mua, null);
        return new sanPhamDaMuaAdapter.ViewHolder(view);
    }
    // gan du lieu view
    @Override
    public void onBindViewHolder(@NonNull sanPhamDaMuaAdapter.ViewHolder holder, int position) {
        holder.img_purchasedProduct.setImageResource(list.get(position).getImage());
        holder.tv_purchasedProductName.setText(list.get(position).getName());
        holder.tv_productStatus.setText(list.get(position).getStatus());
        holder.tv_productDetail.setText(list.get(position).getDetails());
    }
    // so luong Item
    @Override
    public int getItemCount() {
        return list.size();
    }
    // lop lien ket
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_purchasedProduct;
        TextView tv_purchasedProductName, tv_productStatus, tv_productDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_purchasedProduct = itemView.findViewById(R.id.img_purchasedProduct);
            tv_purchasedProductName = itemView.findViewById(R.id.tv_purchasedProductName);
            tv_productStatus = itemView.findViewById(R.id.tv_productStatus);
            tv_productDetail = itemView.findViewById(R.id.tv_productDetail);

        }
    }
}
