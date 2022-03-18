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

import com.example.healthsolutionsapplication.Model.PurchasedProduct;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class PurchasedProductAdapter extends RecyclerView.Adapter<PurchasedProductAdapter.ViewHolder> {
    private List<PurchasedProduct> list;
    private Context context;
    public PurchasedProductAdapter(List<PurchasedProduct> list, Context context) {
        this.list = list;
        this.context= context;
    }

    // tao view
    @NonNull
    @Override
    public PurchasedProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.items_purchased_product, null);
        return new PurchasedProductAdapter.ViewHolder(view);
    }
    // gan du lieu view
    @Override
    public void onBindViewHolder(@NonNull PurchasedProductAdapter.ViewHolder holder, int position) {
        PurchasedProduct purchasedProduct = list.get(position);
        if (purchasedProduct != null){
            holder.imgPurchasedProduct.setImageResource(purchasedProduct.getImagePurchasedProduct());
            holder.tvNamePurchasedProduct.setText(purchasedProduct.getNamePurchasedProduct());
            holder.tvStatusPurchasedProduct.setText(purchasedProduct.getStatusPurchasedProduct());
        }
        holder.tvDetailPurchasedProduct.setOnClickListener(new View.OnClickListener() {
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
        ImageView imgPurchasedProduct;
        TextView tvNamePurchasedProduct, tvStatusPurchasedProduct, tvDetailPurchasedProduct;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPurchasedProduct = itemView.findViewById(R.id.img_purchasedProduct);
            tvNamePurchasedProduct = itemView.findViewById(R.id.tv_purchasedProductName);
            tvStatusPurchasedProduct = itemView.findViewById(R.id.tv_productStatus);
            tvDetailPurchasedProduct = itemView.findViewById(R.id.tv_productDetail);

        }
    }
}
