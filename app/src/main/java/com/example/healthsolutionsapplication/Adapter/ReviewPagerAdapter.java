package com.example.healthsolutionsapplication.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.healthsolutionsapplication.Model.Review;
import com.example.healthsolutionsapplication.R;

import java.util.List;

public class ReviewPagerAdapter extends PagerAdapter {
    List<Review> reviewList;

    public ReviewPagerAdapter(List<Review> reviewList){
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_review, container, false);
        ImageView imgReview = view.findViewById(R.id.img_review);
        Review review = reviewList.get(position);
        imgReview.setImageResource(review.getImage());

        //add view
        container.addView(view);

        return view;
    }

    @Override
    public int getCount() {
        if(reviewList != null){
            return reviewList.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // remember delete super
        container.removeView((View) object);
    }

}
