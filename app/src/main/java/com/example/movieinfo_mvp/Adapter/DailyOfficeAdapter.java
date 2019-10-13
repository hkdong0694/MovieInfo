package com.example.movieinfo_mvp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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
import com.example.movieinfo_mvp.Network.Model.RecyclerViewModel;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.View.MovieDetailActivity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DailyOfficeAdapter extends RecyclerView.Adapter<DailyOfficeAdapter.DailyOfficeHolder> {


    public interface OnclickListener{
        void onclick(View v, int pos,ImageButton imageButton);
    }

    private OnclickListener onclickListener1 = null;

    public void setOnclickListener(OnclickListener onclickListener){
        this.onclickListener1 = onclickListener;
    }

    private Context context;
    private List<RecyclerViewModel> recyclerViewModels = null;
    private RecyclerViewModel recyclerViewModel;
    private String audi;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###");
    private SharedPreferences sf;
    private Gson gson = new GsonBuilder().create();

    public DailyOfficeAdapter(Context context) {
        this.recyclerViewModels = new ArrayList<>();
        this.context = context;
        sf = context.getSharedPreferences("Movielike",Context.MODE_PRIVATE);
    }

    public class DailyOfficeHolder extends RecyclerView.ViewHolder {

        private TextView movieName;
        private TextView openDt;
        private TextView director;
        private RatingBar ratingBar;
        private ImageView imageView;
        private TextView rating;
        private TextView rank;
        private LinearLayout layout;
        private ImageButton imageButton;

        public DailyOfficeHolder(View view){
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
    public DailyOfficeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.dailymovielist_item,parent,false);
        DailyOfficeHolder dailyOfficeHolder = new DailyOfficeHolder(rootView);
        return dailyOfficeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DailyOfficeHolder holder, final int position) {
        recyclerViewModel = recyclerViewModels.get(position);
        holder.movieName.setText(recyclerViewModel.getMovieNm());
        holder.director.setText(recyclerViewModel.getDirector().replace("|"," ") + "감독");
        float rating = Float.parseFloat(recyclerViewModel.getUserRating())/2;
        String rat = String.format("%.1f",rating);
        holder.rating.setText(rat);
        holder.ratingBar.setRating(rating);
        Glide.with(holder.imageView.getContext()).load(recyclerViewModel.getImage()).format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(220,250).into(holder.imageView);
        holder.rank.setText(recyclerViewModel.getRank());
        String open = recyclerViewModel.getOpenDt().substring(5,recyclerViewModel.getOpenDt().length());
        audi = recyclerViewModel.getAudiAcc();
        open = open.replace("-","/");
        holder.openDt.setText(open + " 개봉 (" + decimalFormat.format(Integer.parseInt(audi)) + "명)");
        String key = sf.getString("like",null);
        if(key == null){
            holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
        } else {
            Type listType = new TypeToken<ArrayList<RecyclerViewModel>>() {}.getType();
            List<RecyclerViewModel> datas = gson.fromJson(key,listType);
            boolean check = false;
            for(RecyclerViewModel arrayModel : datas){
                if(arrayModel.getMovieNm().equals(recyclerViewModel.getMovieNm())){
                    check = true;
                    break;
                }
            }
            if(!check) holder.imageButton.setImageResource(R.drawable.ic_favorite_black_24dp);
            else holder.imageButton.setImageResource(R.drawable.ic_favorite_blacks_24dp);
        }
        holder.layout.setOnClickListener(new View.OnClickListener() {
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

    public RecyclerViewModel get(int position){
        return recyclerViewModels.get(position);
    }

    public void clear(){
        recyclerViewModels.clear();
        notifyDataSetChanged();
    }
}
