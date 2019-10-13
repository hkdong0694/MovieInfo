package com.example.movieinfo_mvp.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieinfo_mvp.Network.Model.KMDetail.MovieResult;
import com.example.movieinfo_mvp.R;


public class SubDetailAdapter  extends RecyclerView.Adapter<SubDetailAdapter.SubDetailholder> {

    private String sub[] = {"제작년도","장르","타입","제작","키워드","회사"};
    private MovieResult movieResult;
    private Context context;

    public SubDetailAdapter(Context context, MovieResult movieResult){
        this.context = context;
        this.movieResult = movieResult;
    }

    public class SubDetailholder extends RecyclerView.ViewHolder{
        private TextView sub_title;
        private TextView sub_content;
        private View guideview;
        public SubDetailholder(View view){
            super(view);
            sub_title = view.findViewById(R.id.subtitle);
            sub_content = view.findViewById(R.id.subcomment);
            guideview  = view.findViewById(R.id.guideline);
        }
    }

    @Override
    public int getItemCount() {
        return sub.length;
    }

    @NonNull
    @Override
    public SubDetailholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootview = LayoutInflater.from(context).inflate(R.layout.submovielist_item,parent,false);
        SubDetailholder subDetailholder = new SubDetailholder(rootview);
        return subDetailholder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubDetailholder holder, int position) {
        if(position == sub.length - 1){holder.guideview.setVisibility(View.INVISIBLE); }
        holder.sub_title.setText(sub[position]);
        switch (position){
            case 0: holder.sub_content.setText(movieResult.getProdYear());break;
            case 1: holder.sub_content.setText(movieResult.getGenre());break;
            case 2: holder.sub_content.setText(movieResult.getType());break;
            case 3: holder.sub_content.setText(movieResult.getNation());break;
            case 4: holder.sub_content.setText(movieResult.getKeywords());break;
            case 5: holder.sub_content.setText(movieResult.getCompany());break;
        }
    }
}
