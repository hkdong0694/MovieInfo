package com.example.movieinfo_mvp.Adapter;

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
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.View.MovieDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class LikeMovieAdpater  extends RecyclerView.Adapter<LikeMovieAdpater.likeHolder> {

    private Context context;
    private List<RecyclerViewModel> recyclerViewModels;

    public LikeMovieAdpater(Context context) {
        this.context  = context;
        recyclerViewModels = new ArrayList<>();
    }

    public class likeHolder extends RecyclerView.ViewHolder{

        private ImageView likeimage;
        private TextView liketitle;

        public likeHolder(View view){
            super(view);
            likeimage = view.findViewById(R.id.likeimage);
            liketitle = view.findViewById(R.id.liketitle);
        }
    }

    @NonNull
    @Override
    public likeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.likemovielist_item,parent,false);
        likeHolder likeHolder = new likeHolder(rootview);
        return likeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull likeHolder holder, final int position) {
        RecyclerViewModel recyclerViewModel = recyclerViewModels.get(position);
        holder.liketitle.setText(recyclerViewModel.getMovieNm());
        Glide.with(holder.likeimage.getContext()).load(recyclerViewModel.getImage()).format(DecodeFormat.PREFER_ARGB_8888).override(350,400)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.likeimage);
        holder.likeimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("name","daily");
                intent.putExtra("movie",recyclerViewModels.get(position));
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return recyclerViewModels.size();
    }

    public void add(RecyclerViewModel recyclerViewModel){
        recyclerViewModels.add(recyclerViewModel);
        notifyItemInserted(recyclerViewModels.size()-1);
    }

    public void clear(){
        recyclerViewModels.clear();
        notifyDataSetChanged();
    }
}
