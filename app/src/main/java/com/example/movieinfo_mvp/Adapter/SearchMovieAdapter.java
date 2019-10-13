package com.example.movieinfo_mvp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.View.MovieDetailActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SearchMovieAdapter extends RecyclerView.Adapter<SearchMovieAdapter.MovieSearchHolder>{

    public interface OnclickListener{
        void onclick(View v, int pos, ImageButton imageButton);
    }

    private SearchMovieAdapter.OnclickListener onclickListener1 = null;

    public void setOnclickListener(SearchMovieAdapter.OnclickListener onclickListener){
        this.onclickListener1 = onclickListener;
    }

    private Context context;
    private List<Item> itemList = null;
    private Item item;
    private SharedPreferences sf;
    private Gson gson;

    public SearchMovieAdapter(Context context){
        itemList = new ArrayList<>();
        this.context = context;
        sf = context.getSharedPreferences("Movielike",Context.MODE_PRIVATE);
        gson = new GsonBuilder().create();
    }

    public class MovieSearchHolder extends RecyclerView.ViewHolder {

        private TextView movieName;
        private TextView openDt;
        private TextView director;
        private RatingBar ratingBar;
        private ImageView imageView;
        private TextView rating;
        private TextView rank;
        private LinearLayout layout;
        private ImageButton imageButton;

        public MovieSearchHolder(View view){
            super(view);
            movieName = view.findViewById(R.id.moviename);
            openDt = view.findViewById(R.id.openDt);
            ratingBar = view.findViewById(R.id.ratinga);
            imageView = view.findViewById(R.id.imageView);
            imageView.setClipToOutline(true);
            director = view.findViewById(R.id.director);
            rating = view.findViewById(R.id.rating);
            layout = view.findViewById(R.id.button);
            rank = view.findViewById(R.id.rank);
            layout = view.findViewById(R.id.button);
            imageButton = view.findViewById(R.id.imageButton);
            imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(onclickListener1 != null){
                            onclickListener1.onclick(view,pos,imageButton);
                        }
                    }
                }
            });
        }
    }


    @NonNull
    @Override
    public MovieSearchHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dailymovielist_item,parent,false);
        MovieSearchHolder movieSearchHolder = new MovieSearchHolder(rootView);
        return movieSearchHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MovieSearchHolder holder, final int position) {
        item = itemList.get(position);
        holder.rank.setText(String.valueOf(position+1));
        String name = item.getTitle().replace("<b>","");
        name = name.replace("</b>","");
        holder.movieName.setText(name);
        holder.director.setText(item.getDirector().replace("|"," ") + "감독");
        float rating = Float.parseFloat(item.getUserRating())/2;
        holder.ratingBar.setRating(rating);
        String rat = String.format("%.1f",rating);
        holder.rating.setText(rat);
        holder.openDt.setText(item.getPubDate() + "년 개봉");

        Glide.with(holder.imageView.getContext()).load(item.getImage()).format(DecodeFormat.PREFER_ARGB_8888)
                .placeholder(R.drawable.image2)
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(220,250).into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("name","search");
                intent.putExtra("movie",itemList.get(position));
                context.startActivity(intent);
            }
        });

        String key = sf.getString("like",null);
        if(key == null){
            holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            Type listType = new TypeToken<ArrayList<RecyclerViewModel>>() {
            }.getType();
            List<RecyclerViewModel> datas = gson.fromJson(key, listType);
            boolean check = false;
            for (RecyclerViewModel arrayModel : datas) {
                if (arrayModel.getMovieNm().equals(name)) {
                    check = true;
                    break;
                }
            }
            if (!check) holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
            else holder.imageButton.setImageResource(R.drawable.ic_favorite_blacks_24dp);
        }

    }

    public void add(Item item){
        itemList.add(item);
        notifyItemInserted(itemList.size()-1);
    }

    public void clear(){
        itemList.clear();
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}
