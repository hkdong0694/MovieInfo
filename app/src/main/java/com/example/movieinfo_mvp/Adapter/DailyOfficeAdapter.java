package com.example.movieinfo_mvp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import org.w3c.dom.Text;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DailyOfficeAdapter extends RecyclerView.Adapter<DailyOfficeAdapter.DailyOfficeHolder> {

    private Context context;
    private List<RecyclerViewModel> recyclerViewModels = null;
    private RecyclerViewModel recyclerViewModel;
    private String audi;
    private DecimalFormat decimalFormat = new DecimalFormat("###,###");

    public DailyOfficeAdapter(Context context){
        this.recyclerViewModels = new ArrayList<>();
        this.context = context;
    }

    public class DailyOfficeHolder extends RecyclerView.ViewHolder{
        private TextView movieName;
        private TextView openDt;
        private TextView director;
        private RatingBar ratingBar;
        private ImageView imageView;
        private TextView rating;
        private TextView rank;
        private LinearLayout layout;

        public DailyOfficeHolder(View view){
            super(view);
            movieName = view.findViewById(R.id.moviename);
            openDt = view.findViewById(R.id.openDt);
            ratingBar = view.findViewById(R.id.ratingbar);
            imageView = view.findViewById(R.id.imageView);
            imageView.setClipToOutline(true);
            director = view.findViewById(R.id.director);
            rating = view.findViewById(R.id.rating);
            layout = view.findViewById(R.id.button);
            rank = view.findViewById(R.id.rank);
            layout = view.findViewById(R.id.button);
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
        holder.rank.setText(recyclerViewModel.getRank());
        holder.movieName.setText(recyclerViewModel.getMovieNm());
        String open = recyclerViewModel.getOpenDt().substring(5,recyclerViewModel.getOpenDt().length());
        audi = recyclerViewModel.getAudiAcc();
        open = open.replace("-","/");
        holder.openDt.setText(open + " 개봉 (" + decimalFormat.format(Integer.parseInt(audi)) + "명)");
        holder.director.setText(recyclerViewModel.getDirector().replace("|"," ") + "감독");
        float rating = Float.parseFloat(recyclerViewModel.getUserRating())/2;
        holder.ratingBar.setRating(rating);
        String rat = String.format("%.1f",rating);
        holder.rating.setText(rat);
        Glide.with(holder.imageView.getContext()).load(recyclerViewModel.getImage()).format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL).override(800,250).into(holder.imageView);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra("movieItem",recyclerViewModels.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return recyclerViewModels.size();
    }

    public void updateData(List<RecyclerViewModel> rM){
        recyclerViewModels.clear();
        recyclerViewModels.addAll(rM);
        notifyDataSetChanged();
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
