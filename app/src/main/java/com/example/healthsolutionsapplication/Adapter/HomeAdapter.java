package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Product> mProducts;
    private Context context;

    public HomeAdapter(Context context, List<Product> mProducts) {
        this.mProducts = mProducts;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_product,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = mProducts.get(position);
        if (product != null){
            holder.txt_1.setText(product.getName());
            holder.txt_2.setText(product.getPrice()+ " đ");
            Glide.with(context).load(product.getImage()).into(holder.img_Product);
        }
        holder.item_prodcut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailProductActivity.class);
                intent.putExtra("product_Ids", product.getIds());
                intent.putExtra("product_name", product.getName());
                intent.putExtra("product_price", product.getPrice());
                intent.putExtra("product_category", product.getCategoryName());
                intent.putExtra("product_branch", product.getBranchProduct());
                intent.putExtra("product_where", product.getWhereProduct());
                intent.putExtra("product_img", product.getImage());
                intent.putExtra("product_type", product.getTypeProduct());
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mProducts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_Product;
        private TextView txt_1,txt_2;
        CardView item_prodcut;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_Product = itemView.findViewById(R.id.img_item_product);
            txt_1 = itemView.findViewById(R.id.txt_view_product_1);
            txt_2 = itemView.findViewById(R.id.txt_view_product_2);
            item_prodcut = (CardView) itemView.findViewById(R.id.item_product);
        }
    }
}
