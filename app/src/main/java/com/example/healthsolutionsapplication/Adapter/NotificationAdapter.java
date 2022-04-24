package com.example.healthsolutionsapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthsolutionsapplication.Activity.DetailNotificationActivity;
import com.example.healthsolutionsapplication.Model.Feedback;
import com.example.healthsolutionsapplication.Model.Notification;
import com.example.healthsolutionsapplication.R;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder>{
    Context context;
    List<Notification> notificationList;

    public NotificationAdapter(Context context, List<Notification> notificationList){
        this.context = context;
        this.notificationList = notificationList;
    }

    @NonNull
    @Override
    public NotificationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_notification, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.ViewHolder holder, int position) {
        SharedPreferences sharedPref = context.getSharedPreferences("MyPreferences", context.MODE_PRIVATE);
        String titleFeedback = sharedPref.getString("titleFeedback", new Feedback().getTitleFeedback());
        Notification notification = notificationList.get(position);
        if (notification != null){
            holder.tvTitle.setText(notification.getTitleFeedback());
            holder.tvTitleFeedback.setText(titleFeedback);
            holder.tvDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, DetailNotificationActivity.class);
                    intent.putExtra("idNotification", notification.getIdNotification());
                    context.startActivity(intent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        if (notificationList != null){
            return notificationList.size();
        }
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        MaterialCardView cardNotification;
        TextView tvTitle, tvTitleFeedback, tvDetail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardNotification = itemView.findViewById(R.id.card_notification);
            tvTitle = itemView.findViewById(R.id.tv_titleNotification);
            tvTitleFeedback = itemView.findViewById(R.id.tv_titleFeedback);
            tvDetail = itemView.findViewById(R.id.tv_detailNotification);
        }
    }
}
