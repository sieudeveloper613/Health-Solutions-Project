package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.healthsolutionsapplication.Model.Type;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class TypeAdapter  extends RecyclerView.Adapter<TypeAdapter.ViewHolder> {
    Context context;
    List<Type> typeList;

    public TypeAdapter(Context context, List<Type> typeList) {
        this.context = context;
        this.typeList = typeList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_type, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Type type = typeList.get(position);
        if (type != null){
            holder.tvNameType.setText(type.getNameType());
            Glide.with(context).load(type.getImageType()).into(holder.imgType);
            holder.cardType.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(context, .class);
//                    intent.putExtra("idType", type.getIdType());
//                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if(typeList != null){
            return typeList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardType;
        ImageView imgType;
        TextView tvNameType;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardType = itemView.findViewById(R.id.card_type);
            imgType = itemView.findViewById(R.id.img_type);
            tvNameType = itemView.findViewById(R.id.tv_nameType);

        }
    }

}
