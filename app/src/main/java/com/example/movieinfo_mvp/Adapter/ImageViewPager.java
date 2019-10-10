package com.example.movieinfo_mvp.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.movieinfo_mvp.R;

import java.util.List;

public class ImageViewPager  extends PagerAdapter {

    private Context context;
    private List<String> imageList;
    private LayoutInflater inflater;

    public ImageViewPager(Context context, List<String> imageList){
        this.context = context;
        this.imageList = imageList;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == ((LinearLayout) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = inflater.inflate(R.layout.poster_image_slider,container,false);
        ImageView imageView = v.findViewById(R.id.imageView);
        Glide.with(context).load(imageList.get(position)).override(600,200).into(imageView);
        container.addView(v);
        return v;
    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.invalidate();
    }
}
