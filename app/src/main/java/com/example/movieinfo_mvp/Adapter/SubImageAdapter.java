package com.example.movieinfo_mvp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieinfo_mvp.R;

public class SubImageAdapter extends RecyclerView.Adapter<SubImageAdapter.SubImageHolder>{

    private Context context;
    private String[] arrayString;

    public SubImageAdapter(Context context, String[] arrayString){
        this.context = context;
        this.arrayString = arrayString;
    }

    public class SubImageHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        public SubImageHolder(View view){
            super(view);
            imageView = view.findViewById(R.id.sub_poster);
        }
    }

    @Override
    public int getItemCount() {
        return arrayString.length;
    }

    @NonNull
    @Override
    public SubImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.subposterimagelist_item,parent,false);
        SubImageHolder subImageHolder = new SubImageHolder(rootView);
        return subImageHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubImageHolder holder, int position) {
        String poster = arrayString[position];
        Glide.with(context).load(poster).override(500,300).diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);
    }
}
