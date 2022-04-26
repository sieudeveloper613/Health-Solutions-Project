package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthsolutionsapplication.Constant.Constants;
import com.example.healthsolutionsapplication.Model.Cart;
import com.example.healthsolutionsapplication.Model.Customer;
import com.example.healthsolutionsapplication.R;
import com.example.healthsolutionsapplication.Service.APIConnect;
import com.example.healthsolutionsapplication.Service.RetrofitClient;
import com.example.healthsolutionsapplication.Service.ServerResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> implements View.OnClickListener {
    private Context context;
    private List<Cart> cartList;
    private SharedPreferences sharedPref;
    private boolean isSelectedAll = false;
    int idCustomer, idCart;
    double itemPrice = 0;


    public CartAdapter(Context context, List<Cart> cartList){
        this.context = context;
        this.cartList = cartList;
    }

    public void setCartList(List<Cart> getCartList){
        this.cartList.clear();
        this.cartList.addAll(getCartList);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, int position) {
        final Cart cart = cartList.get(position);
        if (cart != null){
            holder.tvName.setText(cart.getNameProduct());
            holder.tvPrice.setText(String.valueOf(cart.getPriceProduct()) + " đ");
            Glide.with(context).load(cart.getImageProduct()).into(holder.image);

            holder.tvDelete.setOnClickListener(this::onClick);

            holder.cbSelectProductInCart.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        int index = cart.getIdCart();
                        Toast.makeText(context, "id Cart :" + index, Toast.LENGTH_SHORT).show();
                    }
                }
            });

            if (!isSelectedAll){
                holder.cbSelectProductInCart.setChecked(false);
            }else{
                holder.cbSelectProductInCart.setChecked(true);
            }
        }
    }

    @Override
    public int getItemCount() {
        if(cartList != null){
            return cartList.size();
        }
        return 0;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_deleteProductInCart:
                performDeleteProduct();
                break;

        }
    }

     public void getPrice(){
        itemPrice += cartList.get(idCart).getPriceProduct();
     }

    public void selectAll(){
        isSelectedAll = true;
        notifyDataSetChanged();
    }
    public void unselectAll(){
        isSelectedAll = false;
        notifyDataSetChanged();
    }

    private void performDeleteProduct() {
        sharedPref = context.getSharedPreferences("MyPreferences", Context.MODE_PRIVATE);
        idCustomer = sharedPref.getInt("getId", new Customer().getId());
        idCart = cartList.get(new Cart().getIdCart()).getIdCart();

        Call<ServerResponse> callback = RetrofitClient.getClient().create(APIConnect.class)
                                        .deleteProductInCart(idCustomer, idCart);
        callback.enqueue(new Callback<ServerResponse>() {
            @Override
            public void onResponse(Call<ServerResponse> call, Response<ServerResponse> response) {
                if(response.code() == 200){
                    if(response.body().getStatus().equals(Constants.SUCCESS)){
                        if(response.body().getResult() == Constants.RESULT_1){
                            Toast.makeText(context, "Xóa giỏ hàng : " + idCart + " thành công", Toast.LENGTH_SHORT).show();
                            notifyItemRemoved(cartList.size());
                        }

                    }else{
                        Toast.makeText(context, "Xóa giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(context, "System Error", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<ServerResponse> call, Throwable t) {
                Log.d(Constants.ERR, t.getMessage());
            }
        });

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvName, tvPrice, tvDelete, tvAmount;
        CheckBox cbSelectProductInCart;
        Button btnMinus, btnPlus;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_nameProductInCart);
            tvPrice = itemView.findViewById(R.id.tv_priceProductInCart);
            tvDelete = itemView.findViewById(R.id.tv_deleteProductInCart);
            tvAmount = itemView.findViewById(R.id.tv_amountProductInCart);
            cbSelectProductInCart = itemView.findViewById(R.id.cb_selectProductInCart);
            image = itemView.findViewById(R.id.img_productInCart);
            btnMinus = itemView.findViewById(R.id.btn_minus);
            btnPlus = itemView.findViewById(R.id.btn_plus);

            // set Event
            btnPlus.setOnClickListener(this::onClick);
            btnMinus.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            int amount = 1;
            switch (v.getId()){
                case R.id.btn_minus:
                    if (tvAmount.getText().toString().equals("1")){
                        tvAmount.setText("1");
                    }else{
                        amount = Integer.parseInt(tvAmount.getText().toString());
                        amount--;
                        tvAmount.setText(String.valueOf(amount));
                    }

                    break;

                case R.id.btn_plus:
                    amount = Integer.parseInt(tvAmount.getText().toString());
                    amount++;
                    tvAmount.setText(String.valueOf(amount));

                    break;
            }
        }
    }

}
