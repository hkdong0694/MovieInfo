package com.example.movieinfo_mvp.View;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.movieinfo_mvp.Adapter.SearchMovieAdapter;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.Item;
import com.example.movieinfo_mvp.Network.Model.NaverSearch.MovieDetail;
import com.example.movieinfo_mvp.Network.MovieInfoOpenApiService;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.Repository.MovieDetailRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchMovieFragment extends Fragment implements View.OnClickListener {

    private View view;
    private Button searchbutton;
    private EditText searchEdittext;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
    private String searchName;
    private MovieInfoOpenApiService movieInfoOpenApiService;
    private MovieDetailRepository movieDetailRepository;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private SearchMovieAdapter searchMovieAdapter;

    public SearchMovieFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);
        dateFormat.format(mDate);
        searchMovieAdapter = new SearchMovieAdapter(view.getContext());
        searchbutton = view.findViewById(R.id.searchbutton);
        searchEdittext = view.findViewById(R.id.searchtext);
        recyclerView = view.findViewById(R.id.SearchRecyclerview);
        searchbutton.setOnClickListener(this);
        searchMovieAdapter.setOnclickListener(new SearchMovieAdapter.OnclickListener() {
            @Override
            public void onclick(View v, int pos, ImageButton imageButton) {
                Log.e("Start",pos + " 번쨰 클릭!");

            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchbutton:
                searchMovieAdapter.clear();
                movieDetailRepository = new MovieDetailRepository();
                movieInfoOpenApiService = movieDetailRepository.initBuild();
                movieSearch();
                break;
            default:
                break;
        }
    }

    public void movieSearch(){
        searchName = searchEdittext.getText().toString();
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchMovieAdapter);
        movieInfoOpenApiService.getMovies(searchName,100,dateFormat.getCalendar().get(Calendar.YEAR)-100,dateFormat.getCalendar().get(Calendar.YEAR)).enqueue(new Callback<MovieDetail>() {
            @Override
            public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                if(response.isSuccessful()){
                    MovieDetail movieDetail = response.body();
                    List<Item> items = movieDetail.getItems();
                    for(Item item : items){
                        searchMovieAdapter.add(item);
                    }
                } else {

                }
            }
            @Override
            public void onFailure(Call<MovieDetail> call, Throwable t) {
                Log.e("Fail",t.getMessage());
            }
        });
    }
}
