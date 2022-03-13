package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    private List<Product> mProducts;

    private Context context;

    public ProductAdapter(Context context,List<Product> list){
        this.context=context;
        this.mProducts=list;
    }

    public void setData(List<Product> list){
        this.mProducts = list;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_best_price_today,parent,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product=mProducts.get(position);
        if (product == null){
            return;
        }
        holder.imgProduct.setImageResource(product.getImage());
        holder.tvProductName.setText(product.getName());
        holder.tvProductPriceOld.setText(product.getPrice());
        holder.tvProductPriceNew.setText(product.getPrice());
    }

    @Override
    public int getItemCount() {
        if (mProducts != null){
            return mProducts.size();
        }
        return 0;
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvProductName,tvProductPriceOld,tvProductPriceNew;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct=itemView.findViewById(R.id.imgProductSale);
            tvProductName=itemView.findViewById(R.id.tvProductName);
            tvProductPriceOld=itemView.findViewById(R.id.tvProductPriceOld);
            tvProductPriceNew=itemView.findViewById(R.id.tvProductPriceNew);
        }
    }
}
