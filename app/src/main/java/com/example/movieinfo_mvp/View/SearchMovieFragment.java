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

import com.example.movieinfo_mvp.Adapter.DailyOfficeAdapter;
import com.example.movieinfo_mvp.Contract.SearchMovieContract;
import com.example.movieinfo_mvp.Network.Model.DBResult;
import com.example.movieinfo_mvp.Network.Model.Item;
import com.example.movieinfo_mvp.Network.Model.MovieDetail;
import com.example.movieinfo_mvp.Network.Model.MoviedbModel;
import com.example.movieinfo_mvp.Network.MovieConst;
import com.example.movieinfo_mvp.Network.MovieDeatilService;
import com.example.movieinfo_mvp.R;
import com.example.movieinfo_mvp.Repository.MovieDBRepository;
import com.example.movieinfo_mvp.Repository.MovieDetailRepository;

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
    private String searchName;
    private MovieDBRepository movieDBRepository;
    private MovieDeatilService movieDeatilService;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerView;
    private DailyOfficeAdapter dailyOfficeAdapter;

    public SearchMovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_search_movie, container, false);
        dailyOfficeAdapter = new DailyOfficeAdapter(view.getContext());
        searchbutton = view.findViewById(R.id.searchbutton);
        searchEdittext = view.findViewById(R.id.searchtext);
        recyclerView = view.findViewById(R.id.SearchRecyclerview);
        searchbutton.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.searchbutton:
                movieDBRepository = new MovieDBRepository();
                movieDeatilService = movieDBRepository.initBuild();
                movieSearch(movieDeatilService);
                break;
            default:
                break;
        }
    }

    public void movieSearch(MovieDeatilService movieDeatilService){
        searchName = searchEdittext.getText().toString();
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(dailyOfficeAdapter);
        movieDeatilService.getMovieDB(MovieConst.MovieDB_collection,MovieConst.MovieDB_service_key).enqueue(new Callback<DBResult>() {
            @Override
            public void onResponse(Call<DBResult> call, Response<DBResult> response) {
                Log.e("Start",call.request().url().toString() + "성공?");
            }

            @Override
            public void onFailure(Call<DBResult> call, Throwable t) {
                Log.e("Start",call.request().url().toString() + "..??");
            }
        });
/*        movieDeatilService.getSearch(searchName,100).enqueue(new Callback<MovieDetail>() {
                @Override
                public void onResponse(Call<MovieDetail> call, Response<MovieDetail> response) {
                    recyclerView.setNestedScrollingEnabled(false);
                    if(response.isSuccessful()){
                        MovieDetail movieDetail = response.body();
                        List<Item> items = movieDetail.getItems();
                        //어댑터 붙이면 된다..
                        for(Item search : items){

                        }
                        Log.e("Start",items.size() + " 크기");
                    } else{

                    }
                    recyclerView.setNestedScrollingEnabled(true);
                }

                @Override
                public void onFailure(Call<MovieDetail> call, Throwable t) {
                    Log.e("Start",call.request().url().toString() + " url");
                }
            });*/
    }
}
