package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthsolutionsapplication.Activity.DetailProductActivity;
import com.example.healthsolutionsapplication.Model.Product;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder>{
    private List<Product> productList;

    private Context context;

    public ProductAdapter(Context context,List<Product> productList){
        this.context = context;
        this.productList = productList;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product,parent,false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Product product = productList.get(position);
        if (product != null){
            holder.tvProductName.setText(product.getNameProduct());
            holder.tvProductPrice.setText(product.getPriceProduct()+ " đ");
            Glide.with(context).load(product.getImageProduct()).into(holder.imgProduct);
            holder.cardProduct.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailProductActivity.class);
                    intent.putExtra("idProduct", product.getIdProduct());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (productList != null){
            return productList.size();
        }
        return 0;
    }

    public class ProductHolder extends RecyclerView.ViewHolder{
        private ImageView imgProduct;
        private TextView tvProductName,tvProductPrice;
        private CardView cardProduct;
        public ProductHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.img_product);
            tvProductName=itemView.findViewById(R.id.tv_nameProduct);
            tvProductPrice=itemView.findViewById(R.id.tv_priceProduct);
            cardProduct = itemView.findViewById(R.id.card_product);
        }
    }
}
